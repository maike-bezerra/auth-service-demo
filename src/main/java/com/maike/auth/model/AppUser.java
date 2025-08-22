package com.maike.auth.model;

public record AppUser(
        String username,
        String password,
        Role role
) {}
