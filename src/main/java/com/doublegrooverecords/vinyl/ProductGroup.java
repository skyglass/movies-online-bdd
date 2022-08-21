package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.util.List;

@Data
public class ProductGroup {
    Long id;
    List<Long> items;
    private List<Product> products;

    public ProductGroup(Long id, List<Product> products) {
        this.id = id;
        this.products = products;
    }
}
