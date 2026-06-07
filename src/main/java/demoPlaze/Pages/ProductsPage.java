package demoPlaze.Pages;

import demoPlaze.drivers.GuiDriver;
import demoPlaze.utiles.waitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage {
    private final GuiDriver driver;
    public ProductsPage(GuiDriver driver) {
        this.driver=driver;
    }
    private  final By allProductHeader=By.xpath("/html/body/section[2]/div/div/div[2]/div/h2");
    private  final  By searchProductField=By.xpath("//*[@id=\"search_product\"]");
    private  final  By searchButton=By.xpath("//*[@id=\"submit_search\"]");
    private final By firstProductAddToCart = By.cssSelector(".features_items .col-sm-4:nth-child(3) .productinfo .add-to-cart");
    private  final  By addedBox=By.xpath("//*[@id=\"cartModal\"]/div/div/div[1]/div");
    private final  By continueShoppingButton=By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button");
    private final  By addedHeader=By.xpath("//*[@id=\"cartModal\"]/div/div/div[1]/h4");
    private final By searchedProductName = By.xpath("(//div[@class='productinfo text-center'])[1]/p");
    private final By viewCartLink = By.xpath("//div[@id='cartModal']//a[contains(., 'View Cart')]");
    private final By productDetails = By.xpath("(//a[contains(text(),'View Product')])[1]");
 public ProductsPage addProduct(){
     driver.action().click(firstProductAddToCart);
     return new ProductsPage(driver);
 }
    public ProductsPage continueShopping(){
        driver.action().click(continueShoppingButton);
        return this;
    }
    public productDetails productDetails(){
        driver.action().click(productDetails);
        return new productDetails(driver);
    }
    public CartPage viewCart() {
        org.openqa.selenium.WebElement element = new waitManager(driver.get()).fluentWait()
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(viewCartLink));
        try {
            driver.action().click(viewCartLink);
        } catch (Exception e) {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver.get();
            js.executeScript("arguments[0].click();", element);
        }

        return new CartPage(driver);
    }
    public ProductsPage searchButton(){
        driver.action().click(searchButton);
        return this;
    }
    public ProductsPage verifyAddedBox(String expected) {
        new waitManager(driver.get()).fluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(addedBox));
        driver.verfy().isvisiable(addedBox);
        String actual = driver.action().getText(addedHeader);
        driver.verfy().assertequal(actual, expected, "not matched");

        return new ProductsPage(driver);
    }
    public ProductsPage search(String searchKeyword){
     driver.action().sendKey(searchProductField,searchKeyword);
     return this;
    }
    public ProductsPage verifySearchProduct(String expected) {
        new waitManager(driver.get()).fluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(searchedProductName));
        driver.verfy().isvisiable(searchedProductName);
        String actual = driver.action().getText(searchedProductName);
        driver.verfy().assertequal(actual, expected, "not matched");

        return new ProductsPage(driver);
    }
}
