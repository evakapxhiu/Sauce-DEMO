package test.cases;

import base.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

public class YourCartTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        loginPage.setTheLogInCredentials("standard_user","secret_sauce");
    }
    //----Check if we can click on the shopping cart icon and if we can access "your cart" page.----
    @Test
    public void testTheShoppingCartLink() {
        cartPage.clickShoppingCartLink();
        Assertions.assertTrue(cartPage.displayYourCartPage());
    }


}
