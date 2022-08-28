package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SalesReportTest {
  @Test
  public void returnsNegativeOne() throws Exception {
    // arrange
    Method findIndexOf = QuarterlySalesReport.class.getDeclaredMethod("findIndexOf", ArrayList.class, Product.class);
    findIndexOf.setAccessible(true);

    QuarterlySalesReport sut = new QuarterlySalesReport(1, null, null, null, null);

    ArrayList<Pair<Long, BigDecimal>> pairs = new ArrayList<>();

    // act
    int result = (Integer) findIndexOf.invoke(sut, pairs, null);

    // assert
    assertThat(result).isEqualTo(-1);
  }

  @Test
  public void returnsZero() throws Exception {
    // arrange
    long id = 1L;
    Product p = new Product(-1L, null, null, null, null, null, id, null);
    Method findIndexOf = QuarterlySalesReport.class.getDeclaredMethod("findIndexOf", ArrayList.class, Product.class);
    findIndexOf.setAccessible(true);

    QuarterlySalesReport sut = new QuarterlySalesReport(1, null, null, null, null);

    ArrayList<Pair<Long, BigDecimal>> pairs = new ArrayList<>();
    pairs.add(Pair.of(id, new BigDecimal("0")));

    // act
    int result = (Integer) findIndexOf.invoke(sut, pairs, p);

    // assert
    assertThat(result).isEqualTo(0);
  }

  @Nested
  @DisplayName("getMostPurchased")
  class getMostPurchased {

    @Test
    public void listIsEmpty_whenThereAreNoOrders() {
      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, Collections.emptyList());

      assertThat(salesReport.getMostPurchased()).isEmpty();
    }

    @Test
    public void listHasOneItem_whenThereIsOneItemInOneOrder() {
      Product expectedProduct = new Product(1L, null, null, null, null, null, null, null);

      Order orderWithOnePurchase = new Order();
      orderWithOnePurchase.setProducts(List.of(expectedProduct));
      List<Order> oneOrder = List.of(orderWithOnePurchase);

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, oneOrder);

      assertThat(salesReport.getMostPurchased())
              .hasSize(1)
              .containsExactly(expectedProduct);
    }

    @Test
    public void productsAreInCountOrder_whenOneOrderContainsSeveralProducts() {
      Product productOne = new Product(1L, null, null, null, null, null, null, null);
      Product productTwo = new Product(2L, null, null, null, null, null, null, null);

      Order orderWithThreePurchases = new Order();
      orderWithThreePurchases.setProducts(List.of(productOne, productTwo, productTwo));

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, List.of(orderWithThreePurchases));

      assertThat(salesReport.getMostPurchased())
              .hasSize(2)
              .containsExactly(productTwo, productOne);
    }

    @Test
    public void productsAreInCountOrder_whenOneOrderContainsSeveralProductsWithAnotherPurchaseCount() {
      Product productOne = new Product(1L, null, null, null, null, null, null, null);
      Product productTwo = new Product(2L, null, null, null, null, null, null, null);

      Order orderWithThreePurchases = new Order();
      orderWithThreePurchases.setProducts(List.of(productOne, productOne, productTwo));

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, List.of(orderWithThreePurchases));

      assertThat(salesReport.getMostPurchased())
              .hasSize(2)
              .containsExactly(productOne, productTwo);
    }

    @Test
    public void listHasOneProduct_whenEachOrderContainsSameProduct() {
      Product productOne = new Product(1L, null, null, null, null, null, null, null);

      Order orderOne = new Order();
      orderOne.setProducts(List.of(productOne));

      Order orderTwo = new Order();
      orderTwo.setProducts(List.of(productOne));

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, List.of(orderOne, orderTwo));

      assertThat(salesReport.getMostPurchased())
              .hasSize(1)
              .containsExactly(productOne);
    }

    @Test
    public void purchasesOrderedByCount_whenTwoOrdersContainDifferentPurchases() {
      Product productOne = new Product(1L, null, null, null, null, null, null, null);
      Product productTwo = new Product(2L, null, null, null, null, null, null, null);

      Order orderWithProductOneOnce = new Order();
      orderWithProductOneOnce.setProducts(List.of(productOne));

      Order orderWithProductTwoTwice = new Order();
      orderWithProductTwoTwice.setProducts(List.of(productTwo, productTwo));

      List<Order> orders = List.of(orderWithProductOneOnce, orderWithProductTwoTwice);

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, orders);

      assertThat(salesReport.getMostPurchased())
              .hasSize(2)
              .containsExactly(productTwo, productOne);
    }

    @Test
    public void purchasesOrderedByCount_whenTwoOrdersContainDifferentPurchasesWithOppositePurchaseCount() {
      Product productOne = new Product(1L, null, null, null, null, null, null, null);
      Product productTwo = new Product(2L, null, null, null, null, null, null, null);

      Order orderWithProductOneTwice = new Order();
      orderWithProductOneTwice.setProducts(List.of(productOne, productOne));

      Order orderWithProductTwoOnce = new Order();
      orderWithProductTwoOnce.setProducts(List.of(productTwo));

      List<Order> orders = List.of(orderWithProductOneTwice, orderWithProductTwoOnce);

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, orders);

      assertThat(salesReport.getMostPurchased())
              .hasSize(2)
              .containsExactly(productOne, productTwo);
    }

    @Test
    public void productsAreInOrder_whenProductIsPurchasedAcrossMultipleOrders() {
      Product productOne = new Product(1L, null, null, null, null, null, null, null);
      Product productTwo = new Product(2L, null, null, null, null, null, null, null);

      Order orderOne = new Order();
      orderOne.setProducts(List.of(productOne));

      Order orderTwo = new Order();
      orderTwo.setProducts(List.of(productOne, productTwo));

      QuarterlySalesReport salesReport = new QuarterlySalesReport(0, null, null, null, List.of(orderOne, orderTwo));

      assertThat(salesReport.getMostPurchased())
              .hasSize(2)
              .containsExactly(productOne, productTwo);
    }
  }

  @Test
  public void mostPurchased() throws Exception {
    // arrange
    List<Order> orders = new ArrayList<>();
    Order o = new Order();
    Product aProduct = new Product(1L, null, null, null, null, null, null, null);
    o.setProducts(List.of(aProduct));
    orders.add(o);
    Order o2 = new Order();
    o2.setProducts(List.of(aProduct));
    orders.add(o2);

    QuarterlySalesReport sut = new QuarterlySalesReport(2, null, null, null, orders);

    // act
    List<Product> mostPurchased = sut.getMostPurchased();

    // assert
    assertThat(mostPurchased).isEqualTo(List.of(aProduct));
  }

  @Test
  public void getProfit() throws Exception {
    // arrange
    List<Order> orders = new ArrayList<>();
    Order o = new Order();
    Product aProduct = new Product(1L, null, null, null, new BigDecimal("10"), new BigDecimal("2"), null, null);
    o.setProducts(List.of(aProduct));
    o.setTotal(new BigDecimal("10"));
    orders.add(o);

    QuarterlySalesReport sut = new QuarterlySalesReport(2, null, null, null, orders);

    // act
    BigDecimal profit = sut.getProfit();

    // assert
    assertThat(profit).isEqualTo(new BigDecimal("10").subtract(new BigDecimal("2")));
  }

  @Test
  public void sortProducts() throws Exception {
    // arrange
    Product first = new Product(1L, null, null, null, new BigDecimal("10"), new BigDecimal("2"), null, null);
    Product second = new Product(2L, null, null, null, new BigDecimal("10"), new BigDecimal("2"), null, null);
    Order o = new Order();
    o.setProducts(List.of(second, first));
    Method sort = QuarterlySalesReport.class.getDeclaredMethod("sortProducts", Map.class);
    sort.setAccessible(true);

    // act
    QuarterlySalesReport sut = new QuarterlySalesReport(2, null, null, null, List.of(o));
    Map<Product, Integer> args = Map.of(second, 1, first, 2);
    List<Product> result = (List<Product>) sort.invoke(sut, args);

    // assert
    assertThat(result).hasSize(2);
    assertThat(result.get(0)).isEqualTo(first);
    assertThat(result.get(1)).isEqualTo(second);
  }

  @Test
  public void getAmountOwedToManufacturers() throws Exception {
    // arrange
    BigDecimal cost = new BigDecimal("2");
    Product first = new Product(1L, null, null, null, new BigDecimal("10"), cost, 1L, null);
    Product second = new Product(2L, null, null, null, new BigDecimal("10"), cost, 1L, null);
    Order o = new Order();
    o.setProducts(List.of(second, first));
    Method getAmountOwedToManufacturers = QuarterlySalesReport.class.getDeclaredMethod("getAmountOwedToManufacturers");
    getAmountOwedToManufacturers.setAccessible(true);

    // act
    QuarterlySalesReport sut = new QuarterlySalesReport(2, null, null, null, List.of(o));
    Map<Product, Integer> args = Map.of(second, 1, first, 2);
    List<Pair<Long, BigDecimal>> result = (List<Pair<Long, BigDecimal>>) getAmountOwedToManufacturers.invoke(sut);

    // assert
    assertThat(result).hasSize(1);
    Pair<Long, BigDecimal> actual = result.get(0);
    assertThat(actual).hasFieldOrPropertyWithValue("first", 1L);
    assertThat(actual).hasFieldOrPropertyWithValue("second", cost.add(cost));
  }
}
