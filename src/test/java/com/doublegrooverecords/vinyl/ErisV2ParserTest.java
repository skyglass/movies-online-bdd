package com.doublegrooverecords.vinyl;

import com.eris.recommendation.Recommendation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ErisV2ParserTest {
  @Test
  void parsesProduct() {
    final String costInPennies = "1";

    final long expectedId = 1L;
    final String expectedAlbumName = "some product name";
    final String expectedPrice = "1000";
    final String expectedCost = "0.01";
    final String expectedImageUrl = "someImageUrl";
    final String expectedArtistName = "some artist";
    final long expectedPublisherId = 100L;

    final Recommendation recommendation = makeRecommendation(expectedId, expectedAlbumName, expectedPrice, expectedImageUrl, costInPennies, expectedArtistName, expectedPublisherId);

    final Product product = ErisV2Parser.parseProduct(recommendation);

    assertThat(product.getId()).isEqualTo(expectedId);
    assertThat(product.getPublisherId()).isEqualTo(expectedPublisherId);
    assertThat(product.getAlbumTitle()).isEqualTo(expectedAlbumName);
    assertThat(product.getPrice()).isEqualTo(expectedPrice);
    assertThat(product.getImageUrl()).isEqualTo(expectedImageUrl);
    assertThat(product.getCost()).isEqualTo(expectedCost);
    assertThat(product.getArtists())
            .extractingResultOf("getName")
            .containsExactly(expectedArtistName);
  }

  private Recommendation makeRecommendation(long id, String productName, String price, String imageUrl, String cost, String artistName, long publisherId) {
    final Recommendation recommendation = new Recommendation();
    recommendation.setPrice(new BigDecimal(price));
    recommendation.setId(id);
    recommendation.setProductName(productName);
    final Map<String, String> customFields = Map.of("artistName", artistName, "publisherId", String.valueOf(publisherId), "imageUrl", imageUrl, "cogs", cost);
    recommendation.setCustomFields(customFields);
    return recommendation;
  }
}
