package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import java.time.Duration;

public class FormWithInputsTest {
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
    public void testFormSubmission() {

        System.out.println("Navigating to form page...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/form");

        System.out.println("Page title: " + driver.getTitle());

        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("name-input")));
        nameInput.sendKeys("John Doe");

        WebElement genderMaleRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("gender-male")));
        genderMaleRadio.click();

        WebElement newsletterCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("newsletter")));
        newsletterCheckbox.click();

        WebElement countrySelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
        Select countryDropdown = new Select(countrySelect);
        countryDropdown.selectByValue("usa");

        WebElement bioTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("bio")));
        bioTextarea.sendKeys("This is a test bio for the form submission.");

        WebElement fileUpload = wait.until(ExpectedConditions.elementToBeClickable(By.id("file-upload")));
        fileUpload.sendKeys("C:\\Users\\sepid\\Desktop\\data.txt");

        WebElement submitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitButton.click();

        System.out.println("Waiting for alert...");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);

        if (alertText.contains("Form submitted successfully!")) {
            alert.accept();
            System.out.println("Alert accepted successfully.");
        } else {
            System.out.println("Alert did not contain expected message.");
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
