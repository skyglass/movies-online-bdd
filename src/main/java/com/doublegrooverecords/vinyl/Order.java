package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Long customerId;
    private Long orderId;
    private BigDecimal total;
    private List<Product> products = new ArrayList<>();

    String streetAddress1;
    String streetAddress2;
    String stateName;
    String city;
    String zip;
    BigDecimal shippingCost;

    public Order(long orderId, long customerId, String streetAddress1, String streetAddress2, String stateName, String city, String zip, BigDecimal shippingCost, BigDecimal total, List<Product> products) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.stateName = stateName;
        this.city = city;
        this.zip = zip;
        this.shippingCost = shippingCost;
        this.total = total;
        this.products = products;
    }

    public Order() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
