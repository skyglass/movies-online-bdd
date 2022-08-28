package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
  @Test
  public void returnsZero_whenNoDiscount_andNoProducts() {
    final ArrayList<CartProduct> emptyProductsList = new ArrayList<>();

    Cart cart = new Cart(emptyProductsList, Discount.NONE);

    assertThat(cart.getTotalWithDiscount()).isEqualTo(new BigDecimal(0));
  }

  @Test
  public void returnsTen_whenNoDiscount_andOneProduct() {
    final List<CartProduct> emptyProductsList = List.of(testProduct());

    Cart cart = new Cart(emptyProductsList, Discount.NONE);

    assertThat(cart.getTotalWithDiscount()).isEqualTo(new BigDecimal("10.00"));
  }

  @Test
  public void returnsNine_whenDiscountIsTen_andOneProduct() {
    final List<CartProduct> emptyProductsList = List.of(testProduct());

    Cart cart = new Cart(emptyProductsList, Discount.TEN_PERCENT);

    assertThat(cart.getTotalWithDiscount()).isEqualTo(new BigDecimal("9.00"));
  }

  @Test
  public void returnsEighteen_whenDiscountIsTen_andTwoProducts() {
    final List<CartProduct> emptyProductsList = List.of(testProduct(), testProduct());

    Cart cart = new Cart(emptyProductsList, Discount.TEN_PERCENT);

    assertThat(cart.getTotalWithDiscount()).isEqualTo(new BigDecimal("18.00"));
  }

  @Test
  public void returnsRoundedUpValue_whenDiscountIsTen_andOneProductWithCentsInPrice() {
    final List<CartProduct> emptyProductsList = List.of(productWithCentsInPrice());

    Cart cart = new Cart(emptyProductsList, Discount.TEN_PERCENT);

    assertThat(cart.getTotalWithDiscount()).isEqualTo(new BigDecimal("11.89"));
  }

  private CartProduct productWithCentsInPrice() {
    CartProduct cartProduct = new CartProduct(1L, "Subway Entrance Thing", new Artist("Bobby Joel"), "someImageUrl", new BigDecimal("13.21"));
    cartProduct.setQuantity(1L);
    return cartProduct;
  }

  private CartProduct testProduct() {
    CartProduct cartProduct = new CartProduct(1L, "Subway Entrance Thing", new Artist("Bobby Joel"), "someImageUrl", new BigDecimal("10.00"));
    cartProduct.setQuantity(1L);
    return cartProduct;
  }
}