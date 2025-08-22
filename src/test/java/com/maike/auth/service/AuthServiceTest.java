package com.maike.auth.service;

import com.maike.auth.model.AuthRequest;
import com.maike.auth.repository.UserRepositoryMock;
import com.maike.auth.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthServiceTest {

    private AuthService authService;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        authService = new AuthService(jwtUtil, new UserRepositoryMock());
    }

    @Test
    void authenticate_validCredentials_returnsToken() {
        AuthRequest request = new AuthRequest("admin", "password");
        var response = authService.authenticate(request);

        assertNotNull(response);
        assertNotNull(response.token());
        assertFalse(response.token().isEmpty());
    }

    @Test
    void authenticate_invalidCredentials_throwsException() {
        AuthRequest request = new AuthRequest("user", "wrongpass");

        Exception exception = assertThrows(RuntimeException.class,
                () -> authService.authenticate(request));

        assertEquals("Invalid credentials", exception.getMessage());
    }
}
