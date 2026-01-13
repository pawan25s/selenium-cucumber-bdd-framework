package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.CartPage;
import PageObject.ProductPage;
import utilities.ExtentManager;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class CartStep {

    private static final Logger logger = LogManager.getLogger(CartStep.class);

    CartPage cartPage;
    ProductPage productPage;

    @Given("user has product in cart")
    public void user_has_product_in_cart() {
        logger.info("Adding product to cart for setup");
        productPage = new ProductPage(baseClass.getDriver());
        productPage.addProductToCart("Sauce Labs Backpack");
        logger.info("Product added to cart");
    }

    @When("user adds {string} to cart")
    public void user_adds_to_cart(String productName) {
        logger.info("Adding product to cart: {}", productName);
        productPage = new ProductPage(baseClass.getDriver());
        productPage.addProductToCart(productName);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Added " + productName + " to cart");
        }
    }

    @When("user removes product from cart")
    public void user_removes_product_from_cart() {
        logger.info("Removing product from cart");
        cartPage = new CartPage(baseClass.getDriver());
        cartPage.openCart();
        cartPage.removeProduct();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Removed product from cart");
        }
    }

    @When("user opens cart")
    public void user_opens_cart() {
        logger.info("Opening cart");
        cartPage = new CartPage(baseClass.getDriver());
        cartPage.openCart();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Opened cart");
        }
    }

    @Then("cart badge count should be {string}")
    public void cart_badge_count_should_be(String expectedCount) {
        logger.info("Verifying cart badge count: {}", expectedCount);
        productPage = new ProductPage(baseClass.getDriver());
        String actualCount = productPage.getCartBadgeCount();
        Assert.assertEquals(actualCount, expectedCount, "Cart badge count should be " + expectedCount);
        logger.info("Cart badge count verified: {}", actualCount);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Cart badge count is " + actualCount);
        }
    }

    @Then("cart should be empty")
    public void cart_should_be_empty() {
        logger.info("Verifying cart is empty");
        cartPage = new CartPage(baseClass.getDriver());
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
        logger.info("Cart is empty");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Cart is empty");
        }
    }

    @Then("cart page should be displayed")
    public void cart_page_should_be_displayed() {
        logger.info("Verifying cart page is displayed");
        cartPage = new CartPage(baseClass.getDriver());
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        logger.info("Cart page displayed");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Cart page displayed");
        }
    }
}