package com.example.studentManagement.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String SECRET = "demo-secret-key-123456-demo-secret-key-123456";
    public static final long MILLISECONDS_PER_DAY = 86_400_000L; 

    public String generateAccessToken(String username, String role) {


        return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + MILLISECONDS_PER_DAY))
        .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)),
                  SignatureAlgorithm.HS256)
        .compact();
    }

    public String generateRefreshToken(String username, String role) {
        return Jwts.builder().setSubject(username).claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + MILLISECONDS_PER_DAY * 7))
                    .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)),
                  SignatureAlgorithm.HS256)
        .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    
}
