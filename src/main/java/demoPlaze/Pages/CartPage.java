package demoPlaze.Pages;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.utiles.waitManager;
import org.openqa.selenium.By;

// public class CartPage {
//     private final GuiDriver driver;
//     public CartPage(GuiDriver driver) {
//         this.driver=driver;
//     }
//     private final By deleteProductBtn = By.xpath("//a[@class='cart_quantity_delete']");
//     public CartPage deleteProduct() {
//         org.openqa.selenium.WebElement element = new waitManager(driver.get()).fluentWait()
//                 .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(deleteProductBtn));
//         org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver.get();
//         js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
//         new waitManager(driver.get()).fluentWait()
//                 .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(element));
//         element.click();
//         return this;
//     }
// }
// package demoPlaze.Pages;

// import demoPlaze.drivers.GuiDriver;
// import demoPlaze.utiles.waitManager;
// import org.openqa.selenium.By;

public class CartPage {
    private final GuiDriver driver;

    public CartPage(GuiDriver driver) {
        this.driver = driver;
    }

    private final By deleteProductBtn = By.xpath("//a[@class='cart_quantity_delete']");

    public CartPage deleteProduct() {
        org.openqa.selenium.WebElement element = new waitManager(driver.get()).fluentWait()
                .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(deleteProductBtn));
        
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver.get();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        
        new waitManager(driver.get()).fluentWait()
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(element));
        
        // 🚀 الضغطة الـ JavaScript الأضمن على الـ Pipeline
        js.executeScript("arguments[0].click();", element); 
        return this;
    }
}
