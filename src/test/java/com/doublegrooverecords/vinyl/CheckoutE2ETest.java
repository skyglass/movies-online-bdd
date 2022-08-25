package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutE2ETest extends EndToEndTest {

    private String priceWithDiscount = "$70.26";

    @Test
    public void checkoutWithDiscount() {
        givenUserOnHomePage();
        whenTheyAddProductToTheCart();
        thenTheySeeProductInCart();

        whenTheyAddACoupon();
        thenTheySeeTheDiscount();

        whenTheyBuyTheCart();
        thenTheySeeOrderComplete();
        andTheySeeTheTotalPrice();
    }

    private void thenTheySeeTheDiscount() {
        assertThat(findByTestId("discount-amount").getText()).contains("10");
        assertThat(findByTestId("total-with-discount").getText()).contains(priceWithDiscount);
    }

    private void whenTheyAddACoupon() {
        final String couponConde = "FN2187";

        findByTestId("coupon-code").sendKeys(couponConde);
        findByTestId("add-coupon-code").click();
    }

    private void andTheySeeTheTotalPrice() {
        assertThat(findByTestId("total").getText()).isEqualTo(priceWithDiscount);
    }

    private void thenTheySeeOrderComplete() {
        findByTestId("order-complete");
    }

    private void whenTheyBuyTheCart() {
        findByTestId("buy-cart").click();
    }

    private void thenTheySeeProductInCart() {
        findByTestId("downtown-boy");
    }

    private void whenTheyAddProductToTheCart() {
        findByTestId("add-downtown-boy").click();
    }
}
