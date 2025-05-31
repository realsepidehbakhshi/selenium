import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testAboutUsPageLoad() {
        driver.get("https://client-panel-git-main-dortana-projects.vercel.app/about-us");
        String pageTitle = driver.getTitle();
        System.out.println("Page title is: " + pageTitle);
        assertEquals("RovixPro", pageTitle, "Page title does not match expected value 'RovixPro'");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}