package com.doublegrooverecords.vinyl;

import java.math.BigDecimal;

public class CartProduct extends Product {
    public CartProduct(Long id, String albumTitle, Artist artist, String imageUrl, BigDecimal price) {
        super(id, albumTitle, artist, imageUrl, price, null, null, null);
    }

    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().multiply(new BigDecimal(quantity));
    }

    public BigDecimal getIndividualPrice() { return super.getPrice(); }
}
