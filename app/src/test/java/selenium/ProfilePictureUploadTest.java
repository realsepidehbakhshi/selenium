import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePictureUploadTest {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.get("https://client-panel-git-main-dortana-projects.vercel.app/panel/profile");

            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='file'][accept='image/*']")));

            String filePath = "C:\\Users\\sepid\\Pictures\\test-image.jpg";

            fileInput.sendKeys(filePath);

            Thread.sleep(3000);

            System.out.println("✅ Profile picture uploaded successfully!");

        } catch (Exception e) {
            System.out.println("❌ Failed to upload profile picture: " + e.getMessage());
        } finally {

            driver.quit();
        }
    }
}
