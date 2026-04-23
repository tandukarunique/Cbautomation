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
    	    By.xpath("//button[@role='combobox']//span[text()='Select Country']")
    	)).click();
    }
    
    
    
    
}