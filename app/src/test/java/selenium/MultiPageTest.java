package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MultiPageTest {
    private WebDriver driver;

    private static final String[] PAGES = {
            "https://client-panel-git-main-dortana-projects.vercel.app/",
            "https://client-panel-git-main-dortana-projects.vercel.app/about-us",
            "https://client-panel-git-main-dortana-projects.vercel.app/contact-us"
    };

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,720");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testMultiplePagesLoadAndHaveTitle() {
        for (String url : PAGES) {
            System.out.println("Testing: " + url);
            driver.get(url);

            String title = driver.getTitle();
            System.out.println("Page title: " + title);

            assertNotNull(title, "Title should not be null for: " + url);
            assertFalse(title.trim().isEmpty(), "Title should not be empty for: " + url);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
