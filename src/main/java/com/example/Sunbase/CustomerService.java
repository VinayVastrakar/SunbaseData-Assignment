package com.example.Sunbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomerList(String bearerToken) {
        return customerRepository.getCustomerList(bearerToken);
    }

    public ResponseEntity<String> createCustomer(Customer customer, String bearerToken) {
        return customerRepository.createCustomer(customer, bearerToken);
    }

    public ResponseEntity<String> deleteCustomer(String uuid, String bearerToken) {
        return customerRepository.deleteCustomer(uuid, bearerToken);
    }

    public ResponseEntity<String> updateCustomer(String uuid, Customer customer, String bearerToken) {
        return customerRepository.updateCustomer(uuid, customer, bearerToken);
    }
}
