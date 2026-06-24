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
        System.out.println("Tests is Finished");
        System.out.println("Waiting for saving file.JSON");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Generating HTML reports");

        AllureReportGenerator.generateReport(true);

        String finalReportName = AllureReportGenerator.renameReport();
        AllureReportGenerator.openReport(finalReportName);
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogsManager.info(" TestCase [" + result.getName() + "] is started");
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
        }
    }

    public void createOutPutDirectory() {
        FileUtiles.createDirectory(screenShotManager.screenPath);
    }
}