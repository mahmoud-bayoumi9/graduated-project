package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class Actions {
    private WebDriver driver;
    private  waitManager waitManager;
    public Actions(WebDriver driver){
        this.driver=driver;
        this.waitManager=new waitManager(driver);

    }
    public  void click(By locator){
        this.waitManager.fluentWait().until(d ->{
            try{
                WebElement element=d.findElement(locator);
                new org.openqa.selenium.interactions.Actions(d).scrollToElement(element);
                element.click();
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });

    }
    public  void sendKey(By locator,String text){
        this.waitManager.fluentWait().until(d ->{
            try{
                WebElement element=d.findElement(locator);
                new org.openqa.selenium.interactions.Actions(d).scrollToElement(element);
                element.clear();
                element.sendKeys(text);
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });

    }
    public  String getText(By locator) {
        return  this.waitManager.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new org.openqa.selenium.interactions.Actions(d).scrollToElement(element);
                String msg = element.getText();

                return !msg.isEmpty() ? msg : null;

            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return null;
            }
        });
    }
    public  void uploadFile( By locator,String filePath){
            String absolutePath=System.getProperty("user.dir")+ File.separator+filePath;
        this.waitManager.fluentWait().until(d ->{
            try{
                WebElement element=d.findElement(locator);
                new org.openqa.selenium.interactions.Actions(d).scrollToElement(element);
                element.clear();
                element.sendKeys(absolutePath);
                return true;
            } catch (Exception e) {
                LogsManager.error(e.getMessage());
                return false;
            }
        });
    }

    public void dropDown(By locator,String VisibleValue){

        waitManager.fluentWait().until(d -> {
                    try {
                        WebElement dropdownElement = driver.findElement(locator);
                        Select selectCountry = new Select(dropdownElement);
                        selectCountry.selectByVisibleText(VisibleValue);
                        return true;
                    } catch (Exception e) {

                        return true;
                    }
                }
        );
    }

}
