package demoPlaze.validations;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

//softAssertions
public class validation extends BasAssertion {
   public   validation(WebDriver driver){
       super(driver);
   }

   private static  SoftAssert softassert=new SoftAssert();
   private  static  boolean used=false;
    @Override
    protected void assertTrue(boolean condition, String message) {
        used=true;
        softassert.assertTrue(condition,message);

    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used=true;
        softassert.assertFalse(condition,message);
    }

    @Override
    protected void  assertequal(String actual, String expected, String message) {
        used=true;
softassert.assertEquals(actual,expected,message);
    }
    public  static void assertAll(){
        if(!used) return;
        try {
            softassert.assertAll();
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
            throw e;
        }finally {
            softassert=new SoftAssert();
        }
    }
}