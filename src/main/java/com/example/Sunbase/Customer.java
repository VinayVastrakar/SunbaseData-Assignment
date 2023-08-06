package com.example.Sunbase;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    String first_name;
    String last_name;
    String street;
    String address;
    String city;
    String state;
    String email;
    String phone;
}
