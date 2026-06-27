// package demoPlaze.Pages;

// import demoPlaze.Pages.commponent.NavigationBar;
// import demoPlaze.drivers.GuiDriver;
// import io.qameta.allure.Step;
// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// import java.time.Duration;
// import java.time.Year;

// public class ConfirmationPage {
//     private final GuiDriver driver;
//     WebDriver driverr=new ChromeDriver();
//     public ConfirmationPage(GuiDriver driver) {
//         this.driver=driver;
//     }
//     public final By Mr=By.id("id_gender1");
//     public  final  By Mrs=By.id("id_gender2");
//     public  final  By Name=By.id("name");
//     public  final  By Email=By.id("email");
//     public  final  By Password=By.id("password");
//     public  final  By Days=By.id("days");
//     public  final  By Month=By.id("months");
//     public  final  By Years=By.id("years");
//     public  final  By newsletter=By.id("newsletter");
//     public  final  By optin=By.id("optin");
//     public  final  By first_name=By.id("first_name");
//     public  final  By last_name=By.id("last_name");
//     public  final  By company=By.id("company");
//     public  final  By address1=By.id("address1");
//     public  final  By address2=By.id("address2");
//     public  final  By country=By.id("country");
//     public  final  By state=By.id("state");
//     public  final  By city=By.id("city");
//     public  final  By zipcode=By.id("zipcode");
//     public  final  By mobileNumber=By.id("mobile_number");
//     private final By createAccountButton=By.cssSelector("[data-qa='create-account']");
//     public  final  By createdAccountMessage=By.tagName("b");
//     public static final By continueButton=By.cssSelector("[data-qa='continue-button']");


//     public ConfirmationPage chooseTitle(String title) {

//        if(title!=" "){
//            By titleLocator = By.xpath("//input[@value='" + title + "']");
//            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
//            WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(titleLocator));
//            titleElement.click();

//        }else {

//        }

//         return this;
//     }
// @Step("Fill register form")
//     public ConfirmationPage fillRegisterForm(String title,
//                                              String password,String day,String month,String years,String firstNmae,String lastName
//           ,String comp,String address,String adress22,String countryy,String statee,String citty,String zip,String mobile){
//     chooseTitle(title);

//     driver.action().sendKey(Password,password);
//     driver.action().dropDown(Days,day);
//     driver.action().dropDown(Month,month);
//     driver.action().dropDown(Years,years);
//     driver.action().click(newsletter);
//     driver.action().click(optin);
//     driver.action().sendKey(first_name,firstNmae);
//     driver.action().sendKey(last_name,lastName);
//     driver.action().sendKey(company,comp);
//     driver.action().sendKey(address1,address);
//     driver.action().sendKey(address2,adress22);
//     driver.action().dropDown(country,countryy);
//     driver.action().sendKey(state,statee);
//     driver.action().sendKey(city,citty);
//     driver.action().sendKey(zipcode,zip);
//     driver.action().sendKey(mobileNumber,mobile);

// return  this;

// }

// @Step("Click on create account Button")
//     public ConfirmationPage clickCreate(){

//     WebElement createAccountBtn = driver.get().findElement(createAccountButton);
//     WebElement newsletterCheckbox = driver.get().findElement(By.id("newsletter"));
//     JavascriptExecutor js = (JavascriptExecutor) driver.get();

//     js.executeScript("arguments[0].scrollIntoView({block: 'center'});", newsletterCheckbox);
//     js.executeScript("arguments[0].click();", newsletterCheckbox);

//     js.executeScript("arguments[0].click();", createAccountBtn);
// //    driver.action().click(createAccountButton);
//     return this;
// }
// public ConfirmationPage verifyCreatedAccountMessage(){
//     driver.verfy().isvisiable(createdAccountMessage);
//     return this;
// }
// public NavigationBar verifyContenueButton(){
//     driver.action().click(continueButton);
//     return new  NavigationBar(driver);

// }
//     @Step("verify Validation Message")
//     public  String validationRegisterPasswordMessage(){
//         return  driver.get().findElement(Password).getAttribute("validationMessage");
//     }
//     @Step("verify Validation firsname message")
//     public  String validationRegisterFirstnameMessage(){
//         return  driver.get().findElement(first_name).getAttribute("validationMessage");
//     }
//     @Step("verify Validation lastname message")
//     public  String validationRegisterLastnameMessage(){
//         return  driver.get().findElement(last_name).getAttribute("validationMessage");
//     }
//     @Step("verify Validation Address1 message")
//     public  String validationRegisterAddressMessage(){
//         return  driver.get().findElement(address1).getAttribute("validationMessage");
//     }
//     @Step("verify Validation State message")
//     public  String validationRegisterStateMessage(){
//         return  driver.get().findElement(state).getAttribute("validationMessage");
//     }
//     @Step("verify Validation City message")
//     public  String validationRegisterCityMessage(){
//         return  driver.get().findElement(city).getAttribute("validationMessage");
//     }
//     @Step("verify Validation City message")
//     public  String validationRegisterZipCodeMessage(){
//         return  driver.get().findElement(zipcode).getAttribute("validationMessage");
//     }
//     @Step("verify Validation Mobile Number message")
//     public  String validationRegisterMobileNumberMessage(){
//         return  driver.get().findElement(mobileNumber).getAttribute("validationMessage");
//     }
// }

package demoPlaze.Pages;

import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.drivers.GuiDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage {
    private final GuiDriver driver;

    // 🎯 تم حذف سطر WebDriver driverr = new ChromeDriver(); لمنع فتح متصفحات عشوائية وإيقاف الـ Response 500

    public ConfirmationPage(GuiDriver driver) {
        this.driver = driver;
    }

    public final By Mr = By.id("id_gender1");
    public final By Mrs = By.id("id_gender2");
    public final By Name = By.id("name");
    public final By Email = By.id("email");
    public final By Password = By.id("password");
    public final By Days = By.id("days");
    public final By Month = By.id("months");
    public final By Years = By.id("years");
    public final By newsletter = By.id("newsletter");
    public final By optin = By.id("optin");
    public final By first_name = By.id("first_name");
    public final By last_name = By.id("last_name");
    public final By company = By.id("company");
    public final By address1 = By.id("address1");
    public final By address2 = By.id("address2");
    public final By country = By.id("country");
    public final By state = By.id("state");
    public final By city = By.id("city");
    public final By zipcode = By.id("zipcode");
    public final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa='create-account']");
    public final By createdAccountMessage = By.tagName("b");
    public static final By continueButton = By.cssSelector("[data-qa='continue-button']");

    public ConfirmationPage chooseTitle(String title) {
        if (title != null && !title.trim().isEmpty() && !title.equals(" ")) {
            By titleLocator = By.xpath("//input[@value='" + title + "']");
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
            WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(titleLocator));
            titleElement.click();
        }
        return this;
    }

    @Step("Fill register form")
    public ConfirmationPage fillRegisterForm(String title, String password, String day, String month, String years, 
                                             String firstNmae, String lastName, String comp, String address, 
                                             String adress22, String countryy, String statee, String citty, 
                                             String zip, String mobile) {
        chooseTitle(title);

        driver.action().sendKey(Password, password);
        driver.action().dropDown(Days, day);
        driver.action().dropDown(Month, month);
        driver.action().dropDown(Years, years);
        
        // التشييك على الـ Checkboxes
        driver.action().click(newsletter);
        driver.action().click(optin);
        
        driver.action().sendKey(first_name, firstNmae);
        driver.action().sendKey(last_name, lastName);
        driver.action().sendKey(company, comp);
        driver.action().sendKey(address1, address);
        driver.action().sendKey(address2, adress22);
        driver.action().dropDown(country, countryy);
        driver.action().sendKey(state, statee);
        driver.action().sendKey(city, citty);
        driver.action().sendKey(zipcode, zip);
        driver.action().sendKey(mobileNumber, mobile);

        return this;
    }

    @Step("Click on create account Button")
    public ConfirmationPage clickCreate() {
        WebElement createAccountBtn = driver.get().findElement(createAccountButton);
        JavascriptExecutor js = (JavascriptExecutor) driver.get();

        // عمل Scroll لزرار الـ Create مباشرة للتأكد من رؤيته وضغطه بأمان على الـ Pipeline
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", createAccountBtn);
        js.executeScript("arguments[0].click();", createAccountBtn);

        return this;
    }

    public ConfirmationPage verifyCreatedAccountMessage() {
        driver.verfy().isvisiable(createdAccountMessage);
        return this;
    }

    public NavigationBar verifyContenueButton() {
        driver.action().click(continueButton);
        return new NavigationBar(driver);
    }

    @Step("verify Validation Message")
    public String validationRegisterPasswordMessage() {
        return driver.get().findElement(Password).getAttribute("validationMessage");
    }

    @Step("verify Validation firsname message")
    public String validationRegisterFirstnameMessage() {
        return driver.get().findElement(first_name).getAttribute("validationMessage");
    }

    @Step("verify Validation lastname message")
    public String validationRegisterLastnameMessage() {
        return driver.get().findElement(last_name).getAttribute("validationMessage");
    }

    @Step("verify Validation Address1 message")
    public String validationRegisterAddressMessage() {
        return driver.get().findElement(address1).getAttribute("validationMessage");
    }

    @Step("verify Validation State message")
    public String validationRegisterStateMessage() {
        return driver.get().findElement(state).getAttribute("validationMessage");
    }

    @Step("verify Validation City message")
    public String validationRegisterCityMessage() {
        return driver.get().findElement(city).getAttribute("validationMessage");
    }

    @Step("verify Validation ZipCode message")
    public String validationRegisterZipCodeMessage() {
        return driver.get().findElement(zipcode).getAttribute("validationMessage");
    }

    @Step("verify Validation Mobile Number message")
    public String validationRegisterMobileNumberMessage() {
        return driver.get().findElement(mobileNumber).getAttribute("validationMessage");
    }
}










