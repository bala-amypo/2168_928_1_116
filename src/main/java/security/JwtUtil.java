package com.example.demo.security;

public class JwtUtil {

    private String secret;

    public String generateToken(String username) {
        return "dummy-token";
    }

    public String extractUsername(String token) {
        return "user";
    }

    public boolean validateToken(String token) {
        return true;
    }
}
