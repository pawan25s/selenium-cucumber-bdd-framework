package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    // Locators
    private By cartIcon = By.className("shopping_cart_link");
    private By cartTitle = By.className("title");
    private By cartItems = By.className("cart_item");
    private By removeButton = By.cssSelector("button[data-test*='remove']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public boolean isCartPageDisplayed() {
        return driver.findElement(cartTitle).isDisplayed() && driver.findElement(cartTitle).getText().equals("Your Cart");
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeProduct() {
        driver.findElement(removeButton).click();
    }

    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }
}
