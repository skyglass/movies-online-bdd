package com.doublegrooverecords.vinyl;

import java.util.List;

public interface WishlistSupplier {

    List<Product> fetchAllItemsForUser(Long customerId) throws TimedOutException, CustomerNotFoundException;

    boolean addItemToWishlist(Long customerId, Product product) throws TimedOutException, CustomerNotFoundException;

    class TimedOutException extends Exception {
    }

    class CustomerNotFoundException extends Exception {
    }
}
