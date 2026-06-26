package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.io.File;

public class Actions {
    private WebDriver driver;
    private waitManager waitManager;

    public Actions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new waitManager(driver);
    }

    public void click(By locator) {
        try {
            // 🚀 انتظر أولاً حتى يكون العنصر مرئي وقابل للضغط بشكل صريح بدون Lambda معقدة
            WebElement element = waitManager.fluentWait().until(ExpectedConditions.elementToBeClickable(locator));
            
            // عمل سكرول للعنصر لضمان ظهوره في أبعاد شاشة السيرفر
            new org.openqa.selenium.interactions.Actions(driver).scrollToElement(element).perform();
            
            element.click();
        } catch (Exception e) {
            LogsManager.error("Failed to click on element: " + locator + " | Error: " + e.getMessage());
            throw e; // ارفع الخطأ عشان التيست كيس تبان إنها Failed في التقرير مش Skip
        }
    }

    public void sendKey(By locator, String text) {
        try {
            // 🚀 انتظر حتى يكون مربع الكتابة مرئياً تماماً
            WebElement element = waitManager.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            new org.openqa.selenium.interactions.Actions(driver).scrollToElement(element).perform();
            
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            LogsManager.error("Failed to send keys to element: " + locator + " | Error: " + e.getMessage());
            throw e;
        }
    }

    public String getText(By locator) {
        try {
            WebElement element = waitManager.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            new org.openqa.selenium.interactions.Actions(driver).scrollToElement(element).perform();
            return element.getText();
        } catch (Exception e) {
            LogsManager.error("Failed to get text from element: " + locator + " | Error: " + e.getMessage());
            return "";
        }
    }

    public void uploadFile(By locator, String filePath) {
        try {
            String absolutePath = System.getProperty("user.dir") + File.separator + filePath;
            WebElement element = waitManager.fluentWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            
            new org.openqa.selenium.interactions.Actions(driver).scrollToElement(element).perform();
            element.sendKeys(absolutePath);
        } catch (Exception e) {
            LogsManager.error("Failed to upload file to element: " + locator + " | Error: " + e.getMessage());
            throw e;
        }
    }

    public void dropDown(By locator, String visibleValue) {
        try {
            WebElement dropdownElement = waitManager.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            new org.openqa.selenium.interactions.Actions(driver).scrollToElement(dropdownElement).perform();
            
            Select selectCountry = new Select(dropdownElement);
            selectCountry.selectByVisibleText(visibleValue);
        } catch (Exception e) {
            LogsManager.error("Failed to select from dropdown: " + locator + " | Error: " + e.getMessage());
            throw e;
        }
    }
}
