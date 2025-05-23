package com.example.mycloud.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    private Key hmacKey;

    @PostConstruct
    public void initKey() {
        try {
            log.debug("👉 Eingelesener Secret: {}", secret);
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            if (decodedKey.length < 32) {
                throw new IllegalArgumentException("JWT Secret ist zu kurz. Erwartet werden mindestens 32 Bytes für HS256.");
            }

            this.hmacKey = Keys.hmacShaKeyFor(decodedKey);
            log.debug("✅ JWT Secret erfolgreich geladen ({} Bytes)", decodedKey.length);

        } catch (IllegalArgumentException e) {
            log.error("❌ Ungültiger JWT Secret Key: {}", e.getMessage());
            throw new RuntimeException("Ungültiger JWT Secret Key: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("❌ Fehler beim Initialisieren des JWT Keys", e);
            throw new RuntimeException("Fehler beim Initialisieren des JWT Keys", e);
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 Stunden
                .signWith(hmacKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
