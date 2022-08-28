package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
class OrderRepositoryTest {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;

  @Test
  void testOrder() {
    final Order order = new Order();
    order.setCustomerId(1313L);
    order.setCity("Some city");
    order.setStateName("Some State");
    order.setShippingCost(new BigDecimal("1000"));
    order.setStreetAddress1("Some street address");
    order.setZip("some zip");
    order.setOrderId(22L);
    order.setProducts(List.of(productRepository.findById(13L)));
    orderRepository.create(order);

    final Order actual = orderRepository.findByCustomerId(order.getCustomerId());
    assertThat(actual.getTotal()).isEqualTo("43.30");
    assertThat(actual.getOrderId()).isNotNull();
    assertThat(actual.getCity()).isEqualTo("Some city");
  }

  @Test
  void testTwoProducts() {
    final Order order = new Order();
    order.setCustomerId(1313L);
    order.setCity("Some city");
    order.setStateName("Some State");
    order.setShippingCost(new BigDecimal("1000"));
    order.setStreetAddress1("Some street address");
    order.setZip("some zip");
    order.setOrderId(22L);
    order.setProducts(List.of(productRepository.findById(13L)));
    orderRepository.create(order);

    final Order actual = orderRepository.findByCustomerId(order.getCustomerId());
    assertThat(actual.getTotal()).isEqualTo("43.30");
    assertThat(actual.getOrderId()).isNotNull();
    assertThat(actual.getCity()).isEqualTo("Some city");
  }
}