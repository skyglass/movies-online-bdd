package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
abstract class EndToEndTest {
    @LocalServerPort
    int port;

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    void givenUserOnHomePage() {
        driver.get(String.format("http://127.0.0.1:%d", port));
    }

    void givenUserOnAdminPage() {
        signIn();
        driver.get(String.format("http://127.0.0.1:%d/admin", port));
    }

    void signIn() {
        driver.get(String.format("http://127.0.0.1:%d/admin", port));
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("super");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.className("container"));
    }

    WebElement findByTestId(String testId) {
        return driver.findElement(By.cssSelector(String.format("[data-testid='%s']", testId)));
    }
}
