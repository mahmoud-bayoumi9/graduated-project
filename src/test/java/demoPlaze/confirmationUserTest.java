package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.ConfirmationPage;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.customListener.testNGListener;
import fakergenerate.generateConfirmationUser;
import fakergenerate.generateRegisterUser;
import io.qameta.allure.Step;
import org.testng.annotations.*;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Listeners(testNGListener.class)
public class confirmationUserTest extends baseTest {

    private models.confirmationUser conf;
    private models.registerUser user;

    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77")
                        .put("os", System.getProperty("os.name"))
                        .put("URL", "http://testjs.site88.net")
                        .build());
    }

    @BeforeMethod
    public void setup(){
        conf = generateConfirmationUser.returnedConfirmationUser();
        user = generateRegisterUser.returnedRegisterUser();

        new NavigationBar(driver).navigate();
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName(user.getName())
                .EnterRegisterEmail(user.getEmail())
                .clickRegisterButton();
    }

    @Test(priority = 1)
    @Step("user create acount with valid data")
    public void VerifyAccountCreationWithValidMandatorData(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 2)
    @Step("user create acount without select title")
    public void ValidateAccountCreationWithoutSelectingTitle(){
        new ConfirmationPage(driver).fillRegisterForm(" ",conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 3)
    @Step("user should not able to creat acount without password")
    public void ValidateAccountCreationWithoutPassword(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),"", conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        String PasswordValidation= new ConfirmationPage(driver).validationRegisterPasswordMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(PasswordValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 4)
    @Step("user should be able to create account without birth date ")
    public void ValidateAccountCreationWithoutDateOfBirth(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), "", "", "", conf.getFirstName(), conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 5)
    @Step("user should not able to creat acount without firstname")
    public void ValidateAccountCreationWithoutFirstName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), "", conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        String PasswordValidation= new ConfirmationPage(driver).validationRegisterFirstnameMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(PasswordValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 6)
    @Step("user should able to creat acount with Arabic Characters In firstname")
    public void VerifyAccountCreationWithArabicCharactersInFirstName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), "ابانوب", conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 7)
    @Step("user should not able to creat acount with special Characters In firstname")
    public void VerifyAccountCreationWithSpecialCharactersInFirstName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName()+"@", conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 8)
    @Step("user should not able to creat acount without lastname")
    public void ValidateAccountCreationWithoutLastName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), "", conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        String LastNameValidation= new ConfirmationPage(driver).validationRegisterLastnameMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(LastNameValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 9)
    @Step("user should able to creat acount with Arabic Characters In lastname")
    public void VerifyAccountCreationWithArabicCharactersInLastName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), "ابانوب", conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 10)
    @Step("user should not able to creat acount with special Characters In lasttname")
    public void VerifyAccountCreationWithSpecialCharactersInLastName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName()+"@@@@", conf.getCompany(), conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 11)
    @Step("user should able to creat acount without CompanyName")
    public void VerifyAccountCreationWithoutCompanyName(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),"", conf.getAddress(), conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 12)
    @Step("user should not able to creat acount without address1")
    public void VerifyAccountCreationWithoutAddress1(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), "", conf.
                getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        String expected=validationErrorMessage.requiredEmail;
        String actualMessage=new ConfirmationPage(driver).validationRegisterAddressMessage();
        driver.verfy().assertequal(actualMessage,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 13)
    @Step("user should able to creat acount without address linr 2")
    public void VerifyAccountCreationWithoutAddressLine2(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                "", conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/account_created";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 14)
    @Step("user should not able to creat acount without address state")
    public void VerifyAccountCreationWithoutState(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), "",conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate();
        String PasswordValidation= new ConfirmationPage(driver).validationRegisterStateMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(PasswordValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 15)
    @Step("user should not able to creat acount without address city")
    public void VerifyAccountCreationWithoutCity(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), conf.getState(),"", conf.getZipCode(), conf.getMobile()).clickCreate();
        String CityValidation= new ConfirmationPage(driver).validationRegisterCityMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(CityValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 16)
    @Step("user should not able to creat acount without Zip Code")
    public void VerifyAccountCreationWithoutZipCode(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), "", conf.getMobile()).clickCreate();
        String CityValidation= new ConfirmationPage(driver).validationRegisterZipCodeMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(CityValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 17)
    @Step("user should not able to creat acount without mobile number")
    public void VerifyAccountCreationWithoutMobileNumber(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), "").clickCreate();
        String CityValidation= new ConfirmationPage(driver).validationRegisterMobileNumberMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(CityValidation,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 18)
    @Step("user should not able to creat acount with mobile number with characters")
    public void VerifyMobileNumberFieldAcceptsAlphabeticCharacters(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), "dddf33f").clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 19)
    @Step("user should not able to creat acount with mobile number with special characters")
    public void VerifyMobilenumberFieldAcceptsSpecialCharacters(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), "  011#33@43").clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 20)
    @Step("user should not able to creat acount with mobile number with spaces between digits")
    public void VerifyMobilenumberFieldAcceptsSpacesBetweenDigits(){
        new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(),conf.getCompany(), conf.getAddress(),
                conf.getAddress2(), conf.getCountry(), conf.getState(),conf.getCity(), conf.getZipCode(), "  011  33   43").clickCreate();
        driver.verfy().isvisiable(ConfirmationPage.continueButton);
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
}
