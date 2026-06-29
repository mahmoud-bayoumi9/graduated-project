package demoPlaze.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class chromeFactory extends abstractDriver{
   private ChromeOptions getOption(){
       ChromeOptions option=new ChromeOptions();
       option.addArguments("--start-maximized");     
       option.addArguments("--disable-infobars"); 
       option.addArguments("--remote-allow-origins=*");

       return option;
   }

    @Override
    public WebDriver createDriver() {
        return new ChromeDriver(getOption());
    }
}
