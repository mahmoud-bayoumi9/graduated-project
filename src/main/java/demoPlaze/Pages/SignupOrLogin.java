
package demoPlaze.Pages;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.utiles.waitManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupOrLogin {
    private final GuiDriver driver;
    private final waitManager wait;

    public SignupOrLogin(GuiDriver driver) {
        this.driver = driver;
        this.wait = new waitManager(driver.get());
    }

    private final By RegisterName = By.name("name");
    private final By RegisterEmail = By.cssSelector("[data-qa='signup-email']");
    private final By RegisterButton = By.cssSelector("[data-qa='signup-button']");
    private final By LoginEmail = By.cssSelector("[data-qa='login-email']");
    private final By LoginPassword = By.cssSelector("[data-qa='login-password']");
    private final By LoginButton = By.cssSelector("[data-qa='login-button']");
    private final By SubscribeEmail = By.id("susbscribe_email");
    private final By SubscribeButton = By.id("subscribe");
    private final By errorLoginMessage = By.cssSelector(".login-form p");
    private final By errorRegisterMessage = By.xpath("//div[@class='signup-form']//p");
    private final By successMessage = By.id("success-subscribe");

    public SignupOrLogin signupOrLogin() {
        driver.brows().navigateToSpecificUrl("https://automationexercise.com/login");
        return this;
    }

    @Step("Enter login Email")
    public SignupOrLogin EnterLoginEmail(String email) {
        WebElement emailElement = wait.fluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(LoginEmail));
        emailElement.clear();
        emailElement.sendKeys(email);
        return this;
    }

    @Step("Enter LoginPassword")
    public SignupOrLogin EnterLoginPassword(String password) {
        driver.action().sendKey(LoginPassword, password);
        return this;
    }

    @Step("Click on LoginButton")
    public SignupOrLogin clickLoginButton() {
        driver.action().click(LoginButton);
        return this;
    }

    @Step("Enter Register Email")
    public SignupOrLogin EnterRegisterEmail(String email) {
        WebElement emailElement = wait.fluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(RegisterEmail));
        emailElement.clear();
        emailElement.sendKeys(email);
        return this;
    }

    @Step("Enter Register Name")
    public SignupOrLogin EnterRegisterName(String name) {
        WebElement nameElement = wait.fluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(RegisterName));
        nameElement.clear();
        nameElement.sendKeys(name);
        return this;
    }

    @Step("Click on RegisterButton")
    public ConfirmationPage clickRegisterButton() {
        WebElement regBtn = wait.fluentWait()
                .until(ExpectedConditions.elementToBeClickable(RegisterButton));
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver.get();
        js.executeScript("arguments[0].click();", regBtn);

        wait.fluentWait().until(ExpectedConditions.urlContains("/signup"));

        return new ConfirmationPage(driver);
    }

    @Step("Enter Subscribe Email")
    public SignupOrLogin SubscribeEmail(String email) {
        ((org.openqa.selenium.JavascriptExecutor) driver.get())
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        driver.action().sendKey(SubscribeEmail, email);
        return this;
    }

    @Step("Click on Subscribe Button")
    public SignupOrLogin ClickSubscribe() {
        ((org.openqa.selenium.JavascriptExecutor) driver.get())
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        driver.action().click(SubscribeButton);
        return this;
    }

    @Step("verify LoginMessage Error")
    public SignupOrLogin verifyLoginError(String expected) {
        String actual = driver.action().getText(errorLoginMessage);
        driver.verfy().assertequal(actual, expected, "actual not match with " + expected);
        return this;
    }

    @Step("verify registerd email")
    public SignupOrLogin verifyRegisterEmail(String expected) {
        wait.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(errorRegisterMessage));
        String actual = driver.action().getText(errorRegisterMessage);
        driver.verfy().assertequal(actual, expected, "actual not match with " + expected);
        return this;
    }

    @Step("verify Validation Message")
    public String validationNameMessage() {
        return driver.get().findElement(RegisterName).getAttribute("validationMessage");
    }

    @Step("verify Validation Message")
    public String validationRegisterEmailMessage() {
        return driver.get().findElement(RegisterEmail).getAttribute("validationMessage");
    }

    @Step("verify Validation Message")
    public String validationSubscriptionInvalidEmailMessage() {
        return driver.get().findElement(SubscribeEmail).getAttribute("validationMessage");
    }

    @Step("verify Validation Message")
    public String validationSubscriptionValidEmMessage() {
        return driver.get().findElement(successMessage).getText();
    }
}
