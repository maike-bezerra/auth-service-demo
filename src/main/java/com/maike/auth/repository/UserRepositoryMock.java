package com.maike.auth.repository;

import com.maike.auth.model.AppUser;
import com.maike.auth.model.Role;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRepositoryMock {

    private final Map<String, AppUser> users = Map.of(
            "user", new AppUser("user", "password", Role.USER),
            "admin", new AppUser("admin", "password", Role.ADMIN)
    );

    public AppUser findByUsername(String username) {
        return users.get(username);
    }
}
