package com.Srvnn.CustomerManagementAPI.controller;

import com.Srvnn.CustomerManagementAPI.model.Customer;
import com.Srvnn.CustomerManagementAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {


    @Autowired
    CustomerService customerService;


    @PostMapping("/create-customer")
    public ResponseEntity<Map<String, String>> createCustomer(@RequestBody Customer customer) {
        // checks first name and last name are not null
        if (customer.getFirst_name() != null && customer.getLast_name() != null) {
            ResponseEntity<String> response = customerService.createNewCustomer(customer);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                Map<String, String> successResponse = new HashMap<>();
                successResponse.put("message", "Successfully Created");
                return ResponseEntity.status(HttpStatus.OK).body(successResponse);
            } else {
                Map<String, String> failureResponse = new HashMap<>();
                failureResponse.put("message", "Failed to create customer.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failureResponse);
            }
        } else {
            Map<String, String> missingFieldsResponse = new HashMap<>();
            missingFieldsResponse.put("message", "First Name or Last Name is missing.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingFieldsResponse);
        }
    }




    @GetMapping("/getCustomerList")
    public List<Customer> getCustomerList() {

        ResponseEntity<List<Customer>> response = customerService.getCustomerList();

        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.getBody());

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return Collections.emptyList();
        }
    }


    @PostMapping("/delete/{uuid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String uuid) {

        ResponseEntity<String> response = customerService.deleteCustomer(UUID.fromString(uuid));

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(response.getStatusCodeValue()).body("Successfully deleted");
        } else if (response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.status(response.getStatusCodeValue()).body("Error Not deleted");
        } else if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.status(response.getStatusCodeValue()).body("UUID not found");
        } else {
            return ResponseEntity.status(response.getStatusCodeValue()).body("Unknown error");
        }
    }

    @PostMapping("/update/{uuid}")
    public ResponseEntity<String> updateCustomer(@PathVariable String uuid, @RequestBody Customer customerUpdateRequest){

        ResponseEntity<String> response = customerService.updateCustomer(uuid, customerUpdateRequest);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(200).body("Successfully Updated");
        }
        else if (response.getStatusCode().is4xxClientError()) {
            if (response.getStatusCodeValue() == 400) {
                return ResponseEntity.status(400).body("Body is Empty");
            } else {
                return ResponseEntity.status(500).body("UUID not found");
            }
        }
        else {
            return ResponseEntity.status(response.getStatusCodeValue()).body("Unknown error");
        }

    }
}
