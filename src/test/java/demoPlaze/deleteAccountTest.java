package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.ConfirmationPage;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.Pages.deleteAccountPage;
import demoPlaze.customListener.testNGListener;
import demoPlaze.drivers.GuiDriver;
import fakergenerate.generateConfirmationUser;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import org.testng.annotations.*;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
<<<<<<< HEAD:src/test/java/demoPlaze/deleteAccountTest.java
@Listeners(testNGListener.class)
public class deleteAccountTest extends baseTest{
    @Test
=======

public class deleteAccount extends baseTest{
    @Test(description= "User should Redirect to confirmation Page")
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9:src/test/java/demoPlaze/deleteAccount.java
    @Tag("userDetails")
    @Owner("Abanob")

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
    models.confirmationUser conf = generateConfirmationUser.returnedConfirmationUser();
        @BeforeMethod
        public void setup(){
            driver=new GuiDriver();
            String email="abanob.soror@gmail.com";
            new NavigationBar(driver).navigate();
            SignupOrLogin auth=new SignupOrLogin(driver);
            auth.signupOrLogin()
                    .EnterRegisterName(conf.getName()).EnterRegisterEmail(conf.getEmail()).clickRegisterButton();
           new ConfirmationPage(driver).fillRegisterForm(conf.getTitle(),conf.getPassword(), conf.getDay(), conf.getMonth(), conf.getYear(), conf.getFirstName(), conf.getLastName(), conf.getCompany(), conf.getAddress(), conf.
                            getAddress2(), conf.getCountry(),
                    conf.getState(),conf.getCity(), conf.getZipCode(), conf.getMobile()).clickCreate().verifyContenueButton();
//            String actualUrl=driver.get().getCurrentUrl();
//            auth.signupOrLogin().EnterLoginEmail(conf.getEmail()).EnterLoginPassword(conf.getPassword()).clickLoginButton();
        }
    @Description("User should able to delete account")
    @Test
    public void VerifyUserCanDeleteAccountSuccessfully(){
//        TC41
        deleteAccountPage  delete= new deleteAccountPage(driver);
        delete.deleteAccount();
        delete.verifyVisibleDeletedParagraph();
    }


    @Description("User should able to logout and login again successfully")
    @Test
    public void VerifyUserCanLogoutAndLoginAgainSuccessfully(){
//            TC42
         NavigationBar navigate=
        new NavigationBar(driver);navigate.clickOnLogOutButton().EnterLoginEmail(conf.getEmail()).EnterLoginPassword(conf.getPassword()).clickLoginButton();
        navigate.verifyLoginAs(conf.getName());

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
