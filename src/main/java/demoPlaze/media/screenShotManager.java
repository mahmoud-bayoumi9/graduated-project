package demoPlaze.media;

import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.logs.timeManager;
import demoPlaze.utiles.report.AllureAttachment;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.logging.LogManager;

public class screenShotManager {
    public   static final String screenPath="test-outPut/screenShots/";
    public static void takeFullScreen(WebDriver driver,String screenName){
        try{
            File screenShoScr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenDestination=new File(screenPath+screenName +' ' + timeManager.getTimestamp()+".png");
            FileUtil.copyFile(screenShoScr,screenDestination);
            AllureAttachment.attachmentScreenShots(screenName,screenDestination.getAbsolutePath());
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }

    }
    public static void takeElementScreen(WebDriver driver, By locator){
        try{
            File screenShoScr = driver.findElement(locator).getScreenshotAs(OutputType.FILE);
            String ariaName=driver.findElement(locator).getAccessibleName();
            File screenDestination=new File(screenPath+ariaName +' ' + timeManager.getTimestamp()+".png");
            FileUtil.copyFile(screenShoScr,screenDestination);
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }

    }
}
