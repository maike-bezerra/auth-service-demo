package com.maike.auth.util;

import com.maike.auth.model.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil() {
        String secret = System.getenv("JWT_SECRET");
        if (secret == null || secret.isEmpty()) {
            this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            System.out.println("⚠️ JWT_SECRET not set, generated temporary key.");
        } else {
            this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        }
    }

    public String generateToken(AppUser user) {
        return Jwts.builder()
                .setSubject(user.username())
                .claim("role", user.role().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username);
    }
}
