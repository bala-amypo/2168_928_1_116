package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenProvider {

    // injected via reflection in tests
    private String jwtSecret;
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        byte[] keyBytes;

        // if secret is too short, pad/expand deterministically
        if (jwtSecret.length() < 32) {
            String padded = String.format("%-32s", jwtSecret).replace(' ', '0');
            keyBytes = padded.getBytes(StandardCharsets.UTF_8);
        } else {
            keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long userId, String email, Set<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("roles", String.join(",", roles));

        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
