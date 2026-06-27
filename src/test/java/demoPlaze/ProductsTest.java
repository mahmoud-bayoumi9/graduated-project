package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.ProductsPage;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.customListener.testNGListener;
import fakergenerate.generateConfirmationUser;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.Description;
import org.testng.annotations.*;

@Listeners(testNGListener.class)
public class ProductsTest extends baseTest {

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
    public void setupPages(){
        new NavigationBar(driver).navigate();
    }

    @Test(priority = 1)
    @Description("Verify User Can Add Product To Cart Successfully")
    public void VerifyUserCanAddProductToCartSuccessfully(){
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product = new ProductsPage(driver);
        product.addProduct();
        product.verifyAddedBox("Added!");
        product.continueShopping();
    }

    @Test(priority = 2)
    @Description("Verify User Can Remove Product From Cart Successfully")
    public void VerifyUserCanRemoveProductFromCartSuccessfully(){
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product = new ProductsPage(driver);
        product.viewCart().deleteProduct();
    }

    @Test(priority = 3)
    @Description("Verify Search Functionality Using TShirt Keyword")
    public void VerifySearchFunctionalityUsing TShirtKeyword(){
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product = new ProductsPage(driver);
        product.search("T-Shirts");
        product.searchButton();
        product.verifySearchProduct("Premium Polo T-Shirts");
    }

    @Test(priority = 4)
    @Description("Verify User Can Add Multiple Quantities Of The Same Product To Cart")
    public void VerifyUserCanAddMultipleQuantitiesOfTheSameProductToCart(){
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product = new ProductsPage(driver);
        product.productDetails().addQuantityOfSameProduct("4");
    }
}
