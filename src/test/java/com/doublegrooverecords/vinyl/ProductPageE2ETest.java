package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductPageE2ETest extends EndToEndTest {
    final Product expectedProduct = new Product("Downtown boy", "Bobby Joel", "Lorem ipsum", "$78.06", "2");

    @Test
    void purchaseProduct() {
        givenUserOnHomePage();
        whenTheyClickOnProduct();
        thenTheySeeProductDetails();

        whenTheyAddTheProductToWishlist();
        thenTheySeeItInWishlist();

        whenTheyNavigateToProductPage();
        whenTheyAddMultipleProductsToTheCart();
        thenTheySeeProductsInTheCart();
    }

    private void whenTheyClickOnProduct() {
        findByTestId("product-id-3").click();
    }

    private void thenTheySeeProductDetails() {
        assertThat(findByTestId("product-name").getText()).isEqualTo(expectedProduct.name);
        assertThat(findByTestId("artist-name").getText()).contains(expectedProduct.artistName);
        assertThat(findByTestId("price").getText()).isEqualTo(expectedProduct.price);
        assertThat(findByTestId("description").getText()).contains(expectedProduct.descriptionSegment);
    }

    private void whenTheyAddTheProductToWishlist() {
        findByTestId("add-to-wishlist-button").click();
    }

    private void thenTheySeeItInWishlist() {
        assertThat(findByTestId("header").getText()).isEqualTo("Your Wishlist");
        assertThat(findByTestId("wishlist-product-3-name").getText().toLowerCase()).isEqualTo(expectedProduct.name.toLowerCase());
        assertThat(findByTestId("wishlist-artist-3-name").getText()).contains(expectedProduct.artistName);
        assertThat(findByTestId("wishlist-price-3").getText()).isEqualTo(expectedProduct.price);
    }

    private void whenTheyNavigateToProductPage() {
        findByTestId("wishlist-product-3").click();
    }

    private void whenTheyAddMultipleProductsToTheCart() {
        findByTestId("quantity").sendKeys(expectedProduct.quantity);
        findByTestId("add-to-cart-button").click();
    }

    private void thenTheySeeProductsInTheCart() {
        assertThat(findByTestId("product-3-name").getText()).isEqualTo(expectedProduct.name);
        assertThat(findByTestId("artist-3-name").getText()).contains(expectedProduct.artistName);
        assertThat(findByTestId("product-3-quantity").getText()).isEqualTo(expectedProduct.quantity);

        assertThat(findByTestId("cart-total").getText()).isEqualTo("$156.12");
    }

    static class Product {
        final String name;
        final String artistName;
        final String descriptionSegment;
        final String quantity;
        final String price;

        public Product(String name, String artistName, String descriptionSegment, String price, String quantity) {
            this.name = name;
            this.artistName = artistName;
            this.descriptionSegment = descriptionSegment;
            this.quantity = quantity;
            this.price = price;
        }
    }
}
