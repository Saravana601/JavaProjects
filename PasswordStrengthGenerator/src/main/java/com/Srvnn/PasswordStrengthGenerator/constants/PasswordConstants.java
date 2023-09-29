package com.Srvnn.PasswordStrengthGenerator.constants;

public class PasswordConstants {

        public static final String SPECIAL_CHARACTER_PATTERN = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/].*";
        public static final String DIGIT_PATTERN = ".*[0-9].*";
        public static final String UPPERCASE_PATTERN = ".*[A-Z].*";
        public static final String LOWERCASE_PATTERN = ".*[a-z].*";
        public static final String[] COMMON_WORDS = {"password", "123456", "qwerty", "letmein", "admin"};
        public static final double THRESHOLD_ENTROPY = 40;
        public static final String CHARACTER_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";

}
