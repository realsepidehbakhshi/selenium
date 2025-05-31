package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebDriverConfigTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up Chrome with custom options...");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,720");
        options.addArguments("--disable-notifications");

        options.addArguments("--user-agent=MyCustomUserAgent");

        driver = new ChromeDriver(options);
        System.out.println("WebDriver initialized with custom options.");
    }

    @Test
    public void testWindowSizeConfig() {
        driver.get("https://example.com");

        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        System.out.println("Window size: " + width + "x" + height);
        assertTrue(width >= 1270 && width <= 1290, "Width should be ~1280");
        assertTrue(height >= 710 && height <= 730, "Height should be ~720");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
