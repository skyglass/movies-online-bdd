package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@AutoConfigureTestDatabase
public class JdbcProductGroupRepositoryTest {
  @Autowired
  JdbcProductGroupRepository productGroupRepository;

  @Test
  public void findById_returns_productGroup() {
    long expectedId = 1L;
    ProductGroup productGroup = productGroupRepository.findById(expectedId);

    assertThat(productGroup).hasFieldOrPropertyWithValue("id", expectedId);
    assertThat(productGroup.getProducts()).hasSizeGreaterThan(3);
  }

  @Test
  public void update_canDeleteAll() {
    long productGroupId = 1L;
    final ProductGroup productGroup = productGroupRepository.findById(productGroupId);
    final String productGroupName = "new name";

    ProductGroup productGroupNoProducts = new ProductGroup(productGroupId, Collections.emptyList(), "");
    productGroupNoProducts.items = Collections.emptyList();
    productGroupRepository.update(productGroupNoProducts);

    ProductGroup savedProductGroup = productGroupRepository.findById(productGroupId);
    assertThat(savedProductGroup).hasFieldOrPropertyWithValue("id", productGroupId);
    assertThat(savedProductGroup.getProducts()).hasSize(0);
  }

  @Test
  public void update_persistsProductGroupName() {
    final String updatedName = "Updated_name";

    ProductGroup productGroup = productGroupRepository.first();
    productGroup.setName(updatedName);
    productGroup.setItems(Collections.emptyList());

    productGroupRepository.update(productGroup);

    assertThat(productGroupRepository.first()).hasFieldOrPropertyWithValue("name", updatedName);
  }

  @Test
  public void update_forKnownId_persistsProductGroupName() {
    final String updatedName = "Updated_name";
    final Long knownId = 1L;

    ProductGroup productGroup = productGroupRepository.findById(knownId);
    productGroup.setName(updatedName);
    productGroup.setItems(Collections.emptyList());

    productGroupRepository.update(productGroup);

    assertThat(productGroupRepository.findById(knownId)).hasFieldOrPropertyWithValue("name", updatedName);
  }
}
