package demoPlaze.customListener;

import demoPlaze.FileUtiles;
import demoPlaze.drivers.webDriverProvider;
import demoPlaze.media.screenShotManager;
import demoPlaze.utiles.datareader.propertyReader;
import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.report.AllureConstant;
//import demoPlaze.utiles.report.AllureEnvironmentManager;
import demoPlaze.utiles.report.AllureReportGenerator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class testNGListener implements ITestListener, IInvokedMethodListener, IExecutionListener {

    @Override
    public void onExecutionStart() {
        // مسح مجلد السكرين شوتس فقط لتجنب تراكم الصور القديمة
        FileUtils.deleteQuietly(new File(screenShotManager.screenPath));
        createOutPutDirectory();

        // نقل الـ History القديم إلى نتائج التست الحالي قبل توليد أي تقرير جديد
        System.out.println("⏳ جاري تحضير الـ History لتقارير Allure...");
        AllureReportGenerator.copyHistor();

        propertyReader.loadProperties();
//        AllureEnvironmentManager.setAllureEnvironment();
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("🏁 انتهت الاختبارات تماماً.");
        System.out.println("⏳ جاري الانتظار ثانيتين للتأكد من استقرار وحفظ ملفات الـ JSON بالكامل...");

        try {
            // مهلة أمان كافية ليغلق فريمورك TestNG ملفات النتائج بسلام
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("🚀 جاري إصدار وتوليد تقرير HTML التلقائي (Single File)...");

        // استدعاء التوليد مرة واحدة فقط كـ Single File
        AllureReportGenerator.generateReport(true);

        System.out.println("📝 جاري إعادة تسمية وفتح التقرير...");
        String finalReportName = AllureReportGenerator.renameReport();
        AllureReportGenerator.openReport(finalReportName);
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogsManager.info("🔴 TestCase [" + result.getName() + "] is started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsManager.info("🟢 TestCase [" + result.getName() + "] is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsManager.info("❌ TestCase [" + result.getName() + "] is failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsManager.info("🟡 TestCase [" + result.getName() + "] is skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("📂 [Tag <test>] بدأ تنفيذ الـ Test Block: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📁 [Tag <test>] انتهى تنفيذ الـ Test Block: " + context.getName());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = null;

            if (testResult.getInstance() instanceof webDriverProvider provider) {
                driver = provider.getWebDriver();
            }

            if (driver != null) {
                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS -> screenShotManager.takeFullScreen(driver, "passed_" + testResult.getName());
                    case ITestResult.SKIP -> screenShotManager.takeFullScreen(driver, "skipped_" + testResult.getName());
                    case ITestResult.FAILURE -> screenShotManager.takeFullScreen(driver, "failed_" + testResult.getName());
                }
            }
            System.out.println("✨ [Method] تم الانتهاء من الميثود والتقاط الحالة لـ: " + method.getTestMethod().getMethodName());
        }
    }

    public void createOutPutDirectory() {
        FileUtiles.createDirectory(screenShotManager.screenPath);
    }
}