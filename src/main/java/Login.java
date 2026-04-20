import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    public Login() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        
        driver.get("https://dev.chatboq.com/login");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys("tandukarunique098@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys("Tha chaina 098!");
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("_r_2_-form-item"))).click();
         
        
        
        
      
   

 

    }
}