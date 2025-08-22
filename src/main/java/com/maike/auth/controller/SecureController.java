package com.maike.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/api/secure")
    public String secureEndpoint() {
        return "This is a protected endpoint. You have a valid JWT!";
    }
}
