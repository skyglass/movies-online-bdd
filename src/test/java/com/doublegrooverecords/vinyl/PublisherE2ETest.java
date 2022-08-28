package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PublisherE2ETest extends EndToEndTest {

  private final Publisher initialPublisher = new Publisher(null, "Quality", "QR", "8565554255", "J. Mallard");
  private final Publisher updatedPublisher = new Publisher(null, "Updated Name", "UD", "Updated Contact", "2125554545");

  @Test
  public void adminEditsPublisherDetails() {
    givenUserOnAdminPage();
    whenTheyViewPublishers();
    thenTheySeeThePublishers();

    whenTheyViewDetailsOfAPublisher();
    thenTheySeeThePublisherDetails();

    whenTheyEditThePublisher();
    thenTheySeeTheUpdatedPublisherDetails();
  }

  private void thenTheySeeTheUpdatedPublisherDetails() {
    assertPublisherOnPage(WebElement::getText, updatedPublisher);
  }

  private void whenTheyEditThePublisher() {
    findByTestId("edit-publisher").click();

    assertPublisherOnPage((element) -> element.getAttribute("value"), initialPublisher);

    updatePublisher(updatedPublisher);

    findByTestId("save-button").click();
  }

  private void updatePublisher(Publisher updatedPublisher) {
    replaceValueInField("publisher-name", updatedPublisher.getName());
    replaceValueInField("publisher-short-name", updatedPublisher.getShortName());
    replaceValueInField("publisher-contact-name", updatedPublisher.getContactName());
    replaceValueInField("publisher-contact-number", updatedPublisher.getContactNumber());
  }

  private void replaceValueInField(String testId, String value) {
    WebElement field = findByTestId(testId);
    field.clear();
    field.sendKeys(value);
  }

  private void thenTheySeeThePublisherDetails() {
    assertPublisherOnPage(WebElement::getText, initialPublisher);
  }

  private void assertPublisherOnPage(Function<WebElement, String> dataForTestId, Publisher expectedPublisher) {
    assertThat(dataForTestId.apply(findByTestId("publisher-name"))).isEqualTo(expectedPublisher.getName());
    assertThat(dataForTestId.apply(findByTestId("publisher-short-name"))).isEqualTo(expectedPublisher.getShortName());
    assertThat(dataForTestId.apply(findByTestId("publisher-contact-name"))).isEqualTo(expectedPublisher.getContactName());
    assertThat(dataForTestId.apply(findByTestId("publisher-contact-number"))).isEqualTo(expectedPublisher.getContactNumber());
  }

  private void whenTheyViewDetailsOfAPublisher() {
    findByTestId("publisher-1-link").click();
  }

  private void whenTheyViewPublishers() {
    findByTestId("view-publishers").click();
  }

  private void thenTheySeeThePublishers() {
    List<WebElement> publishers = driver.findElements(By.className("publisher"));
    assertThat(publishers.size()).isGreaterThan(0);
  }
}
