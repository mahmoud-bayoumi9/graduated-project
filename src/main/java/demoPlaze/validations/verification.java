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
        this.waitManager = new waitManager(driver); // تعريف الـ waitManager ليكون مربوطاً بدرايفر الـ Thread الحالي
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
            // 🚀 استخدام الـ ExpectedConditions الجاهزة والسريعة على السيرفر بدلاً من اللامبدا
            WebElement element = waitManager.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(loc));
            
            // التأكيد المباشر طالما الـ Wait عدى بنجاح والعنصر رجع مرئي
            assertTrue(element.isDisplayed(), "Element located by " + loc + " is not visible");
        } catch (Exception e) {
            assertTrue(false, "Element located by " + loc + " was not found or not visible within timeout. Error: " + e.getMessage());
        }
    }

    public void assertPageUrl(String url){
        // انتظر حتى يتغير الـ URL للـ URL المتوقع لضمان عدم التسرع في الـ Assert على السيرفر
        try {
            waitManager.fluentWait().until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            // لو الـ timeout خلص، الـ Assert تحت هيطلع الـ Error المظبوط في التقرير
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
