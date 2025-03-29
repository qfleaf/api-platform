package com.qfleaf.web.random;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class RandomAuthKeyGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateAuthKey() {
        byte[] randomBytes = new byte[24]; // 24 字节 ~ 32 个 Base64 字符
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}