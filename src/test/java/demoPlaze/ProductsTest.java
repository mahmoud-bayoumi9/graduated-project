package demoPlaze;

import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.ConfirmationPage;
import demoPlaze.Pages.ProductsPage;
import demoPlaze.Pages.SignupOrLogin;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.customListener.testNGListener;
import demoPlaze.drivers.GuiDriver;
import fakergenerate.generateConfirmationUser;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import org.testng.annotations.*;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
@Listeners(testNGListener.class)
public class ProductsTest extends baseTest {
    @Test
    @Description("User should Redirect to confirmation Page")

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
    }
    @Test
    public  void VerifyUserCanAddProductToCartSuccessfully(){
//        TC45
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product=
        new ProductsPage(driver);
        product.addProduct();
        product.verifyAddedBox("Added!");
        product.continueShopping();
    }

    @Test
    public  void VerifyUserCanRemoveProductFromCartSuccessfully(){
//        TC64
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product=
                new ProductsPage(driver);
        product.viewCart().deleteProduct();
    }

    @Test
    public  void VerifySearchFunctionalityUsingTShirtKeyword(){
//        TC47
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product=
                new ProductsPage(driver);
        product.search("T-Shirts");
        product.searchButton();
        product.verifySearchProduct("Premium Polo T-Shirts");
    }

    @Test
    public  void  VerifyUserCanAddMultipleQuantitiesOfTheSameProductToCart(){
//        TC48
        new NavigationBar(driver).clickOnProductsButton();
        ProductsPage product=
                new ProductsPage(driver);
        product.productDetails().addQuantityOfSameProduct("4");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

