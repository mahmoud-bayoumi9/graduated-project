package demoPlaze.validations;

import demoPlaze.utiles.waitManager; // تأكد من اسم الباكدج الصحيح للـ waitManager عندك
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

// hard assertions
public class verification extends BasAssertion {
    private final waitManager waitManager;

    public verification(WebDriver driver){
        super(driver);
        this.waitManager = new waitManager(driver);  
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Override
    public void assertequal(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public void isvisiable(By loc){
        try {
            WebElement element = waitManager.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(loc));
            
            assertTrue(element.isDisplayed(), "Element located by " + loc + " is not visible");
        } catch (Exception e) {
            assertTrue(false, "Element located by " + loc + " was not found or not visible within timeout. Error: " + e.getMessage());
        }
    }

    public void assertPageUrl(String url){
        try {
            waitManager.fluentWait().until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
        }
        String actualUrl = driver.getCurrentUrl();
        assertequal(actualUrl, url, "Actual URL does not match the expected: " + url);
    }

    public void assertTitle(String expectedTitle){
        try {
            waitManager.fluentWait().until(ExpectedConditions.titleIs(expectedTitle));
        } catch (Exception e) {
        }
        String actualTitle = driver.getTitle();
        assertequal(actualTitle, expectedTitle, "Actual title does not match the expected: " + expectedTitle);
    }
}
