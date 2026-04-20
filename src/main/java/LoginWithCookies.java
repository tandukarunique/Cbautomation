import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginWithCookies {

    static final String TOKEN    = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWlkIjoiZTYzODIyZmEtZjhmYy00MTRjLThjZWItN2U4NTIxZWRlYmRiIiwiZW1haWwiOiJwbGFuQGdtYWlsLmNvbSIsImV4cCI6MTc3NjM5MTQ1MX0.8BBclq88oK5SgMOc3Nc0lD-YRhxqWiwdNLLR9XW50fQ";
    static final String EMAIL    = "plan@gmail.com";
    static final String BASE_URL = "https://dev.chatboq.com";

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--no-sandbox",
                             "--disable-dev-shm-usage", "--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.manage().window().maximize();

            // ── Step 1: Login ────────────────────────────────────────
            System.out.println("Step 1: Logging in...");
            driver.get(BASE_URL + "/login");
            Thread.sleep(2000);
            
            js.executeScript("localStorage.setItem('accessToken', arguments[0]);", TOKEN);
            js.executeScript("localStorage.setItem('rememberedEmail', arguments[0]);", EMAIL);
            driver.manage().addCookie(new Cookie.Builder("accessToken", TOKEN)
                .domain("dev.chatboq.com").path("/").isHttpOnly(true).build());
            driver.get(BASE_URL + "/dashboard");
            Thread.sleep(4000);
            
            System.out.println("Logged in! URL: " + driver.getCurrentUrl());

            // ── Step 2: Open workspace dropdown ─────────────────────
            System.out.println("Opening workspace dropdown...");
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space(text())='loading workspace']/..")
            ));
            js.executeScript("arguments[0].click();", dropdown);
            Thread.sleep(2500);
            System.out.println("Dropdown opened!");

            // ── Step 3: Click Stater plan Yearly via JavaScript ────────────────
            System.out.println("Selecting Stater plan Yearly...");
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[normalize-space(text())='Stater plan Yearly']")
            ));
            WebElement staterPlanYearly = driver.findElement(
                By.xpath("//*[normalize-space(text())='Stater plan Yearly']")
            );
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", staterPlanYearly);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", staterPlanYearly);
            Thread.sleep(2000);
            System.out.println("Stater plan Yearly selected!");
            
            // ── Step 4: Click Switch Organization ───────────────────
            System.out.println("Step 4: Clicking Switch Organization...");
            js.executeScript(
                "var btns = document.querySelectorAll('button');" +
                "for(var i=0; i<btns.length; i++){" +
                "  if(btns[i].textContent.includes('Switch') || " +
                "     btns[i].textContent.includes('Organization')){" +
                "    btns[i].click(); break;" +
                "  }" +
                "}"
            );
            Thread.sleep(5000);
            System.out.println("Done! Final URL: " + driver.getCurrentUrl());
            
            
//Convo click garne
         // ── Step 5: Select and open Demo chat ───────────────────
            System.out.println("Step 5: Selecting Demo chat...");

         // ── Step 5: Select and open Demo chat (Based on F12 HTML) ──
            System.out.println("Step 5: Selecting Demo chat...");

            Thread.sleep(3000);

            // Strategy 1: Find by button class pattern
            try {
                WebElement demoChat = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'font-outfit') and contains(@class, 'w-full')]//p[contains(text(), 'Demo')]/ancestor::button")
                ));
                js.executeScript("arguments[0].click();", demoChat);
                System.out.println("Demo chat selected using button class!");
            } catch (Exception e1) {
                
                // Strategy 2: Find by the paragraph text inside button
               
                    // Strategy 3: Find any button with text "Demo" in any child element
                    try {
                        WebElement demoChat = driver.findElement(
                            By.xpath("//button[contains(., 'Demo')]")
                        );
                        js.executeScript("arguments[0].click();", demoChat);
                        System.out.println("Demo chat selected using contains text!");
                    } catch (Exception e3) {
                        System.out.println("Could not find Demo chat");
                    }
                
            }

           
         // ── Step 6: Send 500 messages ────────────────────────────────
         // ── Step 6: Send 500 messages ────────────────────────────────
            System.out.println("Step 6: Sending 500 messages...");

            for (int i = 1; i <= 500; i++) {
                try {
                    // Find the Lexical editor
                    WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("[data-lexical-editor='true']")
                    ));

                    // Click to focus
                    editor.click();
                    Thread.sleep(300);

                    // Type the message like a real user
                    editor.sendKeys("Message number " + i);
                    Thread.sleep(300);

                    // Press Enter to send
                    editor.sendKeys(org.openqa.selenium.Keys.RETURN);

                    System.out.println("Sent message " + i + "/500");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    System.out.println("Error on message " + i + ": " + e.getMessage());
                    Thread.sleep(2000);
                }
            }

            System.out.println("All 500 messages sent!");
            
            Thread.sleep(3000);
            System.out.println("Chat opened! Current URL: " + driver.getCurrentUrl());

        } finally {
            Thread.sleep(3000);
           // driver.quit();
            System.out.println("Browser closed.");
        }
    }
}