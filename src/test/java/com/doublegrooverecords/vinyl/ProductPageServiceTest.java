package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductPageServiceTest {
    @Test
    void returnsEmptyList_whenGetRecommendedItemsReturnsNoProducts() {
        final long productId = 1L;
        final long customerId = 2L;

        final RecommendationProvider recommendationProvider = mock(RecommendationProvider.class);
        final UserRepository userRepository = mock(UserRepository.class);

        when(recommendationProvider.getRecommendedItems(productId, customerId)).thenReturn(Collections.emptyList());

        final ProductPageService sut = new ProductPageService(userRepository, recommendationProvider);

        final ProductPage productPage = sut.execute(productId, customerId);

        assertThat(productPage.recommended).hasSize(0);
    }

    @Test
    void returnsAllProducts_whenGetRecommendedItemReturnsUnPurchasedProductsInCostRange() {
        final long productId = 1L;
        final long customerId = 2L;

        final RecommendationProvider recommendationProvider = mock(RecommendationProvider.class);
        final UserRepository userRepository = mock(UserRepository.class);

        final Product product1 = new Product(1L, null, null, null, new BigDecimal("10.00"), new BigDecimal("0.00"), 1L, null);
        final Product product2 = new Product(2L, null, null, null, new BigDecimal("10.00"), new BigDecimal("0.00"), 1L, null);

        when(recommendationProvider.getRecommendedItems(productId, customerId)).thenReturn(List.of(product1, product2));

        final ProductPageService sut = new ProductPageService(userRepository, recommendationProvider);

        final ProductPage productPage = sut.execute(productId, customerId);

        assertThat(productPage.recommended).hasSize(2);
    }

    @Test
    void returnsEmptyList_whenGetRecommendedItemReturnsPurchasedProducts() {
        final long productId = 1L;
        final long customerId = 2L;

        final RecommendationProvider recommendationProvider = mock(RecommendationProvider.class);
        final UserRepository userRepository = mock(UserRepository.class);

        final Product product1 = new Product(1L, null, null, null, new BigDecimal("1000").movePointLeft(2), new BigDecimal("300").movePointLeft(2), 1L, null);
        final Product product2 = new Product(2L, null, null, null, new BigDecimal("1000").movePointLeft(2), new BigDecimal("300").movePointLeft(2), 1L, null);
        final List<Product> products = List.of(product1, product2);

        when(recommendationProvider.getRecommendedItems(productId, customerId)).thenReturn(products);
        when(userRepository.getAllPurchasedProducts(customerId)).thenReturn(products);

        final ProductPageService sut = new ProductPageService(userRepository, recommendationProvider);

        final ProductPage productPage = sut.execute(productId, customerId);

        assertThat(productPage.recommended).hasSize(0);
    }

    @Test
    void returnsEmptyList_whenGetRecommendedItemReturnsUnPurchasedProducts_NOT_inCostRange() {
        final long productId = 1L;
        final long customerId = 2L;

        final RecommendationProvider recommendationProvider = mock(RecommendationProvider.class);
        final UserRepository userRepository = mock(UserRepository.class);

        final Product product1 = new Product(1L, null, null, null, new BigDecimal("1000").movePointLeft(2), new BigDecimal("310").movePointLeft(2), 1L, null);
        final Product product2 = new Product(2L, null, null, null, new BigDecimal("1000").movePointLeft(2), new BigDecimal("300").movePointLeft(2), 1L, null);

        when(recommendationProvider.getRecommendedItems(productId, customerId)).thenReturn(List.of(product1, product2));

        final ProductPageService sut = new ProductPageService(userRepository, recommendationProvider);

        final ProductPage productPage = sut.execute(productId, customerId);

        assertThat(productPage.recommended).hasSize(1);
    }
}