package com.Srvnn.CustomerManagementAPI.service;

import com.Srvnn.CustomerManagementAPI.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;


@Service
public class CustomerService {
    private final RestTemplate restTemplate = new RestTemplate();



    // Create new Customer
    public ResponseEntity<String> createNewCustomer(Customer customer) {
        String cmd = "create";

        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        String token = "Bearer dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

        // Create headers with Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

        return restTemplate.exchange(
                apiUrl + "?cmd=" + cmd,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }

    // Get customer list
    public ResponseEntity<List<Customer>> getCustomerList() {
        String cmd = "get_customer_list";

        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        String token = "Bearer dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Accept", "text/html");

        headers.setContentType(MediaType.TEXT_HTML);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                apiUrl + "?cmd=" + cmd,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Customer>>() {}
        );
    }

    // delete Customer
    public ResponseEntity<String> deleteCustomer(UUID uuid) {

        // Define the request parameters
        String cmd = "delete";

        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        String token = "Bearer dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

        // Create headers with Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        // Create request parameters
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("uuid", uuid.toString());

        // Create an HTTP entity with headers and parameters
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        return restTemplate.exchange(
                apiUrl + "?cmd=" + cmd,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }

    // Update Customer
    public ResponseEntity<String> updateCustomer(String uuid, Customer customerUpdateRequest) {
        // Define the request parameters
        String cmd = "update";

        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        String token = "Bearer dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

        // Create headers with Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        // Create request parameters
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("uuid", uuid);

        // Create a JSON request body
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(customerUpdateRequest);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Error generating JSON request body");
        }

        // Create an HTTP entity with headers and parameters
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request and returns it
        return restTemplate.exchange(
                apiUrl + "?cmd=" + cmd,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }
}
