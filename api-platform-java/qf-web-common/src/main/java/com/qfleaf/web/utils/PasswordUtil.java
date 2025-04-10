package com.qfleaf.web.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class PasswordUtil {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private PasswordUtil() {
    }

    public static String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
