package demoPlaze.Pages.commponent;

import demoPlaze.Pages.*;
import demoPlaze.drivers.GuiDriver;
import demoPlaze.utiles.datareader.propertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class NavigationBar {
    private final GuiDriver driver;
    public NavigationBar(GuiDriver driver){
        this.driver=driver;
    }
    private final By homeButton=By.xpath( "//a[li[text()='home']]");
    private final By LogoutButton = By.xpath("//a[contains(., 'Logout')]");
    private final By productButton = By.xpath("//a[contains(text(), 'Products')]");
    private final By Cart=By.xpath( "//a[li[text()='Cart']]");
    private final By  SignupOrLogin=By.cssSelector("a[href='/login']");
    private final By  TestCases=By.xpath( "//a[li[text()=' Test Cases']]");
    private final By  APITesting=By.xpath( "//a[li[text()=' API Testing']]");
    private final By  VideoTutorials=By.xpath( "//a[li[text()=' Video Tutorials']]");
    private final By  Contactus=By.xpath( "//a[li[text()=' Contact us']]");
    private final By  deleteAccount=By.xpath( "//a[li[text()='Delete Account']]");
    private final By   LoggedInAs =By.tagName( "b");
    @Step("Navigate To HomePage")
    public NavigationBar navigate(){
        driver.brows().navigateToSpecificUrl("https://automationexercise.com/");
        return this;
    }
    @Step("Click on Home Button")
    public NavigationBar clickOnHomeButton(){
        driver.action().click(homeButton);
        return this;
    }
    @Step("click On Product Button")
    public ProductsPage clickOnProductsButton(){
        driver.action().click(productButton);
        return new  ProductsPage(driver);
    }
    @Step("click On Cart Button")
    public CartPage clickOnCartButton(){
        driver.action().click(Cart);
        return new  CartPage(driver);
    }
    @Step("click On Sigin/SignUp Button")
    public SignupOrLogin clickSignupAndloginButton(){
        driver.action().click(SignupOrLogin);
        return new SignupOrLogin(driver);
    }
    @Step("click On ApiTesting Button")
    public ApiTestingPage clickOnApiTestingButton(){
        driver.action().click(APITesting);
        return new ApiTestingPage(driver);
    }
    @Step("click On LogOut Button")
    public SignupOrLogin clickOnLogOutButton(){
        driver.action().click(LogoutButton);
        return new SignupOrLogin(driver);
    }
    @Step("click On  VideoTutorials Button")
    public VideoTutorialsPage clickOnVideoTutorialsPageButton(){
        driver.action().click(VideoTutorials);
        return new  VideoTutorialsPage(driver);
    }
    @Step("click On  Contactus Button")
    public ContactusPage clickOnContactusButton(){
        driver.action().click(Contactus);
        return new  ContactusPage(driver);
    }
    @Step("click On  deleteAccount Button")
    public deleteAccountPage clickOndeleteAccountButton(){
        driver.action().click(deleteAccount);
        return new  deleteAccountPage(driver);
    }
    @Step("verify login as label")
    public NavigationBar verifyLoginAs(String name){
        String actual=driver.action().getText(LoggedInAs);
        driver.verfy().assertequal(actual,name,"Actual is not match"+name);
        return this;
    }




















}
