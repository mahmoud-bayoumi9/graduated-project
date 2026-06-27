package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    // جعلناه static لضمان مشاركة نفس النسخة عبر دورة حياة الـ TestNG بالكامل
    protected static GuiDriver driver; 

    @BeforeMethod
    public void setUp() {
        // حماية 1: إنشاء الـ GuiDriver لو كان null أو لو التيست اللي قبله صفّره
        if (driver == null) {
            driver = new GuiDriver();
        }
        
        // حماية 2: تأكيد عمل maximize وجلب الـ URL الأساسي لو محتاج
        try {
            if (driver.get() != null) {
                driver.get().manage().window().maximize();
            } else {
                System.out.println("[WARN] الـ WebDriver الداخلي بـ null، تأكد من كلاس GuiDriver!");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] فشل التعامل مع الـ driver في الـ setUp: " + e.getMessage());
        }
    }

    @Override
    public WebDriver getWebDriver() {
        // حماية 3 (الإنقاذ السريع): لو تم استدعاء الدالة في وقت غلط والـ driver لسه null
        if (driver == null) {
            System.out.println("[⚠️ EMERGENCY] تم استدعاء getWebDriver والـ driver بـ null! يتم الإنشاء الآن...");
            driver = new GuiDriver();
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
                // 🚀 الحل السحري: تصفير الـ driver تماماً لضمان فتح متصفح جديد ونظيف للتيست القادم
                driver = null; 
            }
        }
    }
}
