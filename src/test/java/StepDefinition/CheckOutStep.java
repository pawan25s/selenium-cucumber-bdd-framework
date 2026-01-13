package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.CartPage;
import PageObject.CheckOutPage;
import PageObject.ProductPage;
import utilities.ExtentManager;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class CheckOutStep {

    private static final Logger logger = LogManager.getLogger(CheckOutStep.class);

    CartPage cartPage;
    CheckOutPage checkOutPage;
    ProductPage productPage;

    @Given("user has products in cart")
    public void user_has_products_in_cart() {
        logger.info("Adding product to cart for checkout setup");
        productPage = new ProductPage(baseClass.getDriver());
        productPage.addProductToCart("Sauce Labs Backpack");
        logger.info("Product added to cart");
    }

    @When("user navigates to cart")
    public void user_navigates_to_cart() {
        logger.info("Navigating to cart");
        cartPage = new CartPage(baseClass.getDriver());
        cartPage.openCart();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Navigated to cart");
        }
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        logger.info("Proceeding to checkout");
        checkOutPage = new CheckOutPage(baseClass.getDriver());
        checkOutPage.proceedToCheckout();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Proceeded to checkout");
        }
    }

    @When("user enters checkout details:")
    public void user_enters_checkout_details(io.cucumber.datatable.DataTable dataTable) {
        logger.info("Entering checkout details");
        checkOutPage = new CheckOutPage(baseClass.getDriver());
        java.util.Map<String, String> details = new java.util.HashMap<>();
        for (java.util.List<String> row : dataTable.asLists()) {
            if (row.size() == 2) {
                details.put(row.get(0), row.get(1));
            }
        }
        String firstName = details.get("firstName");
        String lastName = details.get("lastName");
        String zipCode = details.get("zipCode");
        checkOutPage.enterCheckoutDetails(firstName, lastName, zipCode);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Entered checkout details");
        }
    }

    @When("user completes checkout")
    public void user_completes_checkout() {
        logger.info("Completing checkout");
        checkOutPage = new CheckOutPage(baseClass.getDriver());
        checkOutPage.completeCheckout();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Completed checkout");
        }
    }

    @Then("order confirmation page should be displayed")
    public void order_confirmation_page_should_be_displayed() {
        logger.info("Verifying order confirmation page");
        checkOutPage = new CheckOutPage(baseClass.getDriver());
        Assert.assertTrue(checkOutPage.isOrderConfirmationDisplayed(), "Order confirmation page should be displayed");
        logger.info("Order confirmation page displayed");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Order confirmation page displayed");
        }
    }
}