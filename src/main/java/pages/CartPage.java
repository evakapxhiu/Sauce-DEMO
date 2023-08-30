package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
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

    //----Check if we can click on the shopping cart icon and if we can access "your cart" page.----
    public void clickShoppingCartLink() {
        shoppingCartLink.click();
    }
    public boolean displayYourCartPage() {
        return  cartContainer.isDisplayed();
    }
    //-----***********************************************************************-----
    //----I tried something,but it's not correct. I will delete it later or do some refactor.----
    public record CartItem(String title,String desc,Double price){
    }
    public List<CartItem> getCartItems2() {
        List<CartItem> cartItems2=new ArrayList<>();
        String name = getWebTitleProducts().get(2);
        String desc = getWebDescriptionProducts().get(2);
        Double price = getWebPriceProducts().get(2);
        cartItems2.add(new CartItem(name,desc,price));
        return cartItems2;
    }
    public List<CartItem> getCartItems3() {
        List<CartItem> cartItems3=new ArrayList<>();
        String name = getWebTitleProducts().get(3);
        String desc = getWebDescriptionProducts().get(3);
        Double price = getWebPriceProducts().get(3);
        cartItems3.add(new CartItem(name,desc,price));
        return cartItems3;
    }
    public List<CartItem> getCartItems4() {
        List<CartItem> cartItems4=new ArrayList<>();
        String name = getWebTitleProducts().get(4);
        String desc = getWebDescriptionProducts().get(4);
        Double price = getWebPriceProducts().get(4);
        cartItems4.add(new CartItem(name,desc,price));
        return cartItems4;
    }

    public List<String> getWebTitleProducts() {
        List<WebElement> webTitleProductElements = webDriver.findElements(By.className("inventory_item_name"));
        return webTitleProductElements
                .stream()
                .map(el -> el.getText().trim())
                .toList();
    }
    public List<String> getWebDescriptionProducts () {
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
}
