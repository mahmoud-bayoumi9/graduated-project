import com.google.common.collect.ImmutableMap;
import demoPlaze.Pages.CartPage;
import demoPlaze.Pages.ProductsPage;
import demoPlaze.Pages.commponent.NavigationBar;
import demoPlaze.baseTest;
import demoPlaze.drivers.GuiDriver;
import fakergenerate.generateConfirmationUser;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class Cart extends baseTest {
    @Test
    @Tag("userDetails")
    @Description("User should Redirect to confirmation Page")
//    @Issue("jjjjj")
//    @Severity(SeverityLevel.CRITICAL)
//    @Link("DDDDDDDD")
    @Owner("Abanob")

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
    public void setup() {
        driver = new GuiDriver();
        NavigationBar navigate = new NavigationBar(driver);
        navigate.navigate();
        navigate.clickOnProductsButton();

        ProductsPage product = new ProductsPage(driver);
        product.addProduct();
        product.viewCart(); // الـ Setup تنتهي وأنت داخل صفحة السلة (CartPage)
    }

    @Test
    public void VerifyUserCanRemoveProductFromCartSuccessfully() {
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteProduct();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}



