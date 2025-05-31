package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class UserUploadProfilePicTest {
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
    public void testLogoutAfterLogin() {
        System.out.println("Navigating to login page...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/auth/login");

        String loginPageTitle = driver.getTitle();
        System.out.println("Login page title: " + loginPageTitle);

        System.out.println("Entering email: info@dortana.com");
        driver.findElement(By.id("email"))
                .sendKeys("info@dortana.com");

        System.out.println("Entering password: 11111111");
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
        String dashboardUrl = driver.getCurrentUrl();
        System.out.println("Dashboard URL: " + dashboardUrl);
        assertEquals("https://client-panel-git-main-dortana-projects.vercel.app/panel/dashboard",
                dashboardUrl,
                "URL does not match expected dashboard URL");

        System.out.println("Navigating to Profile page...");
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/panel/profile']")));
        profileLink.click();

        System.out.println("Waiting for profile page...");
        wait.until(ExpectedConditions.urlContains("/panel/profile"));
        String profileUrl = driver.getCurrentUrl();
        System.out.println("Profile URL: " + profileUrl);
        assertEquals("https://client-panel-git-main-dortana-projects.vercel.app/panel/profile",
                profileUrl,
                "URL does not match expected profile URL");

        System.out.println("Finding 'Upload New' button...");
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Upload New')]")));

        System.out.println("Triggering file upload...");
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@type='file']")));

        String filePath = "C:\\Users\\sepid\\Desktop\\profile.jpg";
        fileInput.sendKeys(filePath);

        uploadButton.click();

        System.out.println("Waiting for upload confirmation...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during upload wait: " + e.getMessage());
        }

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'successfully')]")));
        assertEquals(true, successMessage.isDisplayed(), "Upload success message not displayed");

        System.out.println("Profile picture upload test completed successfully.");
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