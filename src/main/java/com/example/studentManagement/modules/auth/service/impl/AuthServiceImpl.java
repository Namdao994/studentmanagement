package com.example.studentManagement.modules.auth.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentManagement.common.exception.BusinessException;
import com.example.studentManagement.common.exception.ErrorCode;
import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthLoginResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.modules.auth.repository.UserRepository;
import com.example.studentManagement.modules.auth.service.AuthService;
import com.example.studentManagement.modules.auth.service.login.LoginStrategy;
import com.example.studentManagement.modules.auth.service.register.RegisterStrategy;
import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.role.enums.RoleType;
import com.example.studentManagement.modules.role.repository.RoleRepository;
import com.example.studentManagement.util.JwtUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    private final Map<RoleType, RegisterStrategy> registerStrategyMap;
    private final Map<RoleType, LoginStrategy> loginStrategyMap;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            JwtUtil jwtUtil,
            List<RegisterStrategy> registerStrategies,
            List<LoginStrategy> loginStrategies) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

        this.jwtUtil = jwtUtil;
        this.registerStrategyMap = registerStrategies.stream()
                .collect(Collectors.toMap(RegisterStrategy::getRole, s -> s));
        this.loginStrategyMap = loginStrategies.stream().collect(Collectors.toMap(LoginStrategy::getRole, s -> s));
    }

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        if (request.getRole() == RoleType.ADMIN) {
            throw new BusinessException(ErrorCode.ROLE_ASSIGN_NOT_ALLOWED.name(), "ADMIN is not allowed");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        RoleEntity role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND.name(), "Role not found"));

        userEntity.setRoleEntities(Set.of(role));
        userEntity.setFullName(request.getFullName());
        userEntity.setGender(request.getGender());
        userEntity.setDateOfBirth(request.getDateOfBirth());
        userRepository.save(userEntity);

        RegisterStrategy strategy = registerStrategyMap.get(request.getRole());

        if (strategy == null) {
            throw new BusinessException(ErrorCode.ROLE_NOT_SUPPORTED.name(),
                    "Role is not supported for login response");
        }

        return strategy.register(userEntity, request);
    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {

        UserEntity userEntity = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND.name(), "User not found"));

        boolean isValidPassword = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());

        if (!isValidPassword) {
            throw new BusinessException(ErrorCode.PASSWORD_IS_INCORRECT.name(), "Password is incorrect");
        }

        boolean isActive = userEntity.getIsActive();
        if (!isActive) {
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_ACTIVATED.name(), "Account is not activated");
        }

        List<RoleType> roles = userEntity.getRoleEntities().stream().map(RoleEntity::getName).toList();
        String accessToken = jwtUtil.generateAccessToken(userEntity.getUsername(), roles);
        String refreshToken = jwtUtil.generateRefreshToken(userEntity.getUsername(), roles);

        for (RoleType role : roles) {
            LoginStrategy strategy = loginStrategyMap.get(role);
            if (strategy != null) {
                return strategy.login(userEntity, accessToken, refreshToken);
            }
        }
        throw new BusinessException(ErrorCode.ROLE_NOT_SUPPORTED.name(), "Role is not supported for login response");
    }

}
