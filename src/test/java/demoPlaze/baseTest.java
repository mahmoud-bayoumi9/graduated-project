package demoPlaze;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.drivers.webDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest implements webDriverProvider {
    protected GuiDriver guiDriver; // 👈 غيرنا الاسم عشان نفرق بينه وبين الـ WebDriver الفعلي
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        guiDriver = new GuiDriver();
        
        // ⚠️ اتأكد إن السطر ده أو ما يشابهه موجود جوه الـ GuiDriver عشان يملى الـ ThreadLocal
        // لو الـ Constructor مش بيعمل ده تلقائي، لازم تنادي دالة التشغيل هنا، مثلاً:
        // guiDriver.startBrowser(); 
        
        this.driver = guiDriver.get(); // 👈 بناخد الـ WebDriver الفعلي ونخزنه هنا فوراً
    }

    @Override
    public WebDriver getWebDriver() {
        // لو الـ driver لسه null، بنحاول نجيبه من الـ guiDriver كخطة بديلة
        if (driver == null && guiDriver != null) {
            return guiDriver.get();
        }
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (guiDriver != null) {
            guiDriver.quit();
        }
    }
}
