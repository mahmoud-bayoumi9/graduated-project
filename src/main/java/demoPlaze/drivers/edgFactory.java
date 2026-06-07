package demoPlaze.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class edgFactory extends abstractDriver {
    private EdgeOptions  getOption(){
        EdgeOptions option=new EdgeOptions();
        option.addArguments("--start-maximized"); // يفتح المتصفح شاشة كاملة تلقائياً
        option.addArguments("--disable-infobars"); // يخفي شريط "Chrome is being controlled by automated software"
        option.addArguments("--remote-allow-origins=*");

        return option;
    }

    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(getOption());
    }
}
