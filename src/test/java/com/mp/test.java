package com.mp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
//        DefaultPasswordEncoder passwordEncoder = new DefaultPasswordEncoder();
//        System.out.println(passwordEncoder.encode("nihao"));
//        System.out.println(passwordEncoder.matches("nihao","$2a$10$eAri/NfwZzRDRMkUvjwA/.fL6lnPTdN10AinBsnBgZLsWZMWEvwgq"));
//        System.out.println(JWTUtils.createToken("lisi"));
//        String encode = encoder.encode("123");
//        System.out.println(encode);
//        System.out.println(new DefaultPasswordEncoder().matches("123",encode));
        System.out.println(new BCryptPasswordEncoder().encode("123"));

    }

}
