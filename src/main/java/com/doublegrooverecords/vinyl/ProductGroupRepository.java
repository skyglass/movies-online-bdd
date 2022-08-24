package com.doublegrooverecords.vinyl;

import java.util.List;

public interface ProductGroupRepository {
    void update(ProductGroup productGroup);

    ProductGroup findById(Long id);

    ProductGroup first();

    void delete(Long id);
}
