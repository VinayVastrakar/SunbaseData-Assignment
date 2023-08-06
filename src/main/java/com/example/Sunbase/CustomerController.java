package com.example.Sunbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestParam("login_id") String loginId,
                                                   @RequestParam("password") String password) {
        String bearerToken = authenticationService.authenticateUser(loginId, password);
        if (bearerToken != null) {
            return ResponseEntity.ok(bearerToken);
        } else {
            return ResponseEntity.status(401).body("Invalid Authorization");
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestHeader("Authorization") String bearerToken,
                                                 @RequestBody Customer customer) {
        return customerService.createCustomer(customer, bearerToken);
    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomerList(@RequestHeader("Authorization") String bearerToken) {
        List<Customer> customers = customerService.getCustomerList(bearerToken);
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/customers/{uuid}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable String uuid,
                                                 @RequestHeader("Authorization") String bearerToken) {
        return customerService.deleteCustomer(uuid, bearerToken);
    }

    @PostMapping("/customers/{uuid}/update")
    public ResponseEntity<String> updateCustomer(@PathVariable String uuid,
                                                 @RequestHeader("Authorization") String bearerToken,
                                                 @RequestBody Customer customer) {
        return customerService.updateCustomer(uuid, customer, bearerToken);
    }
}
