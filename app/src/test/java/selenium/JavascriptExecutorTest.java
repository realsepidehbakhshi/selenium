package test.java.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavascriptExecutorTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testScrollAndGetPageTitleUsingJS() {
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        String pageTitle = (String) js.executeScript("return document.title;");
        System.out.println("Page title retrieved via JS: " + pageTitle);

        assertTrue(pageTitle != null && !pageTitle.isEmpty(), "Page title should not be empty");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
