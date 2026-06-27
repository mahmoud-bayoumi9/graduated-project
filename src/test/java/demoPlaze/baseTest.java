package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    // رجعناه GuiDriver زي ما كان عشان التستات التانية متضربش كومبيلر
    protected GuiDriver driver; 

    @BeforeMethod
    public void setUp() {
        // 1. إنشاء الكائن
        driver = new GuiDriver();
        
        // 2. ⚠️ خطوة الأمان: بننادي على get() هنا جوه الـ setup 
        // عشان نجبر الـ ThreadLocal يفتح المتصفح الفعلي (Chrome/Firefox) فوراً قبل ما التست يبدأ
        if (driver.get() == null) {
            // لو الـ GuiDriver بتاعك جواه دالة بتعمل init أو start نادها هنا، 
            // لكن مجرد مناداة driver.get() غالباً كافية لتشغيل الـ ThreadLocal الـ lazy-loaded
            System.out.println("تنبيه: متصفح الـ WebDriver لم يتم إنشاؤه تلقائياً!");
        }
    }

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
