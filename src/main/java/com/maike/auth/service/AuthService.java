package com.maike.auth.service;

import com.maike.auth.exception.PasswordWrongException;
import com.maike.auth.model.AppUser;
import com.maike.auth.model.AuthRequest;
import com.maike.auth.model.AuthResponse;
import com.maike.auth.repository.UserRepositoryMock;
import com.maike.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepositoryMock userRepository;

    public AuthResponse authenticate(AuthRequest request) throws PasswordWrongException {
        AppUser user = userRepository.findByUsername(request.username());

        if (user == null || !user.password().equals(request.password())) {
            throw new PasswordWrongException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
}
