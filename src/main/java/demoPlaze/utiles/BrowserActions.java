package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
//import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver;
    public  BrowserActions(WebDriver driver){
        this.driver=driver;
    }
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    public void navigateToSpecificUrl(String url) {
        driver.navigate().to(url);
        LogsManager.info("navigateto"+url);
    }
    public String getUrl() {

                 String url=driver.getCurrentUrl();
        LogsManager.info(url);
        return url;
    }
    public void refresh() {
        driver.navigate().refresh();
   }
   public void closeCurrentWindow(){
        driver.close();
   }
   public void SwitchToNewWindow(){
        driver.switchTo().newWindow(WindowType.WINDOW);
   }
}

