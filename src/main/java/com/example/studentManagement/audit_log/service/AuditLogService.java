package com.example.studentManagement.audit_log.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.studentManagement.audit_log.entity.AuditLogEntity;
import com.example.studentManagement.audit_log.repository.AuditLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;
    private final ObjectMapper objectMapper;

    // private static final Logger log =
    // LoggerFactory.getLogger(AuditLogService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAudit(String module, String action, String referenceId, Object payload, Object responseBody,
            LocalDateTime requesTime) {
        logAudit(module, action, referenceId, payload, responseBody, "SUCCESS", null, requesTime);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAudit(String module, String action, String referenceId, Object payload, Object responseBody,
            String status, String errorMessage, LocalDateTime requestTime) {
        log.info("AUDIT LOG -> Module: {}, Action: {}, RefID: {}, Status: {}", module, action, referenceId, status);

        try {
            String payloadStr = payload != null
                    ? (payload instanceof String ? (String) payload : objectMapper.writeValueAsString(payload))
                    : null;
            String responseStr = responseBody != null
                    ? (responseBody instanceof String ? (String) responseBody
                            : objectMapper.writeValueAsString(responseBody))
                    : null;

            String ipAddress = null;
            String sourceSystem = "UNKNOWN";

            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            try {
                if (attrs != null) {
                    if (attrs != null) {
                        HttpServletRequest request = attrs.getRequest();
                        ipAddress = request.getHeader("X-Forwarded-For");
                        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                            ipAddress = request.getRemoteAddr();
                        }
                        if (ipAddress != null && ipAddress.length() > 50) {
                            ipAddress = ipAddress.substring(0, 50);
                        }

                        sourceSystem = request.getHeader("User-Agent");
                        if (sourceSystem != null && sourceSystem.length() > 90) {
                            sourceSystem = sourceSystem.substring(0, 90);
                        } else if (sourceSystem == null) {
                            sourceSystem = "UNKNOWN";
                        }
                    }
                }
            } catch (Exception e) {
                AuditLogEntity auditLogEntity = AuditLogEntity.builder()
                        .module(module)
                        .action(action)
                        .referenceId(referenceId)
                        .payload(payloadStr)
                        .responseBody(responseStr)
                        .status(status)
                        .errorMessage(errorMessage)
                        .sourceSystem(sourceSystem)
                        .requestTime(requestTime != null ? requestTime : LocalDateTime.now())
                        .executionTime(LocalDateTime.now())
                        .ipAddress(ipAddress)
                        .createdAt(LocalDateTime.now())
                        .build();

                auditLogRepository.save(auditLogEntity);
            }
        } catch (Exception e) {
            log.error("Failed to persist audit log for Action: {}, RefID: {}. Error: {}", action, referenceId,
                    e.getMessage());
        }
    }
}
