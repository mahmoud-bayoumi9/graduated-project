package demoPlaze.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class chromeFactory extends abstractDriver{
   private ChromeOptions getOption(){
       ChromeOptions option=new ChromeOptions();
       option.addArguments("--start-maximized"); // يفتح المتصفح شاشة كاملة تلقائياً
       option.addArguments("--disable-infobars"); // يخفي شريط "Chrome is being controlled by automated software"
       option.addArguments("--remote-allow-origins=*");

       return option;
   }

    @Override
    public WebDriver createDriver() {
        return new ChromeDriver(getOption());
    }
}
