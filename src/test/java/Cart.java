import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.CartPage;
import demoPlaze.Pages.ProductsPage;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.baseTest;
import demoPlaze.customListener.testNGListener;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import org.testng.annotations.*;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Listeners(testNGListener.class)
public class Cart extends baseTest {

    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77")
                        .put("os", System.getProperty("os.name"))
                        .put("URL", "http://testjs.site88.net")
                        .build());
    }

    @Test
    @Tag("userDetails")
    @Description("User should be able to remove product from cart successfully")
    @Owner("Abanob")
    public void VerifyUserCanRemoveProductFromCartSuccessfully() {
        
        NavigationBar navigate = new NavigationBar(driver);
        navigate.clickOnProductsButton();

        ProductsPage product = new ProductsPage(driver);
        product.addProduct();
        product.viewCart(); 

        CartPage cartPage = new CartPage(driver);
        cartPage.deleteProduct();
    }
}
