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
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

    WebElement findByTestId(String testId) {
        return driver.findElement(By.cssSelector(String.format("[data-testid='%s']", testId)));
    }
}
