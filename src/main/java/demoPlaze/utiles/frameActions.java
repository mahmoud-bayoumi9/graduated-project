package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class frameActions {
    private final WebDriver driver;
    private final waitManager waitManager;
    public  frameActions(WebDriver driver){
        this.driver=driver;
        this.waitManager=new waitManager(driver);
    }
    public void switchToFrameByIndex(int index){
        waitManager.fluentWait().until(driver1 -> {
            try {
                driver1.switchTo().frame(index);
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }
    public void switchToFrameByNameOrId(String nameOrId) {
        waitManager.fluentWait().until(driver1 -> {
            try {
                driver1.switchTo().frame(nameOrId);
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }
    public void switchToFrameByWebElement(By frameLocator) {
        waitManager.fluentWait().until(driver1 -> {
            try {
                var frameElement = driver1.findElement(frameLocator);
                driver1.switchTo().frame(frameElement);
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }
    public void switchToDefaultContent() {
        waitManager.fluentWait().until(driver1 -> {
            try {
                driver1.switchTo().defaultContent();
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }
}
