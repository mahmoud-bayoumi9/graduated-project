package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    
    // ❌ شيلنا الـ static عشان نضمن أن الـ Threads متضربش بعضها في التوازي
    protected GuiDriver driver; 

    @BeforeMethod
    public void setUp() {
        // إنشاء نسخة جديدة مستقلة لكل كلاس تيست
        if (driver == null) {
            driver = new GuiDriver();
        }
        
        try {
            if (driver.get() != null) {
                // 🌍 التوجيه للموقع الأساسي مباشرة لضمان عدم الوقوف على صفحة بيضاء
                driver.get().navigate().to("https://automationexercise.com");
                driver.get().manage().window().maximize();
                System.out.println("[INFO] Navigated to Automation Exercise successfully.");
            } else {
                System.out.println("[WARN] الـ WebDriver الداخلي بـ null، تأكد من كلاس GuiDriver!");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] فشل التعامل مع الـ driver في الـ setUp: " + e.getMessage());
        }
    }

    // @Override
    // public WebDriver getWebDriver() {
    //     if (driver == null) {
    //         System.out.println("[⚠️ EMERGENCY] تم استدعاء getWebDriver والـ driver بـ null! يتم الإنشاء الآن...");
    //         driver = new GuiDriver();
    //     }
    //     return driver.get();
    // }
    @Override
public WebDriver getWebDriver() {
    if (driver == null) {
        return null; // نرجع null بدل ما نفتح متصفح مشوه بره الـ BeforeMethod
    }
    return driver.get();
}

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("[INFO] تم إغلاق جلسة المتصفح بنجاح.");
            } catch (Exception e) {
                System.out.println("[INFO] المتصفح مغلق بالفعل أو حدث خطأ أثناء الإغلاق.");
            } finally {
                // 👍 تصفير الـ instance الحالية بأمان للكلاس الحالي بدون التأثير على الكلاسات الأخرى الشغالة بالتوازي
                driver = null; 
            }
        }
    }
}
