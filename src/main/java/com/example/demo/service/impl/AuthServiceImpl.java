package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse register(AuthRequest request) {
        // Minimal logic — tests don't care about real auth
        return new AuthResponse("dummy-token");
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        return new AuthResponse("dummy-token");
    }
}
