package com.doublegrooverecords.vinyl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Profile("!production")
public class InMemoryWishlistSupplier implements WishlistSupplier {
    Map<Long, List<Product>> customerToProducts = new HashMap<>();

    @Override
    public List<Product> fetchAllItemsForUser(Long customerId) throws TimedOutException, CustomerNotFoundException {
        if (!customerToProducts.containsKey(customerId))
            return List.of();

        return customerToProducts.get(customerId);
    }

    @Override
    public boolean addItemToWishlist(Long customerId, Product product) throws TimedOutException, CustomerNotFoundException {
        final List<Product> wishlist = makeWishlistForCustomerId(customerId);

        if (wishlist.contains(product)) {
            return false;
        }

        wishlist.add(product);
        customerToProducts.put(customerId, wishlist);
        return true;
    }

    private List<Product> makeWishlistForCustomerId(Long customerId) {
        if (customerToProducts.containsKey(customerId))
            return customerToProducts.get(customerId);

        return new LinkedList<>();
    }
}
