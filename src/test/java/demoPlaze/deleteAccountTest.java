package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.ConfirmationPage;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.Pages.deleteAccountPage;
import demoPlaze.customListener.testNGListener;
import fakergenerate.generateConfirmationUser;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.Description;
import org.testng.annotations.*;

@Listeners(testNGListener.class)
public class deleteAccountTest extends baseTest {

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

    models.confirmationUser conf = generateConfirmationUser.returnedConfirmationUser();

    @BeforeMethod
    public void setup() {
        new NavigationBar(driver).navigate();
        SignupOrLogin auth = new SignupOrLogin(driver);
        auth.signupOrLogin()
                .EnterRegisterName(conf.getName())
                .EnterRegisterEmail(conf.getEmail())
                .clickRegisterButton();
                
        new ConfirmationPage(driver).fillRegisterForm(
                conf.getTitle(), conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), 
                conf.getFirstName(), conf.getLastName(), conf.getCompany(), conf.getAddress(), 
                conf.getAddress2(), conf.getCountry(), conf.getState(), conf.getCity(), 
                conf.getZipCode(), conf.getMobile()).clickCreate().verifyContenueButton();
    }

    @Test(priority = 1)
    @Description("User should able to delete account")
    @Tag("userDetails")
    @Owner("Abanob")
    public void VerifyUserCanDeleteAccountSuccessfully() {
        deleteAccountPage delete = new deleteAccountPage(driver);
        delete.deleteAccount();
        delete.verifyVisibleDeletedParagraph();
    }

    @Test(priority = 2)
    @Description("User should able to logout and login again successfully")
    @Tag("userDetails")
    @Owner("Abanob")
    public void VerifyUserCanLogoutAndLoginAgainSuccessfully() {
        NavigationBar navigate = new NavigationBar(driver);
        navigate.clickOnLogOutButton()
                .EnterLoginEmail(conf.getEmail())
                .EnterLoginPassword(conf.getPassword())
                .clickLoginButton();
        navigate.verifyLoginAs(conf.getName());
    }
}
