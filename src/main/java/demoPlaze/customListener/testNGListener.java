package demoPlaze.customListener;

import demoPlaze.FileUtiles;
import demoPlaze.drivers.webDriverProvider;
import demoPlaze.media.screenShotManager;
import demoPlaze.utiles.datareader.propertyReader;
import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.report.AllureConstant;
import demoPlaze.utiles.report.AllureEnvironmentManager;
import demoPlaze.utiles.report.AllureReportGenerator;
import demoPlaze.validations.validation;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

import java.io.File;
public class testNGListener implements ITestListener, IInvokedMethodListener, IExecutionListener {

    @Override
    public void onExecutionStart() {
        try {
            LogsManager.info("🔴 Starting Test Execution Suite...");
            
            // مسح وتنظيف المجلدات لتجنب تراكم الملفات القديمة
            FileUtils.deleteQuietly(new File(screenShotManager.screenPath));
            cleanTestOutPutDirectory();
            
            // إعادة إنشاء المجلدات النظيفة
            createOutPutDirectory();
            LogsManager.info("🧹 Directories cleaned and recreated successfully.");

            // تحضير الـ History لتقارير Allure
            System.out.println("⏳ Preparing Allure History...");
            AllureReportGenerator.copyHistory();

            propertyReader.loadProperties();
            AllureEnvironmentManager.setAllureEnvironment();
            
        } catch (Exception e) {
            LogsManager.error("Error during onExecutionStart: " + e.getMessage());
        }
    }

    @Override
    public void onExecutionFinish() {
<<<<<<< HEAD
        System.out.println("🏁 Test suite execution finished completely.");
        System.out.println("⏳ Waiting 2 seconds for TestNG to flush JSON results...");
=======
        System.out.println("Tests is Finished");
        System.out.println("Waiting for saving file.JSON");
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

<<<<<<< HEAD
        // تسجيل الـ Shutdown Hook لتوليد التقارير قبل إغلاق الـ JVM بسلام
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("[ShutdownHook] Starting Allure Report generation process...");
                AllureReportGenerator.copyHistory();
                AllureReportGenerator.generateReports(false); // توليد المجلد الكامل
                AllureReportGenerator.generateReports(true);  // توليد الـ Single File
                String finalReportName = AllureReportGenerator.renameReport();
                System.out.println("[ShutdownHook] Allure Report generated successfully as: " + finalReportName);

                // حماية الـ CI/CD (GitHub Actions): لا تفتح التقرير أبدًا إذا كان الكود يعمل على السيرفر
                if (System.getenv("GITHUB_ACTIONS") == null) {
                    System.out.println("[ShutdownHook] Local execution detected. Launching Allure Server...");
                    AllureReportGenerator.openReport(); 
                } else {
                    System.out.println("[ShutdownHook] CI/CD (GitHub Actions) detected. Skipping interactive server launch.");
                }
            } catch (Exception e) {
                System.err.println("[ShutdownHook] Error during Allure generation: " + e.getMessage());
            }
        }));
=======
        System.out.println("Generating HTML reports");

        AllureReportGenerator.generateReport(true);

        String finalReportName = AllureReportGenerator.renameReport();
        AllureReportGenerator.openReport(finalReportName);
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
    }

    @Override
    public void onTestStart(ITestResult result) {
<<<<<<< HEAD
        LogsManager.info("🚀 TestCase [" + result.getName() + "] is started");
=======
        LogsManager.info(" TestCase [" + result.getName() + "] is started");
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsManager.info(" TestCase [" + result.getName() + "] is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsManager.info(" TestCase [" + result.getName() + "] is failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsManager.info(" TestCase [" + result.getName() + "] is skipped");
    }

<<<<<<< HEAD
    @Override
    public void onStart(ITestContext context) {
        System.out.println("📂 [Tag <test>] Started Test Block: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📁 [Tag <test>] Finished Test Block: " + context.getName());
    }
=======
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // يمكن تركه فارغاً أو استخدامه لاحقاً
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            validation.assertAll();
            WebDriver driver = null;

            if (testResult.getInstance() instanceof webDriverProvider provider) {
                driver = provider.getWebDriver();
            }

            if (driver != null) {
                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS -> screenShotManager.takeFullScreen(driver, "success_" + testResult.getName());
                    case ITestResult.SKIP -> screenShotManager.takeFullScreen(driver, "skip_" + testResult.getName());
                    case ITestResult.FAILURE -> screenShotManager.takeFullScreen(driver, "fail_" + testResult.getName());
                }
                System.out.println("✨ [Method] Captured screenshot for: " + method.getTestMethod().getMethodName());
            }
        }
    }

    public void createOutPutDirectory() {
        FileUtiles.createDirectory(screenShotManager.screenPath);
    }

    public void cleanTestOutPutDirectory() {
        FileUtiles.cleanDirectory(AllureConstant.RESULT_FOLDER.toFile());
        FileUtiles.cleanDirectory(new File(screenShotManager.screenPath));
    }
}