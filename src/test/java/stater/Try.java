package stater;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Try {
    static final String EMAIL    = "uniquetandukar8645@gmail.com";
    static final String PASSWORD = "Tha chaina 098!";
    static final String DASHBOARD = "/dashboard";

    public static void main(String[] args) throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
            "--disable-notifications",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--remote-allow-origins=*"
        );

        ChromeDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();

            Loginpage loginPage = new Loginpage(driver);
            boolean authLoaded = loginPage.loadSavedAuth();
            
            if (!authLoaded) {
                loginPage.manualLoginWithCaptcha(EMAIL, PASSWORD);
            }

            loginPage.gotoAuthenticated(DASHBOARD, ".*\\/dashboard.*");
            System.out.println("✅ Dashboard reached!");
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'rounded-full') and contains(@class,'h-8')]")
            )).click();
            Thread.sleep(1000);

           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}