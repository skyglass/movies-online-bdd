package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErisV2RecommendationProviderTest {
  @Test
  void throwsRuntimeException_whenTimeoutExceptionIsThrown() {
    final RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {
              final long timeOutCustomerId = 408L;

              final ErisV2RecommendationProvider sut = new ErisV2RecommendationProvider();

              sut.getRecommendedItems(1L, timeOutCustomerId);
            }
    );

    assertThat(exception).hasMessage("Eris v2 Timeout");
  }

  @Test
  void throwsRuntimeException_whenConnectionFails() {
    final RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {
              final long failedConnectionCustomerId = 500L;

              final ErisV2RecommendationProvider sut = new ErisV2RecommendationProvider();

              sut.getRecommendedItems(1L, failedConnectionCustomerId);
            }
    );

    assertThat(exception).hasMessage("Eris v2 Failed To Connect");
  }

  @Test
  void returnsEmptyList_whenRecommendationForReturnsEmptyList() {
    final long noRecommendationsCustomerId = 404L;

    final ErisV2RecommendationProvider sut = new ErisV2RecommendationProvider();

    final List<Product> recommendedItems = sut.getRecommendedItems(1L, noRecommendationsCustomerId);

    assertThat(recommendedItems).hasSize(0);
  }

  @Test
  void throwsRuntimeException_whenRecommendationAreMissingCustomFields() {
    final RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {
              final long noCustomFieldsCustomerId = 204L;

              final ErisV2RecommendationProvider sut = new ErisV2RecommendationProvider();

              sut.getRecommendedItems(1L, noCustomFieldsCustomerId);
            });

    assertThat(exception).hasMessage("Product missing custom fields for customer 204");
  }

  @Test
  void returnsProducts_whenRecommendationsAreReturned() {
    final long customerWithRecommendations = 2L;

    final ErisV2RecommendationProvider sut = new ErisV2RecommendationProvider();

    final List<Product> recommendedItems = sut.getRecommendedItems(1L, customerWithRecommendations);

    assertThat(recommendedItems).isNotEmpty();
  }
}