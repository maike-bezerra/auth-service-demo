package com.maike.auth.service;

import com.maike.auth.model.AuthRequest;
import com.maike.auth.model.AuthResponse;
import com.maike.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        // Mocked users
        if ("admin".equals(request.username()) && "1234".equals(request.password())) {
            String token = jwtUtil.generateToken(request.username());
            return new AuthResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
