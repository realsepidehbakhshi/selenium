package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;

public class LoginPageTest {
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
    public void testLoginWithCorrectCredentials() {
        System.out.println("Navigating to login page for correct credentials test...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/auth/login");

        String pageTitle = driver.getTitle();
        System.out.println("Login page title: " + pageTitle);

        System.out.println("Entering correct email: info@dortana.com");
        driver.findElement(By.id("email"))
                .sendKeys("info@dortana.com");

        System.out.println("Entering correct password: 11111111");
        driver.findElement(By.id("password"))
                .sendKeys("11111111");

        System.out.println("Submitting login form...");
        driver.findElement(By.xpath("//button[text()='Continue']"))
                .click();

        System.out.println("Waiting 2 seconds after form submission...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during wait: " + e.getMessage());
        }

        System.out.println("Waiting for dashboard page...");
        wait.until(ExpectedConditions.urlContains("/panel/dashboard"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        assertEquals("https://client-panel-git-main-dortana-projects.vercel.app/panel/dashboard",
                currentUrl,
                "URL does not match expected dashboard URL");

        String postLoginTitle = driver.getTitle();
        System.out.println("Post-login page title: " + postLoginTitle);

        System.out.println("Correct credentials test completed successfully.");
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        System.out.println("Navigating to login page for incorrect credentials test...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/auth/login");

        String pageTitle = driver.getTitle();
        System.out.println("Login page title: " + pageTitle);

        System.out.println("Entering incorrect email: wrong@example.com");
        driver.findElement(By.id("email"))
                .sendKeys("info@dortana.com");

        System.out.println("Entering incorrect password: wrongpassword");
        driver.findElement(By.id("password"))
                .sendKeys("wrongpassword");

        System.out.println("Submitting login form...");
        driver.findElement(By.xpath("//button[text()='Continue']"))
                .click();

        System.out.println("Waiting 2 seconds after form submission...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during wait: " + e.getMessage());
        }

        System.out.println("Checking for error message...");
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[text()='Invalid login details']")));
            System.out.println("Error message displayed: " + errorMessage.getText());
            assertTrue(errorMessage.isDisplayed(), "Error message not displayed for incorrect credentials");
            assertEquals("Invalid login details", errorMessage.getText(),
                    "Error message does not match expected text 'Invalid login details'");
        } catch (Exception e) {
            System.out.println("No error message found: " + e.getMessage());
            assertTrue(false, "Expected error message 'Invalid login details' not found");
        }

        System.out.println("Incorrect credentials test completed successfully.");
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