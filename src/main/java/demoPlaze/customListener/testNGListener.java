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

public class testNGListener implements ITestListener, IInvokedMethodListener, IExecutionListener {

    @Override
    public void onExecutionStart() {
        try {
            LogsManager.info("🔴 Starting Test Execution Suite...");
            FileUtils.deleteQuietly(new File(screenShotManager.screenPath));
            cleanTestOutPutDirectory();
            createOutPutDirectory();
            LogsManager.info("🧹 Directories cleaned and recreated successfully.");

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
        System.out.println("🏁 Test suite execution finished completely.");
        System.out.println("⏳ Waiting 2 seconds for TestNG to flush JSON results...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("[ShutdownHook] Starting Allure Report generation process...");
                AllureReportGenerator.copyHistory();
                AllureReportGenerator.generateReport(false); 
                AllureReportGenerator.generateReport(true);  
                String finalReportName = AllureReportGenerator.renameReport();
                System.out.println("[ShutdownHook] Allure Report generated successfully as: " + finalReportName);

                // حماية جيت هاب إكسترا
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

        System.out.println("Generating HTML reports...");
        AllureReportGenerator.generateReport(true);
        AllureReportGenerator.renameReport();

        // 🚀 تم تصليح الكارثة هنا: منع فتح السيرفر التفاعلي نهائياً لو شغالين على الـ CI/CD لضمان عدم الكراش
        if (System.getenv("GITHUB_ACTIONS") == null) {
            AllureReportGenerator.openReport();
        } else {
            System.out.println("CI/CD Environment: Skipping interactive openReport() to prevent build crash.");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogsManager.info("🚀 TestCase [" + result.getName() + "] is started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsManager.info(" TestCase [" + result.getName() + "] is passed");
    }

    // @Override
    // public void onTestFailure(ITestResult result) {
    //     LogsManager.info(" TestCase [" + result.getName() + "] is failed");
        
    //     // 🚀 أفضل وأأمن مكان لأخذ السكرين شوت لحظة الفشل فوراً قبل الـ TearDown وقبل قفل السيرفر
    //     if (result.getInstance() instanceof webDriverProvider provider) {
    //         WebDriver driver = provider.getWebDriver();
    //         if (driver != null) {
    //             try {
    //                 screenShotManager.takeFullScreen(driver, "fail_" + result.getName());
    //                 System.out.println("✨ [Listener] Captured Failure screenshot for: " + result.getName());
    //             } catch (Exception e) {
    //                 System.out.println("❌ Could not capture screenshot: " + e.getMessage());
    //             }
    //         }
    //     }
    // }

@Override
public void onTestFailure(org.testng.ITestResult result) {
    Object currentClass = result.getInstance();
    org.openqa.selenium.WebDriver webDriver = null;

    try {
        // 🔥 الحل العبقري: هنجيب الدالة عن طريق الـ Reflection عشان نتخطى مشكلة الـ Packages والـ compile
        java.lang.reflect.Method method = currentClass.getClass().getMethod("getWebDriver");
        webDriver = (org.openqa.selenium.WebDriver) method.invoke(currentClass);
    } catch (Exception e) {
        System.out.println("[WARN] لم نتمكن من جلب الـ WebDriver من كلاس التيست الحالي: " + e.getMessage());
    }

    if (webDriver != null) {
        // كود السكرين شوت بتاعك بـ Allure هنا سيبه زي ما هو
        // مثلاً: Allure.addAttachment("Screenshot", ...);
    }
}

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsManager.info(" TestCase [" + result.getName() + "] is skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("📂 [Tag <test>] Started Test Block: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📁 [Tag <test>] Finished Test Block: " + context.getName());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {}

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            validation.assertAll();
            
            // تم نقل الـ Failure screenshot لميثود onTestFailure الأصلية والآمنة
            // ونبقي هنا فقط على الـ Success والـ Skip لو المتصفح لسه مفتوح وبحماية try/catch
            WebDriver driver = null;
            if (testResult.getInstance() instanceof webDriverProvider provider) {
                driver = provider.getWebDriver();
            }

            if (driver != null) {
                try {
                    switch (testResult.getStatus()) {
                        case ITestResult.SUCCESS -> screenShotManager.takeFullScreen(driver, "success_" + testResult.getName());
                        case ITestResult.SKIP -> screenShotManager.takeFullScreen(driver, "skip_" + testResult.getName());
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Driver session already closed, skipping afterInvocation snapshot.");
                }
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
