package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VinylE2ETest {

    @LocalServerPort
    int port;

    WebDriver driver;

    @Autowired
    JdbcProductGroupRepository productGroupRepository;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));

        long expectedId = 1L;
        ProductGroup productGroup = productGroupRepository.findById(expectedId);

        assertThat(productGroup).hasFieldOrPropertyWithValue("id", expectedId);
        assertThat(productGroup.getProducts()).hasSize(4);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    void adminEditsProductGroup() {
        givenUserOnHomePage();
        thenUserSeesProductGroup();

        givenUserOnAdminPage();
        whenTheyEditProductGroup();
        andTheyAddProduct();
        thenTheySeeProductInProductGroup();

        whenTheySaveChanges();
        givenUserOnHomePage();
        thenTheySeeProductOnHomePage();
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

    private void givenUserOnAdminPage() {
        driver.get(String.format("http://127.0.0.1:%d/admin", port));
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

    private void givenUserOnHomePage() {
        driver.get(String.format("http://127.0.0.1:%d", port));
    }

    static private String defaultImageUrl() {
        return "https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960";
    }
}
