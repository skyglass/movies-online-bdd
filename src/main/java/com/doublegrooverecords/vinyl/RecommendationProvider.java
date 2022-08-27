package com.doublegrooverecords.vinyl;

import java.util.List;

public interface RecommendationProvider {
    List<Product> getRecommendedItems(long productId, long customerId);
}
