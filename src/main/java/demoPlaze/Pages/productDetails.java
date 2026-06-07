package demoPlaze.Pages;

import demoPlaze.drivers.GuiDriver;
import org.openqa.selenium.By;

public class productDetails {
    private final GuiDriver driver;
    public productDetails (GuiDriver driver) {
        this.driver=driver;
    }
    private final By quantityInputField = By.xpath("//input[@id='quantity']");
    private final By addToCartBtn = By.cssSelector("button.cart");
    public productDetails addQuantityOfSameProduct(String quantity){
        driver.get().findElement(quantityInputField).clear();
        driver.action().sendKey(quantityInputField,quantity);
        driver.action().click(addToCartBtn);
        return this;

    }

}
