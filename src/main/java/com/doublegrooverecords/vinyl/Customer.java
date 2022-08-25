package com.doublegrooverecords.vinyl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String username;
    private String password;
    private String emailAddress;
    private String streetAddress1;
    private String streetAddress2;
    private String stateName;
    private String city;
    private String zip;
}
