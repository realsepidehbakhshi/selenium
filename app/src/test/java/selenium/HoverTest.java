package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HoverTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("WebDriver initialized.");
    }

    @Test
    public void testHoverOverTradeMenu() {
        System.out.println("Navigating to home page...");
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app");

        WebElement tradeMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Trade')]")));

        String initialColor = tradeMenu.getCssValue("color");

        Actions actions = new Actions(driver);
        actions.moveToElement(tradeMenu).perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String hoverColor = tradeMenu.getCssValue("color");

        assertNotEquals(initialColor, hoverColor, "Text color should change on hover");

        System.out.println("Initial color: " + initialColor);
        System.out.println("Hover color: " + hoverColor);
        System.out.println("Hover test completed successfully");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Closing WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("WebDriver closed.");
    }
}