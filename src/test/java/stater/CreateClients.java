package stater;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CreateClients {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    // Constructor
    public CreateClients(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }
    
    
    public void clickclientoption() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/clients')]"))).click();
  	
    }
    
    

    public void clickNewEntry() {

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//section[text()='New Entry']")
        )).click();
    }
    
    public void Fullname() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("Demo kumer kumer");
    }
    
    public void Email() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys("demo@gds.com");
    }
    
    public void phnum() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).sendKeys("9800033307");
    }
    
    
    public void selectPlatform() throws InterruptedException {
    try {
        // Click the visible dropdown button to open it
        WebElement platform = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@data-state='closed' and contains(@class, 'group')]")
        ));
        js.executeScript("arguments[0].click();", platform);
        Thread.sleep(1000);

        
        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='option'] | //li[@role='option']")
        ));
        js.executeScript("arguments[0].click();", firstOption);
       

    } catch (Exception e) {
        System.out.println("Platform select failed: " + e.getMessage());
    }
    }
    
    public void selectCountry() {

            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-slot='select-trigger' and .//span[@data-slot='select-value' and text()='Select Country']]")
            )).click();
            System.out.println("Approach succeeded: Located by data-slot and span text");
          
            try {
                WebElement hiddenSelect = driver.findElement(By.cssSelector("select[name='country']"));
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = '81';" +
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    hiddenSelect
                );
                System.out.println("Nepal set via hidden select successfully");
            } catch (Exception e) {
                System.out.println("Failed to set Nepal via hidden select: " + e.getMessage());
                throw new RuntimeException("Could not set Nepal on hidden select", e);
            }
        
     driver.findElement(By.xpath("//h2[data-slot='dialog-title']")).click();
        
    }
 
    public void enterLocation(String location) throws InterruptedException {
        // Wait for element to be clickable AND enabled
        WebElement locationField = wait.until(ExpectedConditions.elementToBeClickable(By.id("location")));
        
        // Optional: Check if enabled
        if (locationField.isEnabled()) {
        	locationField.click();
            locationField.clear();
            locationField.sendKeys(location);
        } else {
            throw new RuntimeException("Location field is disabled");
        }
        driver.findElement(By.tagName("body")).click();
       
    }
    
    public void Profilelink() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[text()='Add Profile Link']"))).click();
    }
    
    
    
}