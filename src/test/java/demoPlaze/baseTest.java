package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;

public class baseTest implements webDriverProvider {
    protected GuiDriver driver;
    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
