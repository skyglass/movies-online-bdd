package com.doublegrooverecords.vinyl;

import com.eris.recommendation.ErisFailedConnectionException;
import com.eris.recommendation.ErisTimeoutException;
import com.eris.recommendation.RecommendationClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ErisV2RecommendationProvider implements RecommendationProvider {
    @Override
    public List<Product> getRecommendedItems(long productId, long customerId) {
        final RecommendationClient recommendationClient = new RecommendationClient();
        try {
            return recommendationClient.recommendationFor(customerId, productId)
                    .stream().map(ErisV2Parser::parseProduct)
                    .collect(Collectors.toList());

        } catch (NullPointerException e) {
            throw new RuntimeException("Product missing custom fields for customer " + customerId);
        } catch (ErisTimeoutException e) {
            throw new RuntimeException("Eris v2 Timeout");
        } catch (ErisFailedConnectionException e) {
            throw new RuntimeException("Eris v2 Failed To Connect");
        }
    }
}
