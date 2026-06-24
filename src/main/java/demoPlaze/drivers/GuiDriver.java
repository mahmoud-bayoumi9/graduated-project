package demoPlaze.drivers;
import io.github.bonigarcia.wdm.WebDriverManager;

import demoPlaze.utiles.Actions;
import demoPlaze.utiles.AllertActions;
import demoPlaze.utiles.BrowserActions;
import demoPlaze.utiles.datareader.propertyReader;
import demoPlaze.utiles.frameActions;
import demoPlaze.validations.validation;
import demoPlaze.validations.verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.util.HashMap;
import java.util.Map;

public class GuiDriver {
//    private static final String browser = propertyReader.getProperty("browserType").toLowerCase();
    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GuiDriver() {
        WebDriver rawDriver;

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--host-resolver-rules=MAP pagead2.googlesyndication.com 127.0.0.1, MAP googleads.g.doubleclick.net 127.0.0.1");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        rawDriver = new ChromeDriver();
        switch ("edge") {
            case "edge":
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().clearDriverCache().setup();
                rawDriver = new ChromeDriver();
//                rawDriver = new EdgeDriver();
                break;
//            case "firefox":
//                rawDriver = new FirefoxDriver();
//                break;
            default:
                throw new IllegalArgumentException("Driver is not Supported " );
        }


        WebDriver protectedDriver = ThreadGuard.protect(rawDriver);

        driverThreadLocal.set(protectedDriver);
    }
   public Actions action(){
        return new Actions(get());
   }
   public BrowserActions brows(){
        return new BrowserActions(get());
   }
   public frameActions frameActions(){
        return new frameActions(get());
   }
   public AllertActions allertActions(){
        return new AllertActions(get());
   }
   public validation validation(){
        return new validation(get());
   }
   public verification verfy(){
        return  new verification(get());

   }

    public WebDriver get() {
        return driverThreadLocal.get();
    }

    public void quit() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}