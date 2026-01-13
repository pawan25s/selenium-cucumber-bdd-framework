package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.ProductPage;
import utilities.ExtentManager;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class ProductStep {

    private static final Logger logger = LogManager.getLogger(ProductStep.class);

    ProductPage productPage;

    @Given("user is on product detail page")
    public void user_is_on_product_detail_page() {
        logger.info("Navigating to product detail page");
        productPage = new ProductPage(baseClass.getDriver());
        productPage.openProduct("Sauce Labs Backpack"); // Open a product to be on detail page
        logger.info("Navigated to product detail page");
    }

    @When("user sorts products by {string}")
    public void user_sorts_products_by(String sortOption) {
        logger.info("Sorting products by: {}", sortOption);
        productPage = new ProductPage(baseClass.getDriver());
        productPage.sortProductsBy(sortOption);
        utilities.TestContext.setTestData("sortOption", sortOption);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Sorted products by " + sortOption);
        }
    }

    @When("user opens product {string}")
    public void user_opens_product(String productName) {
        logger.info("Opening product: {}", productName);
        productPage = new ProductPage(baseClass.getDriver());
        productPage.openProduct(productName);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Opened product " + productName);
        }
    }

    @When("user clicks back to products")
    public void user_clicks_back_to_products() {
        logger.info("Clicking back to products");
        productPage.clickBackToProducts();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Clicked back to products");
        }
    }

    @Then("products page should be displayed")
    public void products_page_should_be_displayed() {
        logger.info("Verifying products page is displayed");
        productPage = new ProductPage(baseClass.getDriver());
        Assert.assertTrue(productPage.isProductsPageDisplayed(), "Products page should be displayed");
        logger.info("Products page displayed successfully");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Products page displayed");
        }
    }

    @Then("products page title should be {string}")
    public void products_page_title_should_be(String expectedTitle) {
        logger.info("Verifying products page title: {}", expectedTitle);
        productPage = new ProductPage(baseClass.getDriver());
        String actualTitle = productPage.getProductsPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Products page title should be " + expectedTitle);
        logger.info("Products page title verified: {}", actualTitle);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Products page title is " + actualTitle);
        }
    }

    @Then("product list should contain at least one product")
    public void product_list_should_contain_at_least_one_product() {
        logger.info("Verifying product list is not empty");
        productPage = new ProductPage(baseClass.getDriver());
        Assert.assertTrue(productPage.hasAtLeastOneProduct(), "Product list should contain at least one product");
        logger.info("Product list contains products");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Product list is not empty");
        }
    }

    @Then("each product should display name, price and image")
    public void each_product_should_display_name_price_and_image() {
        logger.info("Verifying each product has name, price and image");
        productPage = new ProductPage(baseClass.getDriver());
        Assert.assertTrue(productPage.eachProductHasNamePriceImage(), "Each product should display name, price and image");
        logger.info("All products have required elements");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "All products display name, price and image");
        }
    }

    @Then("product prices should be displayed in valid currency format")
    public void product_prices_should_be_displayed_in_valid_currency_format() {
        logger.info("Verifying product prices are in valid format");
        productPage = new ProductPage(baseClass.getDriver());
        Assert.assertTrue(productPage.arePricesInValidFormat(), "Product prices should be in valid currency format");
        logger.info("Product prices are in valid format");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Product prices are in valid format");
        }
    }

    @Then("products should be sorted accordingly")
    public void products_should_be_sorted_accordingly() {
        String sortOption = (String) utilities.TestContext.getTestData("sortOption");
        logger.info("Verifying products are sorted by: {}", sortOption);
        Assert.assertTrue(productPage.areProductsSortedBy(sortOption), "Products should be sorted by " + sortOption);
        logger.info("Products are sorted by {}", sortOption);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Products are sorted by " + sortOption);
        }
    }

    @Then("product detail page should be displayed")
    public void product_detail_page_should_be_displayed() {
        logger.info("Verifying product detail page is displayed");
        productPage = new ProductPage(baseClass.getDriver());
        Assert.assertTrue(productPage.isProductDetailPageDisplayed(), "Product detail page should be displayed");
        logger.info("Product detail page displayed");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Product detail page displayed");
        }
    }
}