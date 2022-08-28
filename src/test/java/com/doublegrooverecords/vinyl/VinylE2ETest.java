package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VinylE2ETest extends EndToEndTest {
  private String productGroupName;

  @Test
  void adminEditsProductGroup() {
    givenUserOnHomePage();
    thenUserSeesProductGroup();

    givenUserOnAdminPage();
    whenTheyEditProductGroup();
    andTheyEditTheProductGroupName();
    andTheyAddProduct();
    thenTheySeeProductInProductGroup();

    whenTheySaveChanges();
    givenUserOnHomePage();
    thenTheySeeProductOnHomePage();
    andTheySeeTheProductGroupName();
  }

  private void andTheySeeTheProductGroupName() {
    assertThat(findByTestId("product-group-name").getText()).contains(productGroupName);
  }

  private void andTheyEditTheProductGroupName() {
    productGroupName = "Exciting Products";
    findByTestId("product-group-name").sendKeys(productGroupName);
  }

  private void thenTheySeeProductOnHomePage() {
    assertThat(driver.findElements(By.className("product"))).hasSize(getExpectedUIProducts().size() + 1);
  }

  private void whenTheySaveChanges() {
    driver.findElement(By.className("save-button")).click();
  }

  private void thenTheySeeProductInProductGroup() {
    List<WebElement> selectedItemElements = driver.findElements(By.name("items"));

    assertThat(selectedItemElements).hasSize(getExpectedUIProducts().size() + 1);
  }

  private void andTheyAddProduct() {
    driver.findElement(By.id("product-search")).sendKeys(getExpectedUIProducts().get(0).getArtist());
    driver.findElement(By.id("search-button")).click();
    driver.findElement(By.className("add-button")).click();
  }

  private void whenTheyEditProductGroup() {
    driver.findElement(By.id("edit-product-group-link")).click();
  }

  private void thenUserSeesProductGroup() {
    WebElement productGroupContainer = driver.findElement(By.className("product-group-container"));

    getExpectedUIProducts().forEach(expectedProduct -> {
      assertThat(productGroupContainer.getText()).containsIgnoringCase(expectedProduct.getAlbum());
      assertThat(productGroupContainer.getText()).containsIgnoringCase(expectedProduct.getArtist());
    });

    assertThat(driver.findElement(By.className("product-group-name")).getText()).isEqualTo("flash sale");
  }

  static private List<UIProduct> getExpectedUIProducts() {
    return List.of(
            new UIProduct("The Coworker", "Bobby Joel", defaultImageUrl()),
            new UIProduct("Downtown Boy", "Bobby Joel", defaultImageUrl()),
            new UIProduct("Hand Me Housing", "The stationary boulders", defaultImageUrl()),
            new UIProduct("aftergym", "The stationary boulders", defaultImageUrl())
    );
  }

  static private String defaultImageUrl() {
    return "https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960";
  }
}
