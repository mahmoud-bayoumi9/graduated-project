package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    protected GuiDriver driver;

    @BeforeMethod
    public void setUp() {
        // 🚀 هنا بنخليه يفتح الـ GuiDriver اللي متظبط فيه الـ Headless والـ Options تلقائياً
        driver = new GuiDriver();
    }

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        // 🛑 قفل المتصفح وتنظيف الـ ThreadLocal بعد كل تيست كيس
        if (driver != null) {
            driver.quit();
        }
    }
}
