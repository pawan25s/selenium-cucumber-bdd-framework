package PageObject;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    private WebDriver driver;

    // Locators
    private By productsTitle = By.className("title");
    private By productList = By.className("inventory_item");
    private By productNameLocator = By.className("inventory_item_name");
    private By productPrice = By.className("inventory_item_price");
    private By productImage = By.xpath("//img[@class='inventory_item_img']");
    private By sortDropdown = By.className("product_sort_container");
    private By productDetailName = By.className("inventory_details_name");
    private By backToProductsButton = By.cssSelector("button[data-test='back-to-products']");
    private By addToCartButton = By.xpath("//button[text()='Add to cart']");
    private By cartBadge = By.className("shopping_cart_badge");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }

    public String getProductsPageTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public List<WebElement> getProductList() {
        return driver.findElements(productList);
    }

    public boolean hasAtLeastOneProduct() {
        return !getProductList().isEmpty();
    }

    public boolean eachProductHasNamePriceImage() {
        List<WebElement> products = getProductList();
        for (WebElement product : products) {
            WebElement name = product.findElement(productNameLocator);
            WebElement price = product.findElement(productPrice);
            List<WebElement> images = product.findElements(productImage);
            if (!name.isDisplayed() || !price.isDisplayed() || images.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean arePricesInValidFormat() {
        List<WebElement> products = getProductList();
        for (WebElement product : products) {
            String priceText = product.findElement(productPrice).getText();
            // Assuming format like $29.99
            if (!priceText.matches("\\$\\d+\\.\\d{2}")) {
                return false;
            }
        }
        return true;
    }

    public void sortProductsBy(String sortOption) {
        Select sortSelect = new Select(driver.findElement(sortDropdown));
        sortSelect.selectByVisibleText(sortOption);
    }

    public boolean areProductsSortedBy(String sortOption) {
        List<WebElement> products = getProductList();
        List<String> names = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        for (WebElement product : products) {
            names.add(product.findElement(productNameLocator).getText());
            String priceText = product.findElement(productPrice).getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        switch (sortOption) {
            case "Name (A to Z)":
                for (int i = 1; i < names.size(); i++) {
                    if (names.get(i-1).compareTo(names.get(i)) > 0) return false;
                }
                break;
            case "Name (Z to A)":
                for (int i = 1; i < names.size(); i++) {
                    if (names.get(i-1).compareTo(names.get(i)) < 0) return false;
                }
                break;
            case "Price (low to high)":
                for (int i = 1; i < prices.size(); i++) {
                    if (prices.get(i-1) > prices.get(i)) return false;
                }
                break;
            case "Price (high to low)":
                for (int i = 1; i < prices.size(); i++) {
                    if (prices.get(i-1) < prices.get(i)) return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public void openProduct(String productName) {
        List<WebElement> products = getProductList();
        for (WebElement product : products) {
            if (product.findElement(productNameLocator).getText().equals(productName)) {
                product.findElement(productNameLocator).click();
                break;
            }
        }
    }

    public boolean isProductDetailPageDisplayed() {
        return driver.findElement(productDetailName).isDisplayed();
    }

    public void clickBackToProducts() {
        driver.findElement(backToProductsButton).click();
    }

    public void addProductToCart(String productName) {
        List<WebElement> products = getProductList();
        for (WebElement product : products) {
            if (product.findElement(productNameLocator).getText().equals(productName)) {
                product.findElement(addToCartButton).click();
                break;
            }
        }
    }

    public String getCartBadgeCount() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0"; // No badge if empty
        }
    }

    public void logout() {
        driver.findElement(menuButton).click();
        driver.findElement(logoutLink).click();
    }
}
