package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductPageService {
    private final UserRepository userRepository;
    private final RecommendationProvider recommendationProvider;

    public ProductPageService(UserRepository userRepository, RecommendationProvider recommendationEngine) {
        this.userRepository = userRepository;
        this.recommendationProvider = recommendationEngine;
    }

    public ProductPage execute(long productId, long customerId) {
        ProductPage productPage = new ProductPage();

        List<Product> products = recommendationProvider.getRecommendedItems(productId, customerId);

        Set<Long> purchasedProductIds = getAllPurchasedProductIds(customerId);

        final List<Product> filteredProducts = products
                .stream()
                .filter(product -> !purchasedProductIds.contains(product.getId()))
                .filter(this::isCostInBounds)
                .collect(Collectors.toList());

        productPage.recommended = filteredProducts;

        return productPage;
    }

    private boolean isCostInBounds(Product product) {
        final BigDecimal costUpperbound = new BigDecimal("30");

        final BigDecimal productCost = product.getCost().divide(product.getPrice(), RoundingMode.CEILING).movePointRight(2);

        return productCost.compareTo(costUpperbound) <= 0;
    }

    private Set<Long> getAllPurchasedProductIds(long customerId) {
        return userRepository.getAllPurchasedProducts(customerId)
                .stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
    }
}
