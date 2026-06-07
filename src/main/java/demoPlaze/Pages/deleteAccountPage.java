package demoPlaze.Pages;

import demoPlaze.drivers.GuiDriver;
import org.openqa.selenium.By;

public class deleteAccountPage {
    private final GuiDriver driver;
    public deleteAccountPage(GuiDriver driver) {
        this.driver=driver;
    }
    private  final By deleteBuTTOn=By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    private final By deletedParagraph = By.xpath("//h2[@data-qa='account-deleted']/b");
    private  final  By continueButton=By.xpath("//*[@id=\"form\"]/div/div/div/div/a");
    public  deleteAccountPage deleteAccount(){
        driver.action().click(deleteBuTTOn);
        return this;

    }
    public deleteAccountPage verifyVisibleDeletedParagraph(){
        driver.verfy().isvisiable(deletedParagraph);
        return this;
    }
    public home clickOnContinue(){
        driver.action().click(continueButton);
        return  new home(driver);
    }
}
