package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class StoreNearbyDiscount {
    private StoreRepository storeRepository;

    public StoreNearbyDiscount(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void apply(Customer customer, Order order) {
        List<Store> storesNearCustomer = storeRepository.findByZip(customer.getZip());

        if (storesNearCustomer.size() > 0) {
            order.setShippingCost(new BigDecimal(0));
        }
    }
}
