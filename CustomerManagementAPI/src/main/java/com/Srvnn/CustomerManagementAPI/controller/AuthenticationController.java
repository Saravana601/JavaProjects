package com.Srvnn.CustomerManagementAPI.controller;
import com.Srvnn.CustomerManagementAPI.controller.dto.LoginDTO;
import com.Srvnn.CustomerManagementAPI.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthenticationController {
    private final String LOGIN_ID = "test@sunbasedata.com";
    private final String PASSWORD = "Test@123";
    public static String token;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginRequest) {
        String loginId = loginRequest.getLoginId();
        String password = loginRequest.getPassword();

        if (loginId.equals(LOGIN_ID) && password.equals(PASSWORD)) {
            token = authenticationService.callExternalApiForToken();
            if (token != null) {
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to obtain Bearer token.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
