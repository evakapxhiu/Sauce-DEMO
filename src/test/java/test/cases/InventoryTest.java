package test.cases;

import base.tests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        loginPage.setTheLogInCredentials("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
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
        assertEquals(inventoryPage.getWebTitleProducts(), cartPage.getWebTitleProducts());
        assertEquals(inventoryPage.getWebDescriptionProducts(), cartPage.getWebDescriptionProducts());
        assertEquals(inventoryPage.getWebPriceProducts(), cartPage.getWebPriceProducts());
    }
    //DropDown menu with select options.
    //----A-Z and Z-A----
    @Test
    public void selectAlphabeticOrderOption() {
        String option = "Name (A to Z)";
        inventoryPage.selectFromDropDown(option);
        assertEquals(inventoryPage.getSelectedOptions().size(), 1);
        System.out.println(option);
        List<String> titles = inventoryPage.getWebTitleProducts();
        for (int i = 1; i < titles.size(); i++) {
            assertTrue(String.valueOf(titles.get(i - 1)).compareTo(String.valueOf(titles.get(i))) < 0);
        }
        System.out.println(titles);
    }
    @Test
    public void selectAlphabeticReversedOrderOption() {
        String option = "Name (Z to A)";
        inventoryPage.selectFromDropDown(option);
        assertEquals(inventoryPage.getSelectedOptions().size(), 1);
        List<String> titles = inventoryPage.getWebTitleProducts();
        for (int i = 1; i < titles.size(); i++) {
            assertTrue(String.valueOf(titles.get(i - 1)).compareTo(String.valueOf(titles.get(i))) > 0);
        }
    }
    //DropDown menu with select options.
    //----Price Low to high and high to low----
    @Test
    public void selectAscendingOrderOption() {
        String option = "Price (low to high)";
        inventoryPage.selectFromDropDown(option);
        List<Double> prices = inventoryPage.getWebPriceProducts();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) <= prices.get(i + 1));
        }
    }
    @Test
    public void selectDescendingOrderOption() {
        String option = "Price (high to low)";
        inventoryPage.selectFromDropDown(option);
        List<Double> prices = inventoryPage.getWebPriceProducts();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) >= prices.get(i + 1));
        }
    }
    //It's suppossed to fail.
    @Test
    public void resetAppState() {
        inventoryPage.resetAppState();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(inventoryPage.testResetAppState());
    }
}

