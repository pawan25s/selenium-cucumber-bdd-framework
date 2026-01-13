package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.ProductPage;
import PageObject.LoginPage;
import utilities.ExtentManager;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class LogoutStep {

    private static final Logger logger = LogManager.getLogger(LogoutStep.class);

    ProductPage productPage;
    LoginPage loginPage;

    @When("user logs out")
    public void user_logs_out() {
        logger.info("Logging out user");
        productPage = new ProductPage(baseClass.getDriver());
        productPage.logout();
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "User logged out");
        }
    }

    @Then("user should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        logger.info("Verifying user is redirected to login page");
        loginPage = new LoginPage(baseClass.getDriver());
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User should be redirected to login page");
        logger.info("User redirected to login page");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "User redirected to login page");
        }
    }
}