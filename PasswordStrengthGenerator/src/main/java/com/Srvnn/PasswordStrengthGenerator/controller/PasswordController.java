package com.Srvnn.PasswordStrengthGenerator.controller;

import com.Srvnn.PasswordStrengthGenerator.model.Password;
import com.Srvnn.PasswordStrengthGenerator.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password-strength")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @PostMapping("/generate")
    public String checkPasswordStrength(@RequestBody Password password){
        return passwordService.checkPasswordStrength(password);
    }
}
