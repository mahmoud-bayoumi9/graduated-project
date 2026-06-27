package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.customListener.testNGListener;
import io.qameta.allure.*;
import models.registerUser;
import org.testng.annotations.*;

@Listeners(testNGListener.class)
public class RegisterTest extends baseTest { 

    @BeforeSuite
    void setAllureEnvironment() {
        com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter(
                com.google.common.collect.ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77")
                        .put("os", System.getProperty("os.name"))
                        .put("URL", "http://testjs.site88.net")
                        .build());
    }

    @BeforeMethod
    public void setupPages(){
        // 🚀 الحل هنا: نمرر getWebDriver() لضمان إرسال المتصفح الشغال والنشط حالياً
        new NavigationBar(getWebDriver()).navigate();
    }

    @Test(priority = 1)
    @Description("User should Redirect to confirmation Page")
    public void RegisterWithValidData(){
        String email="abanob.soror@gmail.com";
        String name="Abanob";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName(name).EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=getWebDriver().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 2)
    @Description("User should not able to register without name")
    public void ValidateSignupWitheEmptyNameField(){
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterEmail("abanob.soryor@gmail.com").clickRegisterButton();
        String actualMsg=new SignupOrLogin(getWebDriver()).validationNameMessage();
        String expected=validationErrorMessage.requiredName;
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 3)
    @Description("User should not able to register without Email")
    public void ValidateSignupWitheEmptyEmailField(){
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("abanob").clickRegisterButton();
        String actualMsg=new SignupOrLogin(getWebDriver()).validationRegisterEmailMessage();
        String expected=validationErrorMessage.requiredEmail;
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 4)
    @Description("User should not able to register with Invalid Email Formate")
    public void ValidateSignupWithInvalidEmaiLFormat(){
        String email="abanob.sororgmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualMsg=new SignupOrLogin(getWebDriver()).validationRegisterEmailMessage();
        String expected=validationErrorMessage.Emailvalidation(email);
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 5)
    @Description("User should not able to register with Invalid Email Formate")
    public void ValidateSignupWithWithSpaceInEmail(){
        String email="abanob.soror @gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualMsg=new SignupOrLogin(getWebDriver()).validationRegisterEmailMessage();
        String expected="A part followed by '@' should not contain the symbol ' '.";
        driver.verfy().assertequal(actualMsg,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 6)
    @Description("User should able to register with Email contains numbers")
    public void VerifySignupAcceptsEmailContainingNumericValues(){
        String email="abanob.soror2017@gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("abanob").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=getWebDriver().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 7)
    @Description("User should able to register with Email contains numbers")
    public void ValidateSignupWithSpacesOnInputInNameField(){
        String email="abanob.soror2017@gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName(" ").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=getWebDriver().getCurrentUrl();
        String expected="https://automationexercise.com/Login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 8)
    @Description("User should able to register with name contains numbers")
    public void VerifySignupWithNumericValuesInUsername(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("Sorour123").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=getWebDriver().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 9)
    @Description("User should able to register with name contains Special characters")
    public void VerifySignupWithSpecialCharactersValuesInUsername(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("@@@@@").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=getWebDriver().getCurrentUrl();
        String expected="https://automationexercise.com/Login";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 10)
    @Description("User should able to register with name contains Arabic characters")
    public void VerifySignupWithArabicCharactersValuesInUsername(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .EnterRegisterName("ابانوب").EnterRegisterEmail(email).clickRegisterButton();
        String actualUrl=getWebDriver().getCurrentUrl();
        String expected="https://automationexercise.com/signup";
        driver.verfy().assertequal(actualUrl,expected,"actual is not Matched"+expected);
    }

    @Test(priority = 11)
    @Description("User should able to subscription with valid email")
    public void VerifySuccessfulSubscriptionWithValidEmail(){
        String email="abanob.soror@gmail.com";
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .SubscribeEmail(email).ClickSubscribe();
        String actualMessage=new SignupOrLogin(getWebDriver()).validationSubscriptionValidEmMessage();
        String expected="You have been successfully subscribed!";
        driver.verfy().assertequal(actualMessage,expected,"not matched");
    }

    @Test(priority = 12)
    @Description("User should not able to subscription without valid email")
    public void ValidateSubscriptionWithEmptyEmailField(){
        new SignupOrLogin(getWebDriver()).signupOrLogin()
                .ClickSubscribe();
        String expected=validationErrorMessage.requiredEmail;
        String actualMessage=new SignupOrLogin(getWebDriver()).validationSubscriptionInvalidEmailMessage();
        driver.verfy().assertequal(actualMessage,expected,"not matched");
    }

    @Test(priority = 13)
    @Description("User should not able to subscription with invalid email format")
    public void ValidateSubscriptionWithInvalidEmailFomat(){
        new SignupOrLogin(getWebDriver()).signupOrLogin().SubscribeEmail("ddd33")
                .ClickSubscribe();
        String expected="Please include an '@' in the email address. 'ddd33' is missing an '@'.";
        String actualMessage=new SignupOrLogin(getWebDriver()).validationSubscriptionInvalidEmailMessage();
        driver.verfy().assertequal(actualMessage,expected,"not matched");
    }

    @Test(priority = 14)
    @Description("User should not able to register with registeredEmail")
    public void ValidateSignupusingAlreadyRegisteredEmailAddress(){
        String email="abanob.soror@gmail.com";
        String name="Abanob";
        SignupOrLogin AuthPage= new SignupOrLogin(getWebDriver()).signupOrLogin();
        AuthPage.EnterRegisterName(name).EnterRegisterEmail(email).clickRegisterButton();
        AuthPage.verifyRegisterEmail("Email Address already exist!");
    }

    @Test(priority = 15)
    @Description("User should not able to login with incorrect password")
    public void ValidateLoginWithIncorrectPassword(){
        SignupOrLogin AuthPage=new SignupOrLogin(getWebDriver());
        AuthPage.signupOrLogin().EnterLoginEmail("abanob.soror@gmail.com").EnterLoginPassword("23sedrf").clickLoginButton();
        AuthPage.verifyLoginError("Your email or password is incorrect!");
    }

    @Test(priority = 16)
    @Description("User should not able to login with incorrect email")
    public void ValidateLoginWithIncorrectEmailAddress(){
        SignupOrLogin AuthPage=new SignupOrLogin(getWebDriver());
        AuthPage.signupOrLogin().EnterLoginEmail("abanob.mahmoud@gmail.com").EnterLoginPassword("01116871063").clickLoginButton();
        AuthPage.verifyLoginError("Your email or password is incorrect!");
    }
}
