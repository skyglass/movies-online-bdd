package com.doublegrooverecords.vinyl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Store {
    Long id;
    String street_address_1;
    String street_address_2;
    String state_name;
    String city;
    String zip;
}
