package com.example.Sunbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class CustomerRepository {
    private static final String CUSTOMER_API_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

    @Autowired
    RestTemplate restTemplate;


    public ResponseEntity<String> createCustomer(Customer customer, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

        return restTemplate.exchange(
                CUSTOMER_API_URL + "?cmd=create",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }
    public List<Customer> getCustomerList(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Customer[]> response = restTemplate.exchange(
                CUSTOMER_API_URL + "?cmd=get_customer_list",
                HttpMethod.GET,
                entity,
                Customer[].class
        );

        return Arrays.asList(response.getBody());
    }

    public ResponseEntity<String> deleteCustomer(String uuid, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                CUSTOMER_API_URL + "?cmd=delete&uuid=" + uuid,
                HttpMethod.POST,
                entity,
                String.class
        );
    }

    public ResponseEntity<String> updateCustomer(String uuid, Customer customer, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

        return restTemplate.exchange(
                CUSTOMER_API_URL + "?cmd=update&uuid=" + uuid,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }
}
