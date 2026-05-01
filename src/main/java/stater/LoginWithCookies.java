package stater;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import emailAutomate.Emailautomate;

// import Ticket.GuestCustomer;
// import Ticket.NormalCustomer;

public class LoginWithCookies {

    static final String EMAIL    = "uniquetandukar8645@gmail.com";
    static final String PASSWORD = "Tha chaina 098!";
    static final String BASE_URL = "https://dev.chatboq.com";
    static final String ORG_ID   = "b4cdf57d-a4b4-462b-aa9e-2ffc762fa82a";
    

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
            "--disable-notifications",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--remote-allow-origins=*"
        );

        WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // Emailautomate emailAutomate = null;
       
        try {
            driver.manage().window().maximize();

            // ── LOGIN (no CAPTCHA after first run) ─────────────────────────
            Loginpage loginPage = new Loginpage(driver);  
            boolean authLoaded = loginPage.loadSavedAuth();
            if (!authLoaded) {
                loginPage.manualLoginWithCaptcha(EMAIL, PASSWORD);
            }
            loginPage.gotoAuthenticated("/inbox", ".*\\/inbox.*");
            System.out.println("Login successful! URL: " + driver.getCurrentUrl());
            // ───────────────────────────────────────────────────────────────

            
            
            		// Org name click garera switch garne
                
            WebElement starterRow = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[text()='All']/ancestor::div[contains(@class,'rounded-md border')]")
            ));
            starterRow.findElement(By.xpath(".//section[text()='Switch']")).click();
            System.out.println("Switched to Starter! (Method 1)");
              
            
                    
                    
                    
                    
                    
                    
           /*         System.out.println("Clicking circle wala left side ko.....");
                    try {

                        String cssSelector = "div.bg-primary-color.flex.h-8.w-8.shrink-0.items-center.justify-center.rounded-full.font-medium.text-background";
                                    
                        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
                        WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
                        element.click();
                                                        
                    } catch (Exception e) {
                        System.err.println("Failed to click element: " + e.getMessage());
                    } 
             */      
                    
                    
                    
                    
                    
                    
                    // Switch org click
                    wait.until(ExpectedConditions.elementToBeClickable(
            		By.xpath("//section[text()='Switch Organization']")
                    		)).click();
                    System.out.println("Switch Organization clicked!");
            

                    
                    
                    

            /*
            WebElement ticketsLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class, 'truncate') and text()='Tickets']")
                ));
                ticketsLink.click();
                System.out.println(" Clicked on Tickets");
                Thread.sleep(1500);
            */


            
            // ── Step 4: Select and open Demo chat
            System.out.println("Step 5: Selecting Demo chat...");
            Thread.sleep(1000);

            try {
                WebElement demoChat = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space(text())='demo']")
                ));
                demoChat.click();
                System.out.println("Clicked Demo chat successfully!");
            } catch (Exception e) {
                // If that fails, try clicking the parent
                WebElement demoChat = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='demo']/parent::button")
                ));
                demoChat.click();
            }

            // ── Send "Demo" message
            int i=1;
            for(i=0;i<=100;i++) {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@contenteditable='true']")
            ));
            input.click();
            input.sendKeys("Msg gaieracha.....");
            Thread.sleep(500);
            input.sendKeys(Keys.ENTER);
            System.out.println("Demo message sent!");
            Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println("Error in main execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Thread.sleep(1000);
        
            
            System.out.println("Process completed. Browser can be closed manually.");
        }
        
    }
}