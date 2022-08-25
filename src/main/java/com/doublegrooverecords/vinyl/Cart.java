package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
public class Cart {
    private List<CartProduct> products;
    private BigDecimal total;
    private Discount discount;

    public Cart(List<CartProduct> products, Discount discount) {
        this.products = products;
        this.total = products.stream()
                .map(Product::getPrice)
                .reduce(new BigDecimal("0"), BigDecimal::add);
        this.discount = discount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public BigDecimal getTotalWithDiscount() {
        return discount.apply(total);
    }
}
