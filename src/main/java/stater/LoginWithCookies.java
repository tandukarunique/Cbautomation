package stater;

import org.openqa.selenium.By;
import emailAutomate.Emailautomate;
import org.openqa.selenium.Cookie;
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
import java.util.List;

import Ticket.GuestCustomer;
import Ticket.NormalCustomer;

public class LoginWithCookies {

    static final String TOKEN    = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWlkIjoiNGZjMWYxZGYtY2E1Yy00YzEwLWFjMzYtYjA1YjNkZTdkNmVhIiwiZW1haWwiOiJ1bmlxdWV0YW5kdWthcjg2NDVAZ21haWwuY29tIiwiZXhwIjoxNzc3MTc1NjA3fQ.7mChgpL4lF5_tkz6eMEPt_vZNFW3N2n6TnKa6zenR4Y";
    static final String EMAIL    = "uniquetandukar8645@gmail.com";
    static final String BASE_URL = "https://dev.chatboq.com";

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--no-sandbox",
                             "--disable-dev-shm-usage", "--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Create first email automation instance (normal mode)
   //    Emailautomate emailAutomate = new Emailautomate(false); // false = normal mode

        int totalTickets = 100;
        int successCount = 0;
        int failCount    = 0;

        try {
            driver.manage().window().maximize();

            // Step 1: Login
            System.out.println("Step 1: Logging in...");
            driver.get(BASE_URL + "/login");
            Thread.sleep(1000);

            js.executeScript("localStorage.setItem('accessToken', arguments[0]);", TOKEN);
            js.executeScript("localStorage.setItem('rememberedEmail', arguments[0]);", EMAIL);
            driver.manage().addCookie(new Cookie.Builder("accessToken", TOKEN)
                .domain("dev.chatboq.com").path("/").isHttpOnly(true).build());
            driver.get(BASE_URL + "/4758a134-3b32-4308-a5d4-fd3a3aa7f1ed/inbox");
            Thread.sleep(2000);

            System.out.println("Logged in! URL: " + driver.getCurrentUrl());

            
            // Step 2: Click on profile/workspace icon to open sidebar menu
            System.out.println("Clicking circle wala left side ko.....");
            try {

                String cssSelector = "div.bg-primary-color.flex.h-8.w-8.shrink-0.items-center.justify-center.rounded-full.font-medium.text-background";
                            
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
                element.click();
                                                
            } catch (Exception e) {
                System.err.println("Failed to click element: " + e.getMessage());
            } 
           
        

   
            //Step 3: Click on org name
            
            try {
            	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            	wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='All']"))).click();
            }
            catch(Exception e) {
            	System.out.println("Org name click bhayena...");
            }
     
            
            Thread.sleep(2000);
            
            
            
            
            
            
            
            
            //Step:4 Org switch ma click garne....
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
           
            System.out.println("Switch Organization clicked!");

            
          /*  
            WebElement ticketsLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class, 'truncate') and text()='Tickets']")
                ));
                ticketsLink.click();
                System.out.println(" Clicked on Tickets");
                Thread.sleep(1500);
                

                */
                
                
            Thread.sleep(2000);
            
         //   WebElement closeButton = driver.findElement(By.xpath("//button[@data-slot='dialog-close']//ancestor-or-self::button"));
           // closeButton.click();
            
            

            
            /*
            // ── Step 4: Select and open Demo chat
            System.out.println("Step 5: Selecting Demo chat...");
            Thread.sleep(1000);

            try {
                WebElement demoChat = driver.findElement(
                    By.xpath("//button[contains(., 'Demo')]")
                );
                js.executeScript("arguments[0].click();", demoChat);
                System.out.println("Demo chat selected using contains text!");
                Thread.sleep(1000);
            } catch (Exception e2) {
                System.out.println("Could not find Demo chat: " + e2.getMessage());
            }

           
            // ── Send "Demo" message
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@contenteditable='true']")
            ));
            input.click();
            input.sendKeys("Demo");
            Thread.sleep(500);
            input.sendKeys(Keys.ENTER);
            System.out.println("Demo message sent!");
            Thread.sleep(500);

            // ── EDIT
            WebElement demoMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(@class,'leading-7')]//span[contains(text(),'Demo')]")
            ));
            new Actions(driver).moveToElement(demoMessage).perform();
            Thread.sleep(2000);

            WebElement trigger = (WebElement) js.executeScript(
                "for(var s of document.querySelectorAll('p[class*=\"leading-7\"] span')){" +
                "  if(s.textContent.trim()==='Demo'){" +
                "    var c=s.parentElement;" +
                "    while(c && c.tagName!=='BODY'){" +
                "      var t=c.querySelector('[data-slot=\"dropdown-menu-trigger\"]');" +
                "      if(t) return t;" +
                "      c=c.parentElement;" +
                "    }" +
                "  }" +
                "} return null;"
            );

            new Actions(driver)
                .moveToElement(demoMessage).pause(Duration.ofMillis(800))
                .moveToElement(trigger).pause(Duration.ofMillis(500))
                .click().perform();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[normalize-space(text())='Edit']")).click();
            Thread.sleep(1000);

            WebElement editInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@contenteditable='true']")
            ));
            editInput.click();
            editInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            editInput.sendKeys("message edited");
            editInput.sendKeys(Keys.ENTER);
            System.out.println("Message edited successfully!");
            Thread.sleep(2000);

            // ── DELETE
            WebElement editedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(@class,'leading-7')]//span[contains(text(),'message edited')]")
            ));
            new Actions(driver).moveToElement(editedMessage).perform();
            Thread.sleep(2000);

            WebElement triggerDelete = (WebElement) js.executeScript(
                "for(var s of document.querySelectorAll('p[class*=\"leading-7\"] span')){" +
                "  if(s.textContent.trim()==='message edited'){" +
                "    var c=s.parentElement;" +
                "    while(c && c.tagName!=='BODY'){" +
                "      var t=c.querySelector('[data-slot=\"dropdown-menu-trigger\"]');" +
                "      if(t) return t;" +
                "      c=c.parentElement;" +
                "    }" +
                "  }" +
                "} return null;"
            );

            new Actions(driver)
                .moveToElement(editedMessage).pause(Duration.ofMillis(800))
                .moveToElement(triggerDelete).pause(Duration.ofMillis(500))
                .click().perform();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[normalize-space(text())='Delete']")).click();
            Thread.sleep(1000);

            WebElement deleteElement = driver.findElement(
                By.xpath("//div[contains(@class, 'fixed')]//*[text()='Delete']")
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteElement);
            System.out.println("Delete confirmed via JavaScript!");

            // Upload files
            String[] files = {
                "C:\\Users\\HomePC\\Desktop\\File1.pdf",
                "C:\\Users\\HomePC\\Desktop\\File2.pdf",
                "C:\\Users\\HomePC\\Desktop\\File3.pdf",
                "C:\\Users\\HomePC\\Desktop\\File4.pdf"
            };
            FileUploadTest.UploadMultipleFiles(driver, wait, js, files);

            */


            
            
            // ── Ticket section ───────────────────────────────────────────────
           /*
                
                    WebElement ticketsLink = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(@class, 'truncate') and text()='Tickets']")
                    ));
                    ticketsLink.click();
                    System.out.println(" Clicked on Tickets");
                    Thread.sleep(1500);
                    

                    for (int i = 1; i <= 50; i++) {
                    	System.out.println("Creating Guest Ticket " + i + " of 10");
                    
                    WebElement createTicket = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[.//section[text()='Create Ticket']]")
                    ));
                    js.executeScript("arguments[0].click();", createTicket);
                    System.out.println(" Create Ticket button clicked");
                    Thread.sleep(1500);
                    
                  //Guest Customer
                    GuestCustomer guestCustomer = new GuestCustomer(driver);
                    guestCustomer.clickGuestCustomer();
                    guestCustomer.enterPreciseTopic("Test ticket from guest user");
                    guestCustomer.customeremail();
                    guestCustomer.prioritydropdown();
                    guestCustomer.FullName();
                    guestCustomer.phNumber();
                    guestCustomer.CustomerAddress();
                    guestCustomer.selectTeam();
                    guestCustomer.SuggestedMember();
                    guestCustomer.TicketDescription();
                    guestCustomer.AgentNotes();
                    guestCustomer.Clickcreatebtn();
                    Thread.sleep(1000);
             
                    }
            
            
            */
            
            /*  
           //  ── Loop: Create 100 tickets in normal ─────────────────────────────────────
            for (int i = 1; i <= totalTickets; i++) {
                System.out.println("\n========================================");
                System.out.println("🎫 Creating Ticket " + i + " of " + totalTickets);
                

                try {
                    // Click Create Ticket button
                    WebElement createTicket = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[.//section[text()='Create Ticket']]")
                    ));
                    js.executeScript("arguments[0].click();", createTicket);
                    System.out.println("✓ Create Ticket button clicked");
                    Thread.sleep(1500);

                    // Fill the form
                    NormalCustomer ticketForm = new NormalCustomer(driver, wait, js);
                    ticketForm.fillTicketForm();

                    successCount++;
                    System.out.println(" Ticket " + i + " created! [Success: " + successCount + " | Fail: " + failCount + "]");
                    Thread.sleep(500);

                } catch (Exception e) {
                    failCount++;
                    System.out.println("❌ Ticket " + i + " FAILED: " + e.getMessage().split("\n")[0]);
                    System.out.println("   [Success: " + successCount + " | Fail: " + failCount + "]");

                    // Try to close open modal before next iteration
                    try {
                        WebElement closeBtn = driver.findElement(
                            By.xpath("//div[@role='dialog']//button[@aria-label='Close' or normalize-space()='Cancel' or normalize-space()='×']")
                        );
                        js.executeScript("arguments[0].click();", closeBtn);
                        Thread.sleep(500);
                    } catch (Exception ignore) {
                        // Fallback: press Escape
                        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
                        Thread.sleep(500);
                    }
                }
            }

            */
            
                
                
                
                
                
                
           //Clients Section 
                
            CreateClients createClients = new CreateClients(driver,wait,js);
            createClients.clickclientoption();
            createClients.clickNewEntry();
            Thread.sleep(1000);
            createClients.Fullname();
            createClients.Email();
            createClients.phnum();
            createClients.selectPlatform();
            createClients.selectCountry();  
            createClients.enterLocation("ktm");
            createClients.Profilelink();
            createClients.InternalNotes();
            createClients.createnotebtn();
            
                
                

        } catch (Exception e) {
            System.out.println(" Error in main execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Thread.sleep(1000);
            // Close email browser if still open
          //  if (emailAutomate != null) {
          //      emailAutomate.closeEmailBrowser();
          //  }

        
            
            System.out.println("Process completed. Browser can be closed manually.");
        }
    }
}