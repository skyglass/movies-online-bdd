package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.util.List;

@Data
public class ProductPage {
    Product mainProduct;
    List<Product> recommended;
    List<Product> alsoBought;
}
