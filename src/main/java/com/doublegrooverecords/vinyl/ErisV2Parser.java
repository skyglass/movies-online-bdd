package com.doublegrooverecords.vinyl;

import com.eris.recommendation.Recommendation;

import java.math.BigDecimal;
import java.util.Map;

public class ErisV2Parser {
    public static Product parseProduct(Recommendation recommendation) {
        final Map<String, String> customFields = recommendation.getCustomFields();
        final BigDecimal cogs = new BigDecimal(customFields.get("cogs")).movePointLeft(2);
        final Artist artist = new Artist(customFields.get("artistName"));
        final Long publisherId = Long.valueOf(customFields.get("publisherId"));

        return new Product(recommendation.getId(), recommendation.getProductName(), artist, customFields.get("imageUrl"), recommendation.getPrice(), cogs, publisherId, null);
    }
}
