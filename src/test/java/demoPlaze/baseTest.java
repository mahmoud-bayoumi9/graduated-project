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
        // حماية 1: إنشاء الـ GuiDriver لو كان null
        if (driver == null) {
            driver = new GuiDriver();
        }
        
        // حماية 2: إجبار الـ المتصفح على الفتح والتحقق من قيمته
        try {
            if (driver.get() == null) {
                System.out.println("[WARN] الـ WebDriver الداخلي بـ null، تأكد من كلاس GuiDriver!");
            }
        } catch (NullPointerException e) {
            System.out.println("[ERROR] فشل استدعاء driver.get() من الـ GuiDriver الرئيسي.");
        }
    }

    @Override
    public WebDriver getWebDriver() {
        // حماية 3 (الإنقاذ السريع): لو أي كلاس تست أو صفحة نادت الدالة دي في وقت غلط والـ driver لسه null
        // بنخلقه فوراً في نفس اللحظة عشان التيست ميموتش
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
            } catch (Exception e) {
                System.out.println("[INFO] المتصفح مغلق بالفعل أو حدث خطأ أثناء الإغلاق.");
            }
        }
    }
}
