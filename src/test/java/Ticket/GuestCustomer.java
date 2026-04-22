package Ticket;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GuestCustomer {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    public GuestCustomer(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }
    

    
    public void clickGuestCustomer() {
        // Wait for dialog
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@role='dialog']")
        ));
        
        // Using CSS Selector with aria-controls (best approach)
        WebElement guestOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button[aria-controls*='guest_customer']")
        ));
        guestOption.click();
        System.out.println("✓ Guest Customer clicked");
    }
    
    public void enterPreciseTopic(String topic) {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Your Precise Topic']")
        )).sendKeys(topic);
    }
    
    public void customeremail() {
    	try {
    		wait.until(ExpectedConditions.elementToBeClickable(By.id("customer_email"))).sendKeys("demo@demoo.com");
    	}
    	catch (Exception e) {
    		System.out.println("Customermail click bhayena" + e.getMessage());
    	}
    }
    
    public void prioritydropdown() throws InterruptedException {
    	try {
    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-state='closed' and .//span[text()='Select Priority']]"))).click();
    	}
    	catch(Exception e) {
    		System.out.println("Priority click bhayena+ e.getMessage()");
    		Thread.sleep(1000);
    	}
    	//Dropdown open bhayesi click
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[text()='High']"))).click();
    }
    
    
    public void FullName() {
    	try {
    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Customer Full Name Here']"))).sendKeys("Demo kumar");
    	}
    	catch(Exception e) {
    		System.out.println("Fullname click bhayena+ e.getMessage()");
    	}
    }
    
    public void phNumber() {
    	try {
    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Customer Phone Number']"))).sendKeys("9806877793");
    	}
    	catch(Exception e) {
    		System.out.println("Phnumber pass bhayene" + e.getMessage());
    	}
    }
    
    public void CustomerAddress() {
    	try {
    		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer_location"))).sendKeys("Chabahil");
    	}
    	catch(Exception e) {
    		System.out.println("Customer address click bhayena" + e.getMessage());
    	}
    }
    
    public void selectTeam() {
    	 try {
    	        WebElement teamDropdown = wait.until(ExpectedConditions.elementToBeClickable(
    	            By.xpath("//div[@role='dialog']//*[normalize-space()='Team' or normalize-space()='Team*']/following::input[@role='combobox'][1]")
    	        ));
    	        
    	        js.executeScript("arguments[0].click();", teamDropdown);
    	        System.out.println("✓ Team dropdown clicked");
    	        Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println("Failed to click team dropdown: " + e.getMessage());
        }
        
        
        
     }
    }
    
    
    
