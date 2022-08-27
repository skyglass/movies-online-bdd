package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishlistSupplier wishlistSupplier;

    public WishlistService(WishlistSupplier wishlistSupplier) {
        this.wishlistSupplier = wishlistSupplier;
    }

    public List<Product> getAllProductsForUser(Long customerId) {
        try {
            return wishlistSupplier.fetchAllItemsForUser(customerId);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addProductToWishlist(Long customerId, Product product) {
        try {
            return wishlistSupplier.addItemToWishlist(customerId, product);
        } catch (Exception e) {
            return false;
        }
    }
}
