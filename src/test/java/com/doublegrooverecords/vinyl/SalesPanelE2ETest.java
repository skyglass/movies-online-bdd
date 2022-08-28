package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesPanelE2ETest extends EndToEndTest {
  @Test
  public void displaysSalesPanel() {
    givenUserOnAdminPage();
    whenTheyVisitTheSalesPanel();
    thenTheySeeMostPurchasedProducts();
    thenTheySeeTheCurrentQuarter();
  }

  private void thenTheySeeTheCurrentQuarter() {
    assertThat(findByTestId("current-quarter").getText()).isIn("Q1", "Q2", "Q3", "Q4");
  }

  private void thenTheySeeMostPurchasedProducts() {
    final String[] expectedProductNames = {
            "Cold in The Sun",
            "Builder",
            "Downtown boy",
            "Candle stick maker street",
            "The coworker"
    };

    assertMostPurchasedProductsContain(expectedProductNames);
  }

  private void assertMostPurchasedProductsContain(String[] expectedProducts) {
    var elements = driver.findElements(By.className("most-purchased-product"));

    assertThat(elements)
            .extracting(WebElement::getText)
            .containsAnyOf(expectedProducts);

    assertThat(elements)
            .extracting(webElement -> webElement.getAttribute("class").replace("most-purchased-product", "").trim())
            .containsAnyOf("no-change", "increased", "decreased", "new-to-list");
  }

  private void whenTheyVisitTheSalesPanel() {
    findByTestId("sales-panel").click();
  }
}
