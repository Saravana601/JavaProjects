package com.Srvnn.CustomerManagementAPI.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate = new RestTemplate();

    String api_link = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    // Calls external api and get token as response
    public String callExternalApiForToken() {

        String requestBody = "{\"login_id\":\"test@sunbasedata.com\",\"password\":\"Test@123\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                api_link,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Step 6");
            return responseEntity.getBody();
        } else {
            System.out.println("Step 7");
            return null;
        }
    }
}
