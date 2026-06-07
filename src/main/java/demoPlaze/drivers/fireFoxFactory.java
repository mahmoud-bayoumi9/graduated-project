package demoPlaze.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class fireFoxFactory extends abstractDriver {
    private     FirefoxOptions getOption(){
       FirefoxOptions option=new     FirefoxOptions();
        option.addArguments("--start-maximized"); // يفتح المتصفح شاشة كاملة تلقائياً
        option.addArguments("--disable-infobars"); // يخفي شريط "Chrome is being controlled by automated software"
        option.addArguments("--remote-allow-origins=*");

        return option;
    }

    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver(getOption());
    }
}
