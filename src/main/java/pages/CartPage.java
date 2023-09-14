package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(how = How.CLASS_NAME,
            using = "shopping_cart_link")
    private WebElement shoppingCartLink;
    @FindBy(how = How.ID,
            using = "cart_contents_container")
    private WebElement cartContainer;
    @FindBy(how = How.ID,
            using = "checkout")
    private WebElement checkOutBtn;
    @FindBy(how = How.ID,
            using = "first-name")
    private WebElement firstName;
    @FindBy(how = How.ID,
            using = "last-name")
    private WebElement lastName;
    @FindBy(how = How.ID,
            using = "postal-code")
    private WebElement zipCode;
    @FindBy(how = How.ID,
            using = "continue")
    private WebElement continueBtn;
    @FindBy(how = How.ID,
            using = "finish")
    private WebElement finishBtn;
    @FindBy(how = How.CLASS_NAME,
    using = "summary_subtotal_label")
    private WebElement itemTotal;
    @FindBy(how = How.CLASS_NAME,
    using = "summary_tax_label")
    private WebElement tax;
    @FindBy(how = How.CLASS_NAME,
    using = "summary_total_label")
    private WebElement totalPrice;

    //----Check if we can click on the shopping cart icon and if we can access "your cart" page.----
    public void clickShoppingCartLink() {
        shoppingCartLink.click();
    }
    public boolean displayYourCartPage() {
        return cartContainer.isDisplayed();
    }
    //-----***********************************************************************-----
    //----I tried something,but it's not correct. I will delete it later or do some refactor.----
    public record CartItem(String title, String desc, Double price) {
    }
    public List<String> getWebTitleProducts() {
        List<WebElement> webTitleProductElements = webDriver.findElements(By.className("inventory_item_name"));
        return webTitleProductElements
                .stream()
                .map(el -> el.getText().trim())
                .toList();
    }
    public List<String> getWebDescriptionProducts() {
        List<WebElement> webDescriptionProductElements = webDriver.findElements(By.className("inventory_item_desc"));
        return webDescriptionProductElements
                .stream()
                .map(el -> el.getText().trim())
                .toList();
    }
    public List<Double> getWebPriceProducts() {
        List<WebElement> webPriceProductElements = webDriver.findElements(By.className("inventory_item_price"));
        return webPriceProductElements
                .stream()
                .map(WebElement::getText)
                .map(price -> Double.parseDouble(price.trim().replaceAll("[^0-9.]", "")))
                .toList();
    }
    public void checkOutYourInformation(String name, String lName, String zip) {
        clickShoppingCartLink();
        checkOutBtn.click();
        firstName.sendKeys(name);
        lastName.sendKeys(lName);
        zipCode.sendKeys(zip);
        continueBtnClick();
    }
    public void checkOutYourInformation() {
        clickShoppingCartLink();
        checkOutBtn.click();
        continueBtnClick();
    }
    public boolean overviewOfYourOrder() {
        WebElement overviewOfOrder = webDriver.findElement(By.className("summary_info"));
        return overviewOfOrder.isDisplayed();
    }
    public void clickFinishButton() {
        finishBtn.click();
    }
    public void continueBtnClick() {
        continueBtn.click();
    }

    public double getTheTotalPaymentOfTheOrder() {
        String priceElement = itemTotal.getText();
        String taxElement = tax.getText();
        double price = Double.parseDouble(priceElement.replaceAll("[^0-9.]", ""));
        double tax = Double.parseDouble(taxElement.replaceAll("[^0-9.]", ""));
        double totalPrice = price + tax;
        return totalPrice;
    }

}
