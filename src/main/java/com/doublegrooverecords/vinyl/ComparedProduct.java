package com.doublegrooverecords.vinyl;

public class ComparedProduct {
    private final Product product;
    private final ComparedPosition comparedPosition;

    public ComparedProduct(Product product, ComparedPosition comparedPosition) {
        this.product = product;
        this.comparedPosition = comparedPosition;
    }

    public String getAlbumTitle() {
        return product.getAlbumTitle();
    }

    public ComparedPosition getPosition() {
        return comparedPosition;
    }
}
