package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InMemoryWishlistSupplierTest {
    @Nested
    @DisplayName("wishlist flow")
    class WishlistFlow {
        @Test
        void returnsEmptyList_whenCustomerHasNoProducts() throws Exception {
            final Long customerId = 1L;

            final WishlistSupplier supplier = new InMemoryWishlistSupplier();

            assertThat(supplier.fetchAllItemsForUser(customerId)).isEqualTo(Collections.emptyList());
        }

        @Test
        void returnsProducts_whenOneIsAdded() throws Exception {
            final Long customerId = 1L;
            final Product product = new Product(10L, null, null, null, null, null, null, null);

            final WishlistSupplier supplier = new InMemoryWishlistSupplier();

            final boolean success = supplier.addItemToWishlist(customerId, product);
            assertThat(success).isTrue();

            assertThat(supplier.fetchAllItemsForUser(customerId)).isEqualTo(List.of(product));
        }

        @Test
        void returnsUnAlteredListOfProducts_whenOneIsAddedASecondTime() throws Exception {
            final Long customerId = 1L;
            final Product product = new Product(10L, null, null, null, null, null, null, null);

            final WishlistSupplier supplier = new InMemoryWishlistSupplier();

            supplier.addItemToWishlist(customerId, product);
            final boolean success = supplier.addItemToWishlist(customerId, product);
            assertThat(success).isFalse();

            assertThat(supplier.fetchAllItemsForUser(customerId)).isEqualTo(List.of(product));
        }

        @Test
        void returnsUpdatedListOfProducts_whenASecondProductIsAdded() throws Exception {
            final Long customerId = 1L;
            final Product product = new Product(10L, null, null, null, null, null, null, null);
            final Product product2 = new Product(20L, null, null, null, null, null, null, null);

            final WishlistSupplier supplier = new InMemoryWishlistSupplier();

            supplier.addItemToWishlist(customerId, product);
            final boolean success = supplier.addItemToWishlist(customerId, product2);
            assertThat(success).isTrue();

            assertThat(supplier.fetchAllItemsForUser(customerId)).isEqualTo(List.of(product, product2));
        }
    }

    @Nested
    @DisplayName("wishlists are isolated by customer id")
    class IsolatedWishlistBehavior {
        @Test
        void returnsEmptyListForCustomer2_whenTheyHaveAnEmptyWishlist() throws Exception {
            final Long firstCustomerId = 1L;
            final Long secondCustomerId = 2L;

            final Product product = new Product(10L, null, null, null, null, null, null, null);

            final WishlistSupplier supplier = new InMemoryWishlistSupplier();
            supplier.addItemToWishlist(firstCustomerId, product);

            assertThat(supplier.fetchAllItemsForUser(secondCustomerId)).isEqualTo(Collections.emptyList());
        }

        @Test
        void returnsUpdatedWishlistForSecondCustomer_whenTheirWishlistIsAltered() throws Exception {
            final Long firstCustomerId = 1L;
            final Long secondCustomerId = 2L;

            final Product product = new Product(10L, null, null, null, null, null, null, null);

            final WishlistSupplier supplier = new InMemoryWishlistSupplier();

            final boolean success = supplier.addItemToWishlist(secondCustomerId, product);
            assertThat(success).isTrue();

            assertThat(supplier.fetchAllItemsForUser(firstCustomerId)).isEqualTo(Collections.emptyList());
            assertThat(supplier.fetchAllItemsForUser(secondCustomerId)).isEqualTo(List.of(product));
        }
    }
}