// package demoPlaze.Pages;

// import demoPlaze.drivers.GuiDriver;
// import demoPlaze.utiles.waitManager;
// import org.openqa.selenium.By;

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
import demoPlaze.baseTest;
import demoPlaze.Pages.CartPage;
import demoPlaze.Pages.commponent.NavigationBar;
import org.testng.annotations.Test;

public class Cart extends baseTest {

    @Test(description = "User should be able to delete product from Cart successfully")
    public void VerifyDeleteProductFromCart() {
        // 1️⃣ التنقل للموقع والضغط على زرار الكارت من الملاحة
        new NavigationBar(driver)
                .navigate()
                .clickOnCartButton();

        // 2️⃣ 🎯 السطر 38 المصلح: إنشاء متغير منفصل تماماً للـ CartPage 
        // بدلاً من إعادة التعيين على الـ driver الأساسي لحل الـ Compilation Error
        CartPage cartPage = new CartPage(driver);
        
        // 3️⃣ تنفيذ عملية حذف المنتج
        cartPage.deleteProduct();
        
        // هنا تقدر تضيف الـ Assertions بتاعتك للتأكد إن الكارت بقى فاضي، كمثال:
        // driver.verfy().assertequal(cartPage.isEmpty(), true, "Cart is not empty!");
    }
}
