package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(how = How.ID,
    using = "user-name")
    private WebElement username;
    @FindBy(how = How.ID,
    using = "password")
    private WebElement password;
    @FindBy(how = How.ID,
    using = "login-button")
    private WebElement loginButton;
    //This is to test the login.
    // After you log in you are able to see the inventory page.
    @FindBy(how = How.CLASS_NAME,
    using = "inventory_container")
    private WebElement inventoryContainer;
    //----User is able to log in successfully with username and pass.----
    public void setTheLogInCredentials(String name,String pass) {
        username.sendKeys(name);
        password.sendKeys(pass);
        clickLoginButton();
    }
    public InventoryPage clickLoginButton() {
        loginButton.click();
        return new InventoryPage(webDriver);
    }
    public boolean inventoryPageIsDisplayed() {
        try {
            inventoryContainer.isDisplayed();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    //----User is locked out----
    @FindBy(how = How.XPATH,
            using = "//h3[@data-test='error']")
    private WebElement errorMessage;
    public void userIsLockedOut(String name,String pass) {
        username.sendKeys(name);
        password.sendKeys(pass);
        loginButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(errorMessage));
    }
    //----Username and password are required to log in.
    public void usernameIsRequired(String pass) {
        password.sendKeys(pass);
        clickLoginButton();
    }
    public void passwordIsRequired(String name) {
        username.sendKeys(name);
        clickLoginButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(errorMessage));
    }
    //User click log out button and is logged out.
}
