package com.Srvnn.PasswordStrengthGenerator.service;

import com.Srvnn.PasswordStrengthGenerator.model.Password;
import org.springframework.stereotype.Service;
import static com.Srvnn.PasswordStrengthGenerator.constants.PasswordConstants.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This service class provides methods to check the strength of passwords based on various criteria
 * such as length, special characters, digits, and more.
 */

@Service
public class PasswordService {

    public String checkPasswordStrength(Password password) {
        if (!isLengthValid(password)) {
            return "Password must be at least 8 characters long.";
        }
        if (!containsSpecialCharacter(password)) {
            return "Password must contain at least one special character like @, $, #, etc.";
        }
        if (!containsLowercase(password)) {
            return "Password must contain lowercase letters.";
        }
        if (!containsUppercase(password)) {
            return "Password must contain uppercase letters.";
        }
        if (!containsDigit(password)) {
            return "Password must contain at least one digit.";
        }
        if (!isNotCommonWord(password)) {
            return "Password must not contain common words like 'Qwerty', 'admin', 'username', etc.";
        }
        if (!hasSufficientEntropy(password)) {
            return "Password is too weak. It should be more unpredictable.";
        }
        return "Your password is strong.";

    }

    // Checks the presence of at least one special character in the password.
    private boolean containsSpecialCharacter(Password password) {
        return password.getPassword().matches(SPECIAL_CHARACTER_PATTERN);
    }

    // Validates the length of the password. It should be at least 8 characters long.
    private boolean isLengthValid(Password password) {
        return password.getPassword().length() > 8;
    }

    // Checks the presence of at least one digit in the password.
    private boolean containsDigit(Password password) {
        return password.getPassword().matches(DIGIT_PATTERN);
    }

    // Checks the presence of at least one uppercase letter in the password.
    private boolean containsUppercase(Password password) {
        return password.getPassword().matches(UPPERCASE_PATTERN);
    }

    // Checks the presence of at least one lowercase letter in the password.
    private boolean containsLowercase(Password password) {
        return password.getPassword().matches(LOWERCASE_PATTERN);
    }

    // Checks if the password contains common words. Common words are case-insensitive.
    private boolean isNotCommonWord(Password password) {
        String lowercasePassword = password.getPassword().toLowerCase();

        for (String commonWord : COMMON_WORDS) {
            if (lowercasePassword.equals(commonWord)) {
                return false;
            }
        }

        return true;
    }

    // Calculates the entropy of the password to determine its strength.
    // Entropy is a measure of password unpredictability.
    private boolean hasSufficientEntropy(Password password) {
        int characterSetSize = calculateCharacterSetSize(password.getPassword());
        int passwordLength = password.getPassword().length();
        double entropy = Math.log(Math.pow(characterSetSize, passwordLength)) / Math.log(2);
        return entropy >= THRESHOLD_ENTROPY;
    }


    private int calculateCharacterSetSize(String password) {
        Set<Character> uniqueCharacters = new HashSet<>();

        for (char c : password.toCharArray()) {
            if (CHARACTER_SET.contains(String.valueOf(c))) {
                uniqueCharacters.add(c);
            }
        }

        return uniqueCharacters.size();
    }

}
