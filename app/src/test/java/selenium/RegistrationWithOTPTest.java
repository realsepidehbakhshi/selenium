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
import java.time.Duration;

public class RegistrationWithOTPTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up WebDriver...");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("WebDriver initialized.");
    }

    @Test
    public void testRegistrationWithOTP() {

        System.out.println("Navigating to signup page...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/auth/signup");
        System.out.println("Page title: " + driver.getTitle());

        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("first_name")));
        firstNameInput.sendKeys("John");

        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("last_name")));
        lastNameInput.sendKeys("Doe");

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailInput.sendKeys("testuser_spd@example.com");

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create Account')]")));
        submitButton.click();
        System.out.println("Registration form submitted. Waiting for OTP page...");

        WebElement otpInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[autocomplete='one-time-code']")));
        otpInput.sendKeys("858585");

        WebElement otpSubmitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Verify Code')]")));
        otpSubmitButton.click();
        System.out.println("OTP submitted. Waiting for registration...");

        wait.until(ExpectedConditions.urlContains("/auth/login?newUser=1"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        if (currentUrl.contains("/auth/login?newUser=1")) {
            System.out.println("Successfully finished.");
        } else {
            System.out.println("Failed");
        }
    }

    @Test
    public void testRegistrationWithExistingEmail() {

        System.out.println("Navigating to signup page for existing email test...");
        driver.get("https://client-panel-git-main katastrofa-projects.vercel.app/auth/signup");
        System.out.println("Page title: " + driver.getTitle());

        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("first_name")));
        firstNameInput.sendKeys("Jane");

        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("last_name")));
        lastNameInput.sendKeys("Smith");

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailInput.sendKeys("existinguser@example.com");

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create Account')]")));
        submitButton.click();
        System.out.println("Registration form submitted with existing email...");

        try {
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(), 'This email is already in use.')]")));
            String errorText = errorMessage.getText();
            System.out.println("Error message found: " + errorText);

            if (errorText.contains("This email is already in use.")) {
                System.out.println("Test passed: Error message displayed for existing email.");
            } else {
                System.out.println("Test failed: Expected error message not found.");
            }
        } catch (Exception e) {
            System.out.println("Test failed: Error message element not found. Exception: " + e.getMessage());
        }
    }

    @Test
    public void testRegistrationWithInvalidOTP() {

        System.out.println("Navigating to signup page for invalid OTP test...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/auth/signup");
        System.out.println("Page title: " + driver.getTitle());

        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("first_name")));
        firstNameInput.sendKeys("Alice");

        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("last_name")));
        lastNameInput.sendKeys("Brown");

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailInput.sendKeys("newuser_otp@example.com");

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create Account')]")));
        submitButton.click();
        System.out.println("Registration form submitted. Waiting for OTP page...");

        WebElement otpInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[autocomplete='one-time-code']")));
        otpInput.sendKeys("999999");

        WebElement otpSubmitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Verify Code')]")));
        otpSubmitButton.click();
        System.out.println("Invalid OTP submitted. Checking for error message...");

        try {
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(), 'Invalid code')]")));
            String errorText = errorMessage.getText();
            System.out.println("Error message found: " + errorText);

            if (errorText.contains("Invalid code")) {
                System.out.println("Test passed: Error message displayed for invalid OTP.");
            } else {
                System.out.println("Test failed: Expected error message not found.");
            }
        } catch (Exception e) {
            System.out.println("Test failed: Error message element not found. Exception: " + e.getMessage());
        }
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