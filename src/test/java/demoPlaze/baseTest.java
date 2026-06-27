package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    
    // 🛡️ نخليها final عشان نضمن إن الـ Instance دي ثابتة ومستقرة ومحدش يصفرها بالتوازي
    protected final GuiDriver driver = new GuiDriver(); 

    @BeforeMethod
    public void setUp() {
        try {
            // 🚀 الـ GuiDriver ثابت، بس الـ ThreadLocal جواه بيفتح متصفح جديد ونظيف لكل تيست هنا بأمان
            if (driver.get() != null) {
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

    @Override
    public WebDriver getWebDriver() {
        // بيرجع الـ driver الحالي للكلاس بأمان ومن غير ريسك لفتح متصفح أقرع
        return (driver != null) ? driver.get() : null;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null && driver.get() != null) {
            try {
                driver.quit(); // 🔒 بيقفل المتصفح الحالي وبيعمل للـ ThreadLocal .remove() عشان ينظف الـ Thread
                System.out.println("[INFO] تم إغلاق جلسة المتصفح بنجاح.");
            } catch (Exception e) {
                System.out.println("[INFO] المتصفح مغلق بالفعل أو حدث خطأ أثناء الإغلاق.");
            }
            // ❌ شيلنا سطر driver = null تماماً عشان ميبوظش الـ Instance الثابتة
        }
    }
}
