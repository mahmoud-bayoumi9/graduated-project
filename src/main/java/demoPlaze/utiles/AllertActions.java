package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class AllertActions {
    private final WebDriver driver;
    private final waitManager waitManager;
    public  AllertActions(WebDriver driver){
        this.driver=driver;
        this.waitManager=new waitManager(driver);
    }
    public void AcceptAlert(){
     this.waitManager.fluentWait().until(driver1 -> {
         try {
             driver1.switchTo().alert().accept();
             return true;
         } catch (Exception e) {
             LogsManager.error(e.getMessage());
             return false;
         }
     });
    }
    public void dismissAlert(){
        this.waitManager.fluentWait().until(driver1 -> {
            try {
                driver1.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }
    public String getText(){
        return this.waitManager.fluentWait().until(driver1 -> {
            try {
                String text=driver1.switchTo().alert().getText();
                return !text.isEmpty()?text:null;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return null;
            }
        });
    }
    public void sendText(String text){
         this.waitManager.fluentWait().until(driver1 -> {
            try {
                driver1.switchTo().alert().sendKeys(text);
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }
}
