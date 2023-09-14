package test.cases;

import base.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YourCartTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        inventoryPage = new InventoryPage(driver);
        loginPage.setTheLogInCredentials("standard_user", "secret_sauce");
    }

    //----Check if we can click on the shopping cart icon and if we can access "your cart" page.----
    @Test
    public void testTheShoppingCartLink() {
        cartPage.clickShoppingCartLink();
        assertTrue(cartPage.displayYourCartPage());
    }

    @Test
    public void checkOutYourInformation() {
        inventoryPage.addToCartSomeProducts();
        cartPage.checkOutYourInformation("Eva", "Kapxhiu", "G001");
        assertTrue(cartPage.overviewOfYourOrder());
        cartPage.clickFinishButton();
        WebElement completeOrderMessage = driver.findElement(By.className("complete-header"));
        assertEquals("Thank you for your order!", completeOrderMessage.getText());
    }

    @Test
    public void checkOutYourInfoWithEmptyFields() {
        inventoryPage.addToCartSomeProducts();
        cartPage.checkOutYourInformation();
        assertEquals("Error: First Name is required", driver.findElement(By.xpath("//h3[@data-test='error']")).getText());
    }
    @Test
    public void verifyTheTotalPayment() {
        inventoryPage.clickAddToCartButtons();
        cartPage.checkOutYourInformation("Eva", "Kapxhiu", "G001");
        String totalString = driver.findElement(By.className("summary_total_label")).getText();
        double total = Double.parseDouble(totalString.replaceAll("[^0-9.]",""));
        assertEquals(cartPage.getTheTotalPaymentOfTheOrder(),total);
        cartPage.clickFinishButton();
    }
}
