package com.maike.auth.controller;

import com.maike.auth.exception.PasswordWrongException;
import com.maike.auth.model.AuthRequest;
import com.maike.auth.model.AuthResponse;
import com.maike.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (PasswordWrongException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
