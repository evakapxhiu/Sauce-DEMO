package test.cases;

import base.tests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.InventoryPage;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.*;
public class LogInTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }
    //----User is able to log in----
    @Test
    public void logInIsSuccessful() {
        loginPage.setTheLogInCredentials("standard_user", "secret_sauce");
        assertTrue(loginPage.inventoryPageIsDisplayed());
    }
    //----Test the locked_out_user username----
    @Test
    public void userIsLockedOut() {
        loginPage.userIsLockedOut("locked_out_user", "secret_sauce");
        WebElement lockedOutUserErrorMEssage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        assertEquals("Epic sadface: Sorry, this user has been locked out.", lockedOutUserErrorMEssage.getText());
    }
    //----Username is required----
    @Test
    public void usernameIsRequired() {
        loginPage.usernameIsRequired("secret_sauce");
        String expectedMessage = "Epic sadface: Username is required";
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        assertEquals(expectedMessage, errorMessage.getText());
    }
    @Test
    public void passwordIsRequired() {
        loginPage.passwordIsRequired("standard_user");
        assertFalse(loginPage.inventoryPageIsDisplayed());
    }
}
