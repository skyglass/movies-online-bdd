package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WishlistServiceTest {
  @Test
  void returnsNull_whenFetchAllItemsForUser_throwsTimedOutException() throws Exception {
    final Long customerId = 40L;

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.fetchAllItemsForUser(customerId)).thenThrow(new WishlistSupplier.TimedOutException());

    assertThat(wishlistService.getAllProductsForUser(customerId)).isNull();
  }

  @Test
  void returnsNull_whenFetchAllItemsForUser_throwsCustomerNotFoundException() throws Exception {
    final Long nonExistentCustomer = -1L;

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.fetchAllItemsForUser(nonExistentCustomer)).thenThrow(new WishlistSupplier.CustomerNotFoundException());

    assertThat(wishlistService.getAllProductsForUser(nonExistentCustomer)).isNull();
  }

  @Test
  void returnsList_whenFetchAllItemsForUser_returnsList() throws Exception {
    final Long customerId = 40L;

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.fetchAllItemsForUser(customerId)).thenReturn(Collections.emptyList());

    assertThat(wishlistService.getAllProductsForUser(customerId)).isEqualTo(Collections.emptyList());
  }

  @Test
  void returnsFalse_whenAddItemToWishlist_throwsCustomerNotFoundException() throws Exception {
    final Long nonExistentCustomer = -1L;
    final Product product = new Product(1L, null, null, null, null, null, null, null);

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.addItemToWishlist(nonExistentCustomer, product)).thenThrow(new WishlistSupplier.CustomerNotFoundException());

    assertThat(wishlistService.addProductToWishlist(nonExistentCustomer, product)).isFalse();
  }

  @Test
  void returnsFalse_whenAddItemToWishlist_throwsTimedOutException() throws Exception {
    final Long customerId = 40L;
    final Product product = new Product(1L, null, null, null, null, null, null, null);

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.addItemToWishlist(customerId, product)).thenThrow(new WishlistSupplier.TimedOutException());

    assertThat(wishlistService.addProductToWishlist(customerId, product)).isFalse();
  }

  @Test
  void returnsTrue_whenAddItemToWishlist_returnsTrue() throws Exception {
    final Long customerId = 40L;
    final Product product = new Product(1L, null, null, null, null, null, null, null);

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.addItemToWishlist(customerId, product)).thenReturn(true);

    assertThat(wishlistService.addProductToWishlist(customerId, product)).isTrue();
  }

  @Test
  void returnsFalse_whenAddItemToWishlist_returnsFalse() throws Exception {
    final Long customerId = 40L;
    final Product product = new Product(1L, null, null, null, null, null, null, null);

    final WishlistSupplier mockWishlistSupplier = mock(WishlistSupplier.class);
    WishlistService wishlistService = new WishlistService(mockWishlistSupplier);

    when(mockWishlistSupplier.addItemToWishlist(customerId, product)).thenReturn(false);

    assertThat(wishlistService.addProductToWishlist(customerId, product)).isFalse();
  }
}
