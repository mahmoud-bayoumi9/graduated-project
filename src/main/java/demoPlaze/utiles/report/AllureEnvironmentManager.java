//package demoPlaze.utiles.report;
//
//import com.github.automatedowl.tools.AllureEnvironmentWriter;
//import com.google.common.collect.ImmutableMap;
//import demoPlaze.utiles.logs.LogsManager;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
//import static demoPlaze.utiles.datareader.propertyReader.getProperty;
//
//public class AllureEnvironmentManager {
//    public static  void setAllureEnvironment() {
//        AllureEnvironmentWriter.allureEnvironmentWriter(
//                ImmutableMap.<String, String>builder()
//                        .put("OS",getProperty("os.name"))
//                        .put("Browser", getProperty("browserType"))
//                        .put("Browser.Version", "70.0.3538.77")
//                        .put("URL", "http://testjs.site88.net")
//                        .build(), getProperty("webBaseUrl")
//                        + "/allure-results/");
//        LogsManager.info("Allure management environment set");
//        AllureBinaryManager.downloadAndExtract();
//
//    }
//
//    @Test
//    void someTest() {
//        Assert.assertTrue(true);
//    }
//}
