package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

public class HistoryNavigationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("WebDriver initialized.");
    }

    @Test
    public void testHistoryNavigation() {

        System.out.println("Navigating to home page...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/");

        String homeUrl = driver.getCurrentUrl();
        System.out.println("Home page URL: " + homeUrl);
        assertEquals("https://client-panel-git-main-dortana-projects.vercel.app/",
                homeUrl,
                "URL does not match expected home page URL");

        System.out.println("Navigating to About Us page...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/about-us");

        System.out.println("Waiting for About Us page...");
        wait.until(ExpectedConditions.urlContains("/about-us"));
        String aboutUsUrl = driver.getCurrentUrl();
        System.out.println("About Us page URL: " + aboutUsUrl);
        assertEquals("https://client-panel-git-main-dortana-projects.vercel.app/about-us",
                aboutUsUrl,
                "URL does not match expected About Us page URL");

        System.out.println("Navigating back to home page...");
        driver.navigate().back();

        System.out.println("Waiting for home page...");
        wait.until(ExpectedConditions.urlToBe("https://client-panel-git-main-dortana-projects.vercel.app/"));
        String backToHomeUrl = driver.getCurrentUrl();
        System.out.println("Back to home page URL: " + backToHomeUrl);
        assertEquals("https://client-panel-git-main-dortana-projects.vercel.app/",
                backToHomeUrl,
                "URL does not match expected home page URL after navigating back");

        System.out.println("History navigation test completed successfully.");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("WebDriver closed.");
    }
}