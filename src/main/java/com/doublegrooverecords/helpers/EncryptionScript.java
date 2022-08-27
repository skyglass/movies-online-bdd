package com.doublegrooverecords.helpers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionScript {
    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("Incorrect number of arguments");
        String encodedPassword = new BCryptPasswordEncoder().encode(args[0]);
        System.out.printf("encoded password: %s%n", encodedPassword);
    }
}
