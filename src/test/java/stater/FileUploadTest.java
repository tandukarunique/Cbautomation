package stater;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class FileUploadTest {

    /**
     * Uploads multiple files in a single dialog and sends them as one message
     * @param driver WebDriver instance
     * @param wait WebDriverWait instance for synchronization
     * @param js JavascriptExecutor for JavaScript execution
     * @param files Array of file paths to upload
     */
    public static void UploadMultipleFiles(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String[] files) {
        
        try {
            // Step 1: Click the plus button svg open garna lai
            WebElement plusButton = driver.findElement(By.xpath("//button[.//*[local-name()='path' and contains(@d, 'M12 6C12 6.13261')]]"));
            js.executeScript("arguments[0].click();", plusButton);
            System.out.println("Clicked plus button");
            Thread.sleep(1500);
            
            // Step 2: Click Document option
            WebElement documentOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Document']")
            ));
            documentOption.click();
            System.out.println("Clicked Document option");
            Thread.sleep(2000);
            
            // Step 3: Build a single string with all file paths in quotes, separated by spaces
            // Example: "C:\file1.pdf" "C:\file2.pdf" "C:\file3.pdf"
            StringBuilder multipleFiles = new StringBuilder();
            for (String filePath : files) {
                File uploadFile = new File(filePath);
                if (!uploadFile.exists()) {
                    System.out.println("File does not exist: " + filePath);
                    continue;
                }
                if (multipleFiles.length() > 0) {
                    multipleFiles.append(" ");
                }
                multipleFiles.append("\"").append(uploadFile.getAbsolutePath()).append("\"");
            }
            
            // Exit if no valid files found
            if (multipleFiles.length() == 0) {
                System.out.println("No valid files to upload");
                return;
            }
            
            System.out.println("Selecting files: " + multipleFiles.toString());
            
            // Step 4: Copy the file paths string to system clipboard
            StringSelection stringSelection = new StringSelection(multipleFiles.toString());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            
            // Step 5: Use Robot class to simulate keyboard actions on native dialog
            Robot robot = new Robot();
            Thread.sleep(1000);
            
            // Ctrl+V to paste file paths into the dialog's filename field
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Pasted file paths");
            
            Thread.sleep(1000);
            
            // Press Enter to confirm selection and close the dialog
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("Selected " + files.length + " files");
            Thread.sleep(3000);
            
            // Step 6: Press Enter to send the message with all attachments
            WebElement messageInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='textbox'] | //textarea")
            ));
            messageInput.sendKeys(Keys.ENTER);
            System.out.println("Message sent with " + files.length + " files");
            
        } catch (Exception e) {
            System.out.println("Upload failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}