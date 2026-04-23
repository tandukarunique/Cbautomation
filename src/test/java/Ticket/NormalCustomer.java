package Ticket;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NormalCustomer {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public NormalCustomer(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    // Helper: open a combobox input and pick the first option via Arrow Down + Enter
    private void selectFirstOption(By inputLocator) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        js.executeScript("arguments[0].focus();", input);
        js.executeScript("arguments[0].click();", input);
        Thread.sleep(800);
        input.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        input.sendKeys(Keys.ENTER);
        Thread.sleep(500);
    }

    public void fillTicketForm() throws InterruptedException {

        // ── Step 1: Ticket Topic ─────────────────────────────────────────────
        WebElement topic = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@role='dialog']//input[contains(@placeholder,'Precise Topic')]")
        ));
        topic.click();
        topic.sendKeys("Test Ticket Issue");
        System.out.println("✓ Ticket topic entered");
        Thread.sleep(500);

        // ── Step 2: Select Normal Customer tab ──────────────────────────────
        try {
            WebElement normalTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']//button[normalize-space()='Normal Customer']")
            ));
            js.executeScript("arguments[0].click();", normalTab);
            System.out.println("✓ Normal Customer tab selected");
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("⚠ Normal Customer tab not found, skipping");
        }

        // ── Step 3: Customer — select first option ───────────────────────────
        System.out.println("Selecting first Customer...");
        selectFirstOption(
            By.xpath("//div[@role='dialog']//*[normalize-space()='Customer']/following::input[@role='combobox'][1]")
        );
        System.out.println("✓ Customer selected");

        // ── Step 4: Priority — select first option ───────────────────────────
        System.out.println("Selecting first Priority...");
        try {
            // Try native <select> first
            WebElement prioritySelect = driver.findElement(
                By.xpath("//div[@role='dialog']//*[normalize-space()='Priority' or normalize-space()='Priority*']/following::select[1]")
            );
            new org.openqa.selenium.support.ui.Select(prioritySelect).selectByIndex(1);
            System.out.println("✓ Priority selected (native select)");
        } catch (Exception e) {
            // Fallback: combobox button
            WebElement priorityBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']//*[normalize-space()='Priority' or normalize-space()='Priority*']/following::button[@role='combobox'][1]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", priorityBtn);
            js.executeScript("arguments[0].click();", priorityBtn);
            Thread.sleep(800);
            // Click first visible option
            WebElement firstOpt = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@role='dialog' or @role='listbox']//*[@role='option'])[1]")
            ));
            js.executeScript("arguments[0].click();", firstOpt);
            System.out.println("✓ Priority selected (combobox)");
        }
        Thread.sleep(500);

        // ── Step 5: Team — select first option ──────────────────────────────
        System.out.println("Selecting first Team...");
        try {
            selectFirstOption(
                By.xpath("//div[@role='dialog']//*[normalize-space()='Team' or normalize-space()='Team*']/following::input[@role='combobox'][1]")
            );
            System.out.println("✓ Team selected");
        } catch (Exception e) {
            System.out.println("⚠ Team dropdown not found, skipping");
        }

        // ── Step 6: Suggested Member — select first option ───────────────────
        System.out.println("Selecting first Suggested Member...");
        try {
            selectFirstOption(
                By.xpath("//div[@role='dialog']//*[normalize-space()='Suggested Member' or normalize-space()='Suggested Member*']/following::input[@role='combobox'][1]")
            );
            System.out.println(" Suggested Member selected");
        } catch (Exception e) {
            System.out.println(" Suggested Member dropdown not found, skipping");
        }

        // ── Step 7: Ticket Remarks ───────────────────────────────────────────
        System.out.println("Entering Ticket Remarks...");
        try {
            WebElement remarks = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='dialog']//textarea[contains(@placeholder,'Subject') or contains(@placeholder,'Remark') or contains(@placeholder,'Note') or contains(@placeholder,'Description')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", remarks);
            remarks.click();
            remarks.sendKeys("Demo");
            System.out.println("✓ Ticket remarks entered: Demo");
        } catch (Exception e) {
            System.out.println("⚠ Remarks field not found, skipping: " + e.getMessage().split("\n")[0]);
        }
        Thread.sleep(500);

        // ── Step 8: Internal Notes ───────────────────────────────────────────
        System.out.println("Entering Internal Notes...");
        try {
            WebElement notes = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='dialog']//textarea[@placeholder='Note to agents']")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", notes);
            notes.click();
            notes.sendKeys("Demo");
            System.out.println("✓ Internal notes entered");
        } catch (Exception e) {
            System.out.println("⚠ Internal notes field not found, skipping");
        }
        Thread.sleep(500);

        // ── Step 9: Submit ───────────────────────────────────────────────────
        System.out.println("Submitting ticket...");
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='dialog']//button[normalize-space()='Create Ticket']")
        ));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", createBtn);
        js.executeScript("arguments[0].click();", createBtn);
        System.out.println("✓ Create Ticket clicked");

        Thread.sleep(2000);
        System.out.println(" Ticket form submitted!");
    }
}