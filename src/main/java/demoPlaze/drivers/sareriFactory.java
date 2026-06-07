//package demoPlaze.drivers;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.safari.SafariOptions;
//
//public class sareriFactory extends  abstractDriver {
//    private SafariOptions getSafariOptions() {
//        SafariOptions option = new SafariOptions();
//        option.setCapability("browserName", "safari");
//
//        return option;
//    }
//    @Override
//    public WebDriver createDriver() {
//        return new SafariDriver(getSafariOptions());
//    }
//}
