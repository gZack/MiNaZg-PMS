package com.minazg.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {
    public static void main(String[] args) {
        String password = "abc125";

        System.out.println(args[0]);
        //String password = args[0];
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }
}
