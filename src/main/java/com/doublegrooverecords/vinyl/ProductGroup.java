package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.util.List;

@Data
public class ProductGroup {
    Long id;
    List<Long> items;
    private List<Product> products;
    private String name;

    public ProductGroup(Long id, List<Product> products, String name) {
        this.id = id;
        this.products = products;
        this.name = name;
    }
}
