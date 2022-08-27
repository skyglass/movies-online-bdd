package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CouponDiscount {
    public void apply(Cart cart, Order order) {
        if (cart.getDiscount() == Discount.TEN_PERCENT) {
            List<Product> discountedProducts = getProductsWithDiscount(order.getProducts());
            order.setProducts(discountedProducts);
        }
    }

    private List<Product> getProductsWithDiscount(List<Product> products) {
        return products
                .stream()
                .map(this::applyDiscountToProduct)
                .collect(Collectors.toList());
    }

    private Product applyDiscountToProduct(Product product) {
        return new Product(
                product.id,
                product.albumTitle,
                product.getArtists().get(0),
                product.getImageUrl(),
                Discount.TEN_PERCENT.apply(product.getPrice()),
                null,
                null,
                null
        );
    }
}
