package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
public class InventoryPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    public InventoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(how = How.XPATH,
            using = "//button[contains(@class,'btn_primary')]")
    private WebElement addToCartButton;
    @FindBy(how = How.CLASS_NAME,
    using = "btn_secondary")
    private WebElement removeFromCartButton;
    @FindBy(how = How.ID,
    using = "logout_sidebar_link")
    private WebElement logOutButton;

    //Add to cart button and Remove Button for all products.txt.
    public void clickAddToCartButtons(){
        List<WebElement> addToCartsButtons=webDriver.findElements(By.xpath( "//button[contains(@class,'btn_primary')]"));
        addToCartsButtons.forEach(e->e.click());
        System.out.println(addToCartsButtons.size());
    }
    public boolean isRemoveButtonVisible() {
        return  removeFromCartButton.isDisplayed();
    }
    public void clickARemoveButtons(){
        clickAddToCartButtons();
        List<WebElement> removeFromCartsButtons=webDriver.findElements(By.xpath( "//button[contains(@class,'btn_secondary')]"));
        removeFromCartsButtons.forEach(e->e.click());
    }
    public boolean isAddToCartButtonVisible() {
        return  addToCartButton.isDisplayed();
    }
    //Add to cart only some products.txt,and check if they are displayed on the cart page.
    public void addToCartThreeProducts(){
        List<WebElement> addToCartsButtons=webDriver.findElements(By.xpath( "//button[contains(@class,'btn_primary')]"));
        try {
            for (int i = 0; i < addToCartsButtons.size(); i++) {
                addToCartsButtons.get(1).click();
                System.out.println(i);
            }
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }
    }
    //LogOut
    public LoginPage clickLogOut() {
        logOutButton.click();
        return new LoginPage(webDriver);
    }
    //-----***********************************************************************-----
    //I tried something,but it's not correct. I will delete it later or do some refactor.
    public record Products(String title,String desc,Double price){
    }
    public List<Products> productsWeExpected(){
        List<Products> expctedProd=new ArrayList<>();
        try {
            List<String> lines= Files.readAllLines(Paths.get("src/test/products.txt"));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    Double price = Double.parseDouble(parts[2].trim());
                    expctedProd.add(new Products(name, description, price));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return expctedProd;
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
   public CartPage addToCartSomeProducts() {
       List<WebElement> addToCartsButtons=webDriver.findElements(By.xpath( "//button[contains(@class,'btn_primary')]"));
       try {
           for (int i = 0; i < productsWeExpected().size(); i++) {
               addToCartsButtons.get(2).click();
           }
       }catch (StaleElementReferenceException e){
           e.printStackTrace();
       }
       return new CartPage(webDriver);
   }
   public List<WebElement> cartItems2() {
       List<WebElement> cartItems2=new ArrayList<>();
        cartItems2.add(addToCartButton);
       return cartItems2;
   }
}
