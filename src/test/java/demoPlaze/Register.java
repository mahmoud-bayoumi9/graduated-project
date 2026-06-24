package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.customListener.testNGListener;
import demoPlaze.drivers.GuiDriver;
import demoPlaze.utiles.waitManager;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import models.registerUser;
import org.aspectj.lang.annotation.Before;
import org.testng.annotations.*;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
@Test
@Tag("userDetails")
@Description("User should Redirect to confirmation Page")
//    @Issue("jjjjj")
//    @Severity(SeverityLevel.CRITICAL)
//    @Link("DDDDDDDD")
@Owner("Abanob")
@Listeners(testNGListener.class)
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
    @Test
    @Tag("userDetails")
    @Description("User should Redirect to confirmation Page")
//    @Issue("jjjjj")
//    @Severity(SeverityLevel.CRITICAL)
//    @Link("DDDDDDDD")
    @Owner("Abanob")
public void RegisterWithValidData(){

        String email="abanob.soror@gmail.com";
        String name="Abanob";
    new SignupOrLogin(driver).signupOrLogin()
            .EnterRegisterName(name).EnterRegisterEmail(email).clickRegisterButton();
    String actualUrl=driver.get().getCurrentUrl();
    String expected="https://automationexercise.com/signup";
    driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
}@Description("User should not able to register without name")
    public void ValidateSignupWitheEmptyNameField(){
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterEmail("abanob.soryor@gmail.com").clickRegisterButton();
        String actualUrl=new SignupOrLogin(driver).validationNameMessage();
        String expected=validationErrorMessage.requiredName;
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should not able to register without Email")
    public void ValidateSignupWitheEmptyEmailField(){
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").clickRegisterButton();
        String actualUrl=new SignupOrLogin(driver).validationRegisterEmailMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should not able to register with Invalid Email Formate")
    public void ValidateSignupWithInvalidEmaiLFormat(){
        String email="abanob.sororgmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=new SignupOrLogin(driver).validationRegisterEmailMessage();
        String expected=validationErrorMessage.Emailvalidation(email);
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should not able to register with Invalid Email Formate")
    public void ValidateSignupWithWithSpaceInEmail(){
        String email="abanob.soror @gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=new SignupOrLogin(driver).validationRegisterEmailMessage();
        String expected="A part followed by '@' should not contain the symbol ' '.";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with Email contains numbers")
    public void VerifySignupAcceptsEmailContainingNumericValues(){
        String email="abanob.soror2017@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with Email contains numbers")
    public void ValidateSignupWithSpacesOnInputInNameField(){
        String email="abanob.soror2017@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName(" ").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/Login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with name contains numbers")
    public void VerifySignupWithNumericValuesInUsername(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("Sorour123").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with name contains Special characters")
    public void VerifySignupWithSpecialCharactersValuesInUsername(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("Sorour@@").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/Login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to register with name contains Arabic characters")
    public void VerifySignupWithArabicCharactersValuesInUsername(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .EnterRegisterName("ابانوب").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=driver.get().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }
    @Description("User should able to subscription with valid email")
    public void VerifySuccessfulSubscriptionWithValidEmail(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(driver).signupOrLogin()
                .SubscribeEmail(email).ClickSubscribe();
        String actualMessage=new SignupOrLogin(driver).validationSubscriptionValidEmMessage();
        String expected="You have been successfully subscribed!";
        driver.verfy().assertequal(actualMessage,expected,"not matched");

    }
    @Description("User should not able to subscription without valid email")
    public void ValidateSubscriptionWithEmptyEmailField(){

        new SignupOrLogin(driver).signupOrLogin()
                .ClickSubscribe();
        String expected=validationErrorMessage.requiredEmail;
        String actualMessage=new SignupOrLogin(driver).validationSubscriptionInvalidEmailMessage();
        driver.verfy().assertequal(actualMessage,expected,"not matched");

    }
    @Description("User should not able to subscription with invalid email format")
    public void ValidateSubscriptionWithInvalidEmailFomat(){

        new SignupOrLogin(driver).signupOrLogin().SubscribeEmail("ddd33")
                .ClickSubscribe();
        String expected="Please include an '@' in the email address. 'ddd33' is missing an '@'.";
        String actualMessage=new SignupOrLogin(driver).validationSubscriptionInvalidEmailMessage();
        driver.verfy().assertequal(actualMessage,expected,"not matched");

    }
    @Description("User should not able to register with registeredEmail")
    public void ValidateSignupusingAlreadyRegisteredEmailAddress(){

        String email="abanob.soror@gmail.com";
        String name="Abanob";
       SignupOrLogin AuthPage= new SignupOrLogin(driver).signupOrLogin();
        AuthPage.EnterRegisterName(name).EnterRegisterEmail(email).clickRegisterButton();
        AuthPage.verifyRegisterEmail("Email Address already exist!");
    }
    @Description("User should not able to login with incorrect password")
    public void ValidateLoginWithIncorrectPassword(){

        String email="abanob.soror@gmail.com";
        SignupOrLogin AuthPage=new SignupOrLogin(driver);AuthPage.signupOrLogin().EnterLoginEmail("abanob.soror@gmail.com").EnterLoginPassword("23sedrf").clickLoginButton();
        AuthPage.verifyLoginError("Your email or password is incorrect!");
    }
    @Description("User should not able to login with incorrect email")
    public void ValidateLoginWithIncorrectEmailAddress(){

        String email="abanob.soror@gmail.com";
        SignupOrLogin AuthPage=new SignupOrLogin(driver);AuthPage.signupOrLogin().EnterLoginEmail("abanob.mahmoud@gmail.com").EnterLoginPassword("01116871063").clickLoginButton();
        AuthPage.verifyLoginError("Your email or password is incorrect!");
    }

@AfterMethod
    public void tearDown(){
    driver.quit();
}
}
