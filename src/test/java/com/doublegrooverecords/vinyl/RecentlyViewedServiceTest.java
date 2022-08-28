package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
class RecentlyViewedServiceTest {
  @Autowired
  RecentlyViewedService recentlyViewedService;

  @MockBean
  ProductViewRepository productViewRepository;

  @MockBean
  TimeWrapper timeWrapper;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;


  @Test
  void doesNOTReturnSameProductTwice() {
    final long customerId = 1L;

    when(timeWrapper.now()).thenReturn(ZonedDateTime.now());
    when(productViewRepository.mostRecentNViews(customerId, 5)).thenReturn(List.of(11L, 11L));

    final List<Product> actual = recentlyViewedService.recentlyViewed(customerId);

    assertThat(actual).hasSize(1)
            .map(Product::getId).containsExactly(11L);
  }

  @Test
  void returnsProductsBasedOnSetup() {
    final long customerId = 1313L;

    when(timeWrapper.now()).thenReturn(ZonedDateTime.now());
    when(productViewRepository.mostRecentNViews(customerId, 5)).thenReturn(List.of(11L, 2L));

    final List<Product> actual = recentlyViewedService.recentlyViewed(customerId);

    assertThat(actual).hasSize(2)
            .map(Product::getId).containsExactly(11L, 2L);
  }

  @Test
  void filtersProductsOrderedInLastThreeMonths() {
    final long customerId = 1313L;

    when(timeWrapper.now()).thenReturn(ZonedDateTime.now().plusMonths(3));
    orderRepository.create(makeOrder());
    when(productViewRepository.mostRecentNViews(customerId, 5)).thenReturn(List.of(11L, 13L, 2L, 1L));


    assertThat(recentlyViewedService.recentlyViewed(customerId)).hasSize(3);
  }

  @Test
  void doesNotFilterProductsMoreThanThreeMonthsAgo() {
    final long customerId = 1313L;

    when(timeWrapper.now()).thenReturn(ZonedDateTime.now().plusMonths(3).plusDays(1));
    orderRepository.create(makeOrder());
    when(productViewRepository.mostRecentNViews(customerId, 5)).thenReturn(List.of(11L, 13L, 2L, 1L));

    assertThat(recentlyViewedService.recentlyViewed(customerId)).hasSize(4);
  }

  private Order makeOrder() {
    final Order order = new Order();
    order.setCustomerId(1313L);
    order.setCity("Some city");
    order.setStateName("Some State");
    order.setShippingCost(new BigDecimal("1000"));
    order.setStreetAddress1("Some street address");
    order.setZip("some zip");
    order.setOrderId(22L);
    order.setProducts(List.of(productRepository.findById(1L)));
    return order;
  }
}