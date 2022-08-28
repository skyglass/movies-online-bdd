package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {RestTemplateWishlistSupplier.class, ProductRepository.class, RestTemplate.class})
@AutoConfigureStubRunner(ids = {"com.doublegrooverecords:double-groove-services:+:stubs:9296"},
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH)
public class RestTemplateWishlistSupplierTest {

  @Autowired
  private RestTemplateWishlistSupplier restTemplateWishlistSupplier;

  @MockBean
  private ProductRepository productRepository;

  @Test
  void returnsEmptyList_whenCustomerHasNoProducts() throws Exception {
    final Long customerWithNoProducts = 204L;

    assertThat(restTemplateWishlistSupplier.fetchAllItemsForUser(customerWithNoProducts)).isEmpty();
  }

  @Test
  void returnsSingleProduct_whenOneIsAdded() throws Exception {
    final Long customerId = 44L;
    final long productId = 1616L;

    final Product wishlistProduct = new Product(productId, null, null, null, null, null, null, null);
    final Product expectedProduct = createExpectedProduct(productId);

    when(productRepository.findById(productId)).thenReturn(expectedProduct);

    // make sure the list is empty to start
    restTemplateWishlistSupplier.clearAll(customerId);

    final boolean status = restTemplateWishlistSupplier.addItemToWishlist(customerId, wishlistProduct);
    assertThat(status).isTrue();

    final List<Product> actual = restTemplateWishlistSupplier.fetchAllItemsForUser(customerId);

    assertThat(actual).hasSize(1);

    final Product actualProduct = actual.get(0);
    assertProduct(actualProduct, expectedProduct);
  }

  private Product createExpectedProduct(long productId) {
    return new Product(
            productId,
            "some title",
            new Artist("some artist"),
            "some image",
            new BigDecimal("100.00"),
            new BigDecimal("5.00"),
            5L,
            "some description"
    );
  }

  @Test
  void returnsSingleProduct_whenOneIsAddedTwice() throws Exception {
    final Long customerId = 62L;
    final long productId = 31L;

    final Product wishlistProduct = new Product(productId, null, null, null, null, null, null, null);
    final Product expectedProduct = createExpectedProduct(productId);

    when(productRepository.findById(productId)).thenReturn(expectedProduct);

    // make sure the list is empty to start
    restTemplateWishlistSupplier.clearAll(customerId);

    restTemplateWishlistSupplier.addItemToWishlist(customerId, wishlistProduct);
    final boolean status = restTemplateWishlistSupplier.addItemToWishlist(customerId, wishlistProduct);
    assertThat(status).isFalse();

    final List<Product> actual = restTemplateWishlistSupplier.fetchAllItemsForUser(customerId);

    assertThat(actual).hasSize(1);

    final Product actualProduct = actual.get(0);
    assertProduct(actualProduct, expectedProduct);
  }

  private void assertProduct(Product actualProduct, Product expectedProduct) {
    assertThat(actualProduct.getId()).isEqualTo(expectedProduct.getId());
    assertThat(actualProduct.getPublisherId()).isEqualTo(expectedProduct.getPublisherId());
    assertThat(actualProduct.getAlbumTitle()).isEqualTo(expectedProduct.getAlbumTitle());
    assertThat(actualProduct.getArtists()).isEqualTo(expectedProduct.getArtists());
    assertThat(actualProduct.getImageUrl()).isEqualTo(expectedProduct.getImageUrl());
    assertThat(actualProduct.getPrice()).isEqualTo(expectedProduct.getPrice());
    assertThat(actualProduct.getCost()).isEqualTo(expectedProduct.getCost());
    assertThat(actualProduct.getDescription()).isEqualTo(expectedProduct.getDescription());
  }

  @Test
  void returnsProducts_whenTwoAreAdded() throws Exception {
    final Long customerId = 33L;
    final Product product1 = new Product(111L, null, null, null, null, null, null, null);
    final Product product2 = new Product(202L, null, null, null, null, null, null, null);

    Product expectedProduct1 = createExpectedProduct(111L);
    Product expectedProduct2 = createExpectedProduct(202L);

    when(productRepository.findById(product1.id)).thenReturn(expectedProduct1);
    when(productRepository.findById(product2.id)).thenReturn(expectedProduct2);

    // make sure the list is empty to start
    restTemplateWishlistSupplier.clearAll(customerId);

    restTemplateWishlistSupplier.addItemToWishlist(customerId, product1);
    restTemplateWishlistSupplier.addItemToWishlist(customerId, product2);

    final List<Product> actual = restTemplateWishlistSupplier.fetchAllItemsForUser(customerId);

    assertThat(actual).hasSize(2);

    assertProduct(actual.get(0), expectedProduct1);
    assertProduct(actual.get(1), expectedProduct2);
  }

  @Test
  void returnsEmptyListForCustomer2_whenTheyHaveAnEmptyWishlist() throws Exception {
    final Long firstCustomerId = 1L;
    final Long secondCustomerId = 2L;

    final Product product = new Product(10L, null, null, null, null, null, null, null);

    // make sure the list is empty to start
    restTemplateWishlistSupplier.clearAll(firstCustomerId);
    restTemplateWishlistSupplier.clearAll(secondCustomerId);

    restTemplateWishlistSupplier.addItemToWishlist(firstCustomerId, product);

    final List<Product> secondCustomerWishlist = restTemplateWishlistSupplier.fetchAllItemsForUser(secondCustomerId);

    assertThat(secondCustomerWishlist).isEmpty();
  }

  @Test
  void returnsUpdatedWishlistForSecondCustomer_whenTheirWishlistIsAltered() throws Exception {
    final Long firstCustomerId = 11L;
    final Long secondCustomerId = 22L;

    final Product product = new Product(33L, null, null, null, null, null, null, null);

    final Product expectedProduct = createExpectedProduct(product.id);

    when(productRepository.findById(product.id)).thenReturn(expectedProduct);

    // make sure the list is empty to start
    restTemplateWishlistSupplier.clearAll(firstCustomerId);
    restTemplateWishlistSupplier.clearAll(secondCustomerId);

    final boolean success = restTemplateWishlistSupplier.addItemToWishlist(secondCustomerId, product);
    assertThat(success).isTrue();

    assertThat(restTemplateWishlistSupplier.fetchAllItemsForUser(firstCustomerId)).isEmpty();

    final List<Product> actual = restTemplateWishlistSupplier.fetchAllItemsForUser(secondCustomerId);

    assertThat(actual).hasSize(1);
    assertProduct(actual.get(0), expectedProduct);
  }
}
