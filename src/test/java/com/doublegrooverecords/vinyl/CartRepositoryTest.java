package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;

    @Test
    public void throwsException_whenCustomerNotFound() {
        final long nonExistentCustomer = 2000L;
        final String knownCouponCode = "FN2187";

        assertThrows(CustomerNotFoundException.class, () -> {
            cartRepository.addCoupon(nonExistentCustomer, knownCouponCode);
        });
    }

    @Test
    public void discountIsNotAdded_whenCouponNotFound() throws Exception {
        final long knownCustomer = 1L;
        final String nonExistentCoupon = "wibble";

        cartRepository.addCoupon(knownCustomer, nonExistentCoupon);

        Cart cart = cartRepository.find(knownCustomer);
        assertThat(cart.getDiscount()).isEqualTo(Discount.NONE);
    }

    @Test
    public void addsDiscount_whenCouponAndCustomerExist() throws Exception {
        final long knownCustomer = 1L;
        final String knownCouponCode = "FN2187";

        cartRepository.addCoupon(knownCustomer, knownCouponCode);

        Cart cart = cartRepository.find(knownCustomer);
        assertThat(cart.getDiscount()).isEqualTo(Discount.TEN_PERCENT);
    }
}