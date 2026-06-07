package demoPlaze.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

//hard assertions
public class verification extends BasAssertion {
    public verification(WebDriver driver){
        super(driver);
    }
    @Override
    protected void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition,message);

    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition,message);
    }

    @Override
    public void  assertequal(String actual, String expected, String message) {
        Assert.assertEquals(actual,expected,message);
    }
    public void isvisiable(By loc){
        wait.fluentWait().until(d->{
            try {
                d.findElement(loc).isDisplayed();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(driver.findElement(loc).isDisplayed(),"element is not visible");
    }
    public  void assertPageUrl(String url){
        String actualUrl=driver.getCurrentUrl();
        assertequal(actualUrl,url,"actual is not match the "+url);
    }
    public void assertTitle(String expectedTitle){
        String actualTitle=driver.getTitle();
        assertequal(actualTitle,expectedTitle,"does not match"+expectedTitle);
    }
}
