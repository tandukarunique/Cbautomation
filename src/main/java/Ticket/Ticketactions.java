package Ticket;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ticketactions {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public Ticketactions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        try {
            // Navigate to tickets page
            driver.get("https://dev.chatboq.com/d2de9a0d-f488-458c-8e3c-51c782562eeb/tickets");
            
            // Wait for page to load completely
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-state='unchecked' and @data-slot='checkbox']")));
            
            // Process 20 tickets
            for(int i = 0; i < 20; i++) {
                processTicket();
                
                // Optional: Add verification that action was successful
                System.out.println("Processed ticket " + (i + 1) + " of 20");
            }
            
            // Process priority for additional ticket
            processPriority();
            
        } catch (Exception e) {
            System.err.println("Error in Ticketactions: " + e.getMessage());
            throw new RuntimeException("Failed to process tickets", e);
        }
    }
    
    private void processTicket() {
        // Re-find elements for each ticket to avoid stale references
        WebElement checkbox = waitUntilClickable(By.xpath("//button[@data-state='unchecked' and @data-slot='checkbox']"));
        checkbox.click();
        
        WebElement setStatus = waitUntilClickable(By.xpath("//div[text()='Set Status']"));
        setStatus.click();
        
        WebElement newStatus = waitUntilClickable(By.xpath("//span[text()='New']"));
        newStatus.click();
        
        WebElement confirm = waitUntilClickable(By.xpath("//span[text()='Confirm']"));
        confirm.click();
        
        // Wait for confirmation to complete before next iteration
        wait.until(ExpectedConditions.invisibilityOf(confirm));
    }
    
    private void processPriority() {
        WebElement checkbox = waitUntilClickable(By.xpath("//button[@data-state='unchecked' and @data-slot='checkbox']"));
        checkbox.click();
        
        WebElement setPriority = waitUntilClickable(By.xpath("//div[text()='Set Priority']"));
        setPriority.click();
        
        WebElement critical = waitUntilClickable(By.xpath("//span[text()='Critical']"));
        critical.click();
        
        WebElement confirm = waitUntilClickable(By.xpath("//span[text()='Confirm']"));
        confirm.click();
    }
    
    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}