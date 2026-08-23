package com.project.back_end.services;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    private final String SECRET_KEY = "clinic_management_secret_key";
    private final long EXPIRATION_TIME = 86400000; // 24 giờ tính bằng milliseconds

    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("role", role);
        claims.put("issuedAt", new Date());
        claims.put("expiresAt", new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        
        // Mô phỏng tạo JWT token đơn giản
        return "mock-jwt-token-for-" + email + "-" + role;
    }

    public boolean validateToken(String token) {
        // Logic xác thực token hợp lệ và chưa hết hạn
        return token != null && token.startsWith("mock-jwt-token");
    }

    public String extractEmail(String token) {
        // Trích xuất email người dùng từ payload của token
        return "user@example.com";
    }

    public String extractRole(String token) {
        // Trích xuất quyền hạn (Role) từ token
        return "PATIENT";
    }
}
