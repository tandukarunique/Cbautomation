package stater;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class Try {
    public static void main(String[] args) {
        ChromeDriver driver = null;
        Loginpage loginHelper = null;
        
        try {
            // Setup Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--start-maximized");
            
            // Create driver instance
            driver = new ChromeDriver(options);
            loginHelper = new Loginpage(driver);
            
            File authFile = new File("auth.json");
            
            if (authFile.exists() && loginHelper.loadSavedAuth()) {
                System.out.println("Auto-login successful!");
            } else {
                System.out.println("Manual login required.");
                // Replace with your credentials
                boolean loginSuccess = loginHelper.manualLoginWithCaptcha(
                    "uniquetandukar8645@gmail.com",  // CHANGE THIS
                    "Tha chaina 098! "             // CHANGE THIS
                );
                
                if (!loginSuccess) {
                    throw new RuntimeException("Login failed!");
                }
            }
            
            // Navigate to dashboard
            loginHelper.gotoAuthenticated("/dashboard", ".*/dashboard.*");
        /*   
            // Get organization ID
            String orgId = loginHelper.getOrgId();
            if (orgId != null) {
                System.out.println("Organization ID: " + orgId);
            }
            
            System.out.println("Successfully logged in and authenticated!");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            
            
            */
        } finally {
            if (driver != null) {
               
            }
            System.out.println("Process completed.");
        }
    }
}