package stater;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

public class SaveCookies {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev.chatboq.com/login");

        System.out.println("Login manually now...");

        String currentUrl = "";
        for(int i = 0; i < 24; i++) {
            Thread.sleep(5000);
            currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            if(!currentUrl.contains("/login")) {
                System.out.println("✅ Login detected!");
                Thread.sleep(3000);
                break;
            }
        }

        // Check localStorage
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String allStorage = (String) js.executeScript(
            "return JSON.stringify(localStorage);"
        );
        System.out.println("LocalStorage: " + allStorage);

        // Check sessionStorage
        String allSession = (String) js.executeScript(
            "return JSON.stringify(sessionStorage);"
        );
        System.out.println("SessionStorage: " + allSession);

        // Print ALL cookies with details
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Total Cookies: " + cookies.size());
        System.out.println("All cookies:");
        for(Cookie c : cookies) {
            System.out.println(
                "Name: " + c.getName() +
                " | Value: " + c.getValue() +
                " | Domain: " + c.getDomain() +
                " | Path: " + c.getPath()
            );
        }

        // Save cookies to file
        PrintWriter writer = new PrintWriter(new FileWriter("cookies.txt"));
        for(Cookie c : cookies) {
            writer.println(
                c.getName() + ";" +
                c.getValue() + ";" +
                c.getDomain() + ";" +
                c.getPath()
            );
        }
        writer.close();

        System.out.println("✅ Done! Cookies saved: " + cookies.size());
        driver.quit();
    }
}