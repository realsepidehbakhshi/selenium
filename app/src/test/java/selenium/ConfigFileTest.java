package test.java.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import test.java.selenium.utils.ConfigLoader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigFileTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        boolean headless = ConfigLoader.getBoolean("headless");
        String userAgent = ConfigLoader.get("userAgent");

        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigLoader.getInt("timeout")));
    }

    @Test
    public void testPageLoadsUsingConfig() {
        String baseUrl = ConfigLoader.get("baseUrl");
        driver.get(baseUrl);
        String title = driver.getTitle();

        System.out.println("Page title: " + title);
        assertTrue(title.length() > 0, "Title should not be empty");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
