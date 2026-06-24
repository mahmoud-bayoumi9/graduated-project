package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.drivers.GuiDriver;
import demoPlaze.utiles.waitManager;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import models.registerUser;
import org.aspectj.lang.annotation.Before;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
@Test
@Description("User should Redirect to confirmation Page")
@Owner("Abanob")
public class Register extends baseTest {
    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77").
                        put("os", System.getProperty("os.name"))
                        .put("URL", "http://testjs.site88.net")
                        .build());
    }
@BeforeMethod
    public void setup(){
    driver=new GuiDriver();
     new NavigationBar(driver).navigate();
}
    @Description("User should Redirect to confirmation Page")
public void RegisterWithValidData(){
// TC01
        String email="abanob.soror@gmail.com";
        String name="Abanob";
    new SignupOrLogin(driver).signupOrLogin()
            .EnterRegisterName(name).EnterRegisterEmail(email).clickRegisterButton();
    String actualUrl=driver.get().getCurrentUrl();
    String expected="https://automationexercise.com/signup";
    driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
}

@Description("User should not able to register without name")
    public void ValidateSignupWitheEmptyNameField(){
    // TC02
    new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterEmail("abanob.soryor@gmail.com").clickRegisterButton();
        String actualMsg=new SignupOrLogin(driver).validationNameMessage();
        String expected=validationErrorMessage.requiredName;
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }

    @Description("User should not able to register without Email")
    public void ValidateSignupWitheEmptyEmailField(){
        // TC03
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").clickRegisterButton();
        String actualMsg=new SignupOrLogin(driver).validationRegisterEmailMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }
    @Description("User should not able to register with Invalid Email Formate")
    public void ValidateSignupWithInvalidEmaiLFormat(){
        // TC04
        String email="abanob.sororgmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualMsg=new SignupOrLogin(driver).validationRegisterEmailMessage();
        String expected=validationErrorMessage.Emailvalidation(email);
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }
    @Description("User should not able to register with Invalid Email Formate")
    public void ValidateSignupWithWithSpaceInEmail(){
        // TC05
        String email="abanob.soror @gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualMsg=new SignupOrLogin(driver).validationRegisterEmailMessage();
        String expected="A part followed by '@' should not contain the symbol ' '.";
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with Email contains numbers")
    public void VerifySignupAcceptsEmailContainingNumericValues(){
//         TC06
        String email="abanob.soror2017@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with Email contains numbers")
    public void ValidateSignupWithSpacesOnInputInNameField(){
//        TC07
        String email="abanob.soror2017@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName(" ").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/Login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with name contains numbers")
    public void VerifySignupWithNumericValuesInUsername(){
//         TC08
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("Sorour123").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with name contains Special characters")
    public void VerifySignupWithSpecialCharactersValuesInUsername(){
//        TC09
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("@@@@@").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/Login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with name contains Arabic characters")
    public void VerifySignupWithArabicCharactersValuesInUsername(){
//        TC10
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("ابانوب").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to subscription with valid email")
    public void VerifySuccessfulSubscriptionWithValidEmail(){
//        TC37
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .SubscribeEmail(email).ClickSubscribe();
        String actualMessage=new SignupOrLogin(driver).validationSubscriptionValidEmMessage();
        String expected="You have been successfully subscribed!";
        driver.verfy().assertequal(actualMessage,expected,"not matched");

    }
    @Description("User should not able to subscription without valid email")
    public void ValidateSubscriptionWithEmptyEmailField(){
//      TC38
        new SignupOrLogin(driver).signupOrLogin()
                .ClickSubscribe();
        String expected=validationErrorMessage.requiredEmail;
        String actualMessage=new SignupOrLogin(driver).validationSubscriptionInvalidEmailMessage();
        driver.verfy().assertequal(actualMessage,expected,"not matched");

    }
    @Description("User should not able to subscription with invalid email format")
    public void ValidateSubscriptionWithInvalidEmailFomat(){
//   TC39
        new SignupOrLogin(driver).signupOrLogin().SubscribeEmail("ddd33")
                .ClickSubscribe();
        String expected="Please include an '@' in the email address. 'ddd33' is missing an '@'.";
        String actualMessage=new SignupOrLogin(driver).validationSubscriptionInvalidEmailMessage();
        driver.verfy().assertequal(actualMessage,expected,"not matched");

    }
    @Description("User should not able to register with registeredEmail")
    public void ValidateSignupusingAlreadyRegisteredEmailAddress(){
//        TC40
        String email="abanob.soror@gmail.com";
        String name="Abanob";
       SignupOrLogin AuthPage= new SignupOrLogin(driver).signupOrLogin();
        AuthPage.EnterRegisterName(name).EnterRegisterEmail(email).clickRegisterButton();
        AuthPage.verifyRegisterEmail("Email Address already exist!");
    }
    @Description("User should not able to login with incorrect password")
    public void ValidateLoginWithIncorrectPassword(){
//        TC43
        String email="abanob.soror@gmail.com";
        SignupOrLogin AuthPage=new SignupOrLogin(driver);AuthPage.signupOrLogin().EnterLoginEmail("abanob.soror@gmail.com").EnterLoginPassword("23sedrf").clickLoginButton();
        AuthPage.verifyLoginError("Your email or password is incorrect!");
    }
    @Description("User should not able to login with incorrect email")
    public void ValidateLoginWithIncorrectEmailAddress(){
//        TC44
        String email="abanob.soror@gmail.com";
        SignupOrLogin AuthPage=new SignupOrLogin(driver);AuthPage.signupOrLogin().EnterLoginEmail("abanob.mahmoud@gmail.com").EnterLoginPassword("01116871063").clickLoginButton();
        AuthPage.verifyLoginError("Your email or password is incorrect!");
    }

@AfterMethod
    public void tearDown(){
    driver.quit();
}
}
