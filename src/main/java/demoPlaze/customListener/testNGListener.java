//package demoPlaze.customListener;
//
//import demoPlaze.FileUtiles;
//import demoPlaze.drivers.webDriverProvider;
//import demoPlaze.media.screenShotManager;
//import demoPlaze.utiles.datareader.propertyReader;
//import demoPlaze.utiles.logs.LogsManager;
//import demoPlaze.utiles.report.AllureConstant;
////import demoPlaze.utiles.report.AllureEnvironmentManager;
//import demoPlaze.utiles.report.AllureEnvironmentManager;
//import demoPlaze.utiles.report.AllureReportGenerator;
//import demoPlaze.validations.validation;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.WebDriver;
//import org.testng.IExecutionListener;
//import org.testng.IInvokedMethod;
//import org.testng.IInvokedMethodListener;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import java.io.File;
//public class testNGListener implements ITestListener, IInvokedMethodListener, IExecutionListener {
//    @Override
//    public void onExecutionStart() {
//     try {
//         LogsManager.info("started");
//         cleanTestOutPutDirectory();
//         LogsManager.info("directories cleaned");
//         creatOutPutDirectory();
//     } catch (Exception e) {
//         LogsManager.error(e.getMessage());
//     }
//        LogsManager.info("directory created");
//        AllureEnvironmentManager.setAllureEnvironment();
//
//    }
//
//    @Override
//    public void onExecutionFinish() {
//        AllureReportGenerator.copyHistory();
//        AllureReportGenerator.generateReports(false);
//
//        AllureReportGenerator.generateReports(true);
//        AllureReportGenerator.renameReport();
//        AllureReportGenerator.openReport();
//        LogsManager.info("execution finished");
//    }
//    @Override
//    public void onTestStart(ITestResult result) {
//        LogsManager.info("🔴 TestCase [" + result.getName() + "] is started");
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        LogsManager.info("🟢 TestCase [" + result.getName() + "] is passed");
//    }
//    @Override
//    public void onTestFailure(ITestResult result) {
//        LogsManager.info("❌ TestCase [" + result.getName() + "] is failed");
//    }
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        LogsManager.info("🟡 TestCase [" + result.getName() + "] is skipped");
//    }
//    @Override
//    public void onStart(ITestContext context) {
//        System.out.println("📂 [Tag <test>] بدأ تنفيذ الـ Test Block: " + context.getName());
//    }
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("📁 [Tag <test>] انتهى تنفيذ الـ Test Block: " + context.getName());
//    }
//    @Override
//    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
//    }
//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//        WebDriver driver=null;
//        if (method.isTestMethod()){
//            validation.assertAll();
//            if (testResult.getInstance() instanceof webDriverProvider provider){
//                driver=provider.getWebDriver();
//            }
//            switch (testResult.getStatus()){
//                case ITestResult.SKIP -> screenShotManager.takeFullScreen(driver,"skip_"+testResult.getName());
//                case ITestResult.SUCCESS ->screenShotManager.takeFullScreen(driver,"success_"+testResult.getName());
//                case ITestResult.FAILURE ->screenShotManager.takeFullScreen(driver,"fail_"+testResult.getName());
//            }
//        }
//
//    }
//    public void createOutPutDirectory() {
//        FileUtiles.createDirectory(screenShotManager.screenPath);
//    }
//    public void cleanTestOutPutDirectory(){
//        FileUtiles.cleanDirectory(AllureConstant.RESULT_FOLDER.toFile());
//        FileUtiles.cleanDirectory(new File(screenShotManager.screenPath));
////        FileUtiles.cleanDirectory(new File(LogsManager.));
//    }
//    public void creatOutPutDirectory(){
//        FileUtiles.createDirectory(screenShotManager.screenPath);
//
//    }
//}
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
            LogsManager.info("started");
            cleanTestOutPutDirectory();
            LogsManager.info("directories cleaned");
            creatOutPutDirectory();
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
        LogsManager.info("directory created");
        AllureEnvironmentManager.setAllureEnvironment();
    }

    @Override
    public void onExecutionFinish() {
        LogsManager.info("Registering Allure Report Generator Hook...");

        // استخدام الـ Shutdown Hook لإجبار جافا والويندوز على إنهاء توليد التقرير قبل إغلاق البرنامج
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("[ShutdownHook] Starting Allure Process...");
                AllureReportGenerator.copyHistory();
                AllureReportGenerator.generateReports(false); // توليد الفولدر الكامل
                AllureReportGenerator.generateReports(true);  // توليد الـ Single File
                AllureReportGenerator.renameReport();

                System.out.println("[ShutdownHook] Launching Allure Server...");
                AllureReportGenerator.openReport(); // فتح السيرفر التفاعلي
            } catch (Exception e) {
                System.err.println("[ShutdownHook] Error: " + e.getMessage());
            }
        }));

        LogsManager.info("execution finished method completed");
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
        WebDriver driver = null;
        if (method.isTestMethod()) {
            validation.assertAll();
            if (testResult.getInstance() instanceof webDriverProvider provider) {
                driver = provider.getWebDriver();
            }
            switch (testResult.getStatus()) {
                case ITestResult.SKIP -> screenShotManager.takeFullScreen(driver, "skip_" + testResult.getName());
                case ITestResult.SUCCESS -> screenShotManager.takeFullScreen(driver, "success_" + testResult.getName());
                case ITestResult.FAILURE -> screenShotManager.takeFullScreen(driver, "fail_" + testResult.getName());
            }
        }
    }

    public void cleanTestOutPutDirectory() {
        FileUtiles.cleanDirectory(AllureConstant.RESULT_FOLDER.toFile());
        FileUtiles.cleanDirectory(new File(screenShotManager.screenPath));
    }

    public void creatOutPutDirectory() {
        FileUtiles.createDirectory(screenShotManager.screenPath);
    }
}