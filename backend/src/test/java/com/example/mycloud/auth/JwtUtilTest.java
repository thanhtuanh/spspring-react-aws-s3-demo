package com.example.mycloud.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        jwtUtil = new JwtUtil();
        // Setze ein gültiges Secret (32 Bytes)
        String secret = Base64.getEncoder().encodeToString("01234567890123456789012345678901".getBytes());
        ReflectionTestUtils.setField(jwtUtil, "secret", secret);
        jwtUtil.initKey();
    }

    @Test
    void generateAndExtractToken() {
        String token = jwtUtil.generateToken("tuan");
        String username = jwtUtil.extractUsername(token);
        assertThat(username).isEqualTo("tuan");
    }
}