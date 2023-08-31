package test.cases;

import base.tests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        loginPage.setTheLogInCredentials("standard_user","secret_sauce");
        inventoryPage = new InventoryPage(driver);
        cartPage=new CartPage(driver);
    }
    @Test
    public void testClickAddToCartButton() {
        inventoryPage.clickAddToCartButtons();
        assertTrue(inventoryPage.isRemoveButtonVisible());
    }
    @Test
    public void testRemoveButton() {
        inventoryPage.clickARemoveButtons();
        assertTrue(inventoryPage.isAddToCartButtonVisible());
    }
    @Test
    public void addToCartThreeProducts() {
        inventoryPage.addToCartThreeProducts();
        assertTrue(inventoryPage.isRemoveButtonVisible());
    }
    @Test
    public void userIsLoggedOut() {
        inventoryPage.clickLogOut();
        assertTrue(inventoryPage.logInPage());
    }
    //-----***********************************************************************-----
    //I tried something,but it's not correct. I will delete it later or do some refactor.
    @Test
    public void addToCartSomeProducts() {
      inventoryPage.addToCartSomeProducts();
      cartPage.clickShoppingCartLink();
      assertEquals(inventoryPage.getWebTitleProducts(),cartPage.getWebTitleProducts());
      assertEquals(inventoryPage.getWebDescriptionProducts(),cartPage.getWebDescriptionProducts());
      assertEquals(inventoryPage.getWebPriceProducts(),cartPage.getWebPriceProducts());
    }
}
