// package demoPlaze.drivers;

// import io.github.bonigarcia.wdm.WebDriverManager;
// import demoPlaze.utiles.Actions;
// import demoPlaze.utiles.AllertActions;
// import demoPlaze.utiles.BrowserActions;
// import demoPlaze.utiles.frameActions;
// import demoPlaze.validations.validation;
// import demoPlaze.validations.verification;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.support.ThreadGuard;
// import java.util.HashMap;
// import java.util.Map;
// public class GuiDriver {
//     private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

//     public GuiDriver() {
//         WebDriver rawDriver;
//         ChromeOptions chromeOptions = new ChromeOptions();
//         Map<String, Object> prefs = new HashMap<>();
//         prefs.put("autofill.profile_enabled", false);
//         prefs.put("autofill.credit_card_enabled", false);
//         prefs.put("credentials_enable_service", false);
//         prefs.put("profile.password_manager_enabled", false);
//         chromeOptions.setExperimentalOption("prefs", prefs);
//         chromeOptions.addArguments("--disable-popup-blocking");
//         chromeOptions.addArguments("--host-resolver-rules=MAP pagead2.googlesyndication.com 127.0.0.1, MAP googleads.g.doubleclick.net 127.0.0.1");
//         chromeOptions.addArguments("--disable-notifications");
//         chromeOptions.addArguments("--start-maximized");
//         chromeOptions.addArguments("--host-resolver-rules=MAP *googleadservices.com 127.0.0.1, MAP *googlesyndication.com 127.0.0.1, MAP *amazon-adsystem.com 127.0.0.1, MAP *google-analytics.com 127.0.0.1");
//         String headlessProp = System.getProperty("headless", "false");
//         if (Boolean.parseBoolean(headlessProp) || System.getenv("GITHUB_ACTIONS") != null) {
//             chromeOptions.addArguments("--headless=new"); 
//             chromeOptions.addArguments("--disable-gpu");
//             chromeOptions.addArguments("--no-sandbox");
//             chromeOptions.addArguments("--disable-dev-shm-usage");
//             chromeOptions.addArguments("--window-size=1920,1080");
//             chromeOptions.addArguments("--remote-allow-origins=*"); 
//         }
//         WebDriverManager.chromedriver().setup();
//         rawDriver = new ChromeDriver(chromeOptions); 
//         WebDriver protectedDriver = ThreadGuard.protect(rawDriver);
//         driverThreadLocal.set(protectedDriver);
//     }
//     public Actions action(){ return new Actions(get()); }
//     public BrowserActions brows(){ return new BrowserActions(get()); }
//     public frameActions frameActions(){ return new frameActions(get()); }
//     public AllertActions allertActions(){ return new AllertActions(get()); }
//     public validation validation(){ return new validation(get()); }
//     public verification verfy(){ return new verification(get()); }
//     public WebDriver get() { return driverThreadLocal.get(); }

//     public void quit() {
//         if (driverThreadLocal.get() != null) {
//             driverThreadLocal.get().quit();
//             driverThreadLocal.remove();
//         }
//     }
// }
package demoPlaze.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import demoPlaze.utiles.Actions;
import demoPlaze.utiles.AllertActions;
import demoPlaze.utiles.BrowserActions;
import demoPlaze.utiles.frameActions;
import demoPlaze.validations.validation;
import demoPlaze.validations.verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ThreadGuard;
import java.util.HashMap;
import java.util.Map;

public class GuiDriver {
    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GuiDriver() {
        WebDriver rawDriver;
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--host-resolver-rules=MAP pagead2.googlesyndication.com 127.0.0.1, MAP googleads.g.doubleclick.net 127.0.0.1");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--host-resolver-rules=MAP *googleadservices.com 127.0.0.1, MAP *googlesyndication.com 127.0.0.1, MAP *amazon-adsystem.com 127.0.0.1, MAP *google-analytics.com 127.0.0.1");
        
        String headlessProp = System.getProperty("headless", "false");
        if (Boolean.parseBoolean(headlessProp) || System.getenv("GITHUB_ACTIONS") != null) {
            chromeOptions.addArguments("--headless=new"); 
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--remote-allow-origins=*"); 
            
            // 🚀 السطور السحرية لتخطي صفحة الـ Cloudflare Verification في الـ Pipeline:
            chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
            chromeOptions.setExperimentalOption("excludeSwitches", new java.util.ArrayList<String>() {{ add("enable-automation"); }});
            chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        }
        
        WebDriverManager.chromedriver().setup();
        rawDriver = new ChromeDriver(chromeOptions); 
        WebDriver protectedDriver = ThreadGuard.protect(rawDriver);
        driverThreadLocal.set(protectedDriver);
    }
    
    public Actions action(){ return new Actions(get()); }
    public BrowserActions brows(){ return new BrowserActions(get()); }
    public frameActions frameActions(){ return new frameActions(get()); }
    public AllertActions allertActions(){ return new AllertActions(get()); }
    public validation validation(){ return new validation(get()); }
    public verification verfy(){ return new verification(get()); }
    public WebDriver get() { return driverThreadLocal.get(); }

    public void quit() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
