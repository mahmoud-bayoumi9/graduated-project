package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    
    protected final GuiDriver driver = new GuiDriver(); 

    @BeforeMethod
    public void setUp() {
        try {
            if (driver.get() != null) {
                driver.get().navigate().to("https://automationexercise.com");
                driver.get().manage().window().maximize();
                System.out.println("[INFO] Navigated to Automation Exercise successfully.");
            } else {
               
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public WebDriver getWebDriver() {
        return (driver != null) ? driver.get() : null;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null && driver.get() != null) {
            try {
                driver.quit(); 
            } catch (Exception e) {
                System.out.println(e)
            }
        }
    }
}
