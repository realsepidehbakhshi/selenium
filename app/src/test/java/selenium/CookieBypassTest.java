package test.java.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CookieBypassTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testBypassConsentPopupWithCookie() {
        String url = "https://client-panel-git-main-dortana-projects.vercel.app";

        driver.get(url);

        Cookie consentCookie = new Cookie.Builder("cookie_consent", "true")
                .domain("client-panel-git-main-dortana-projects.vercel.app")
                .path("/")
                .isHttpOnly(false)
                .build();
        driver.manage().addCookie(consentCookie);

        driver.navigate().refresh();

        boolean isConsentBannerVisible = driver.getPageSource().contains("cookie")
                || driver.getPageSource().toLowerCase().contains("consent");

        assertFalse(isConsentBannerVisible, "Consent popup should not be visible after setting cookie.");
        System.out.println("Consent popup successfully bypassed using cookie.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
