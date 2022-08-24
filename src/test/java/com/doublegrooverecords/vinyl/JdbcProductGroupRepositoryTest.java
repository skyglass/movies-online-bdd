package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class JdbcProductGroupRepositoryTest {
    @Autowired
    JdbcProductGroupRepository productGroupRepository;

    @Test
    public void findById_returns_productGroup() {
        long expectedId = 1L;
        ProductGroup productGroup = productGroupRepository.findById(expectedId);

        assertThat(productGroup).hasFieldOrPropertyWithValue("id", expectedId);
        assertThat(productGroup.getProducts()).hasSize(4);
    }

    @Test
    public void update_canDeleteAll() {
        long productGroupId = 1L;
        final ProductGroup productGroup = productGroupRepository.findById(productGroupId);
        final String productGroupName = "new name";

        ProductGroup productGroupNoProducts = new ProductGroup(productGroupId, Collections.emptyList());
        productGroupNoProducts.items = Collections.emptyList();
        productGroupRepository.update(productGroupNoProducts);

        ProductGroup savedProductGroup = productGroupRepository.findById(productGroupId);
        assertThat(savedProductGroup).hasFieldOrPropertyWithValue("id", productGroupId);
        assertThat(savedProductGroup.getProducts()).hasSize(0);
    }
}
