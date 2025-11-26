package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.HomePage;
import PageObject.LoginPage;
import org.testng.Assert;

public class LoginStep {

    private static final Logger logger = LogManager.getLogger(LoginStep.class);

    LoginPage login;
    HomePage homePage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        logger.info("Navigating to login page: {}", baseClass.getProperty("url"));
        baseClass.getDriver().get(baseClass.getProperty("url"));
        login = new LoginPage(baseClass.getDriver());
        homePage = new HomePage(baseClass.getDriver());
        logger.info("Login page loaded successfully");
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        logger.info("Entering username: {}", username);
        login.setUsername(username);
        logger.info("Username entered successfully");
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        logger.info("Entering password");
        login.enterPassword(password);
        logger.info("Password entered successfully");
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        logger.info("Clicking login button");
        login.clickLogin();
        logger.info("Login button clicked");
    }

      @Then("user should navigate to {string}")
    public void user_should_navigate_to(String expectedResult) {

        switch (expectedResult) {

            case "home page":
                logger.info("Verifying navigation to home page");
                Assert.assertTrue(homePage.isProductsDisplayed());
                logger.info("Successfully navigated to home page - products displayed");
                break;

            case "error message":
                logger.info("Verifying error message for invalid credentials");
                String errorMsg = login.getErrorMessage();
                logger.info("Error message: {}", errorMsg);
                Assert.assertTrue(errorMsg.contains("Epic sadface"));
                logger.info("Error message validation passed");
                break;

            case "username required":
                logger.info("Verifying username required error");
                String usernameError = login.getErrorMessage();
                logger.info("Error message: {}", usernameError);
                Assert.assertTrue(usernameError.contains("Username is required"));
                logger.info("Username required validation passed");
                break;

            case "password required":
                logger.info("Verifying password required error");
                String passwordError = login.getErrorMessage();
                logger.info("Error message: {}", passwordError);
                Assert.assertTrue(passwordError.contains("Password is required"));
                logger.info("Password required validation passed");
                break;

            default:
                Assert.fail("Unknown expected result: " + expectedResult);
        }

    }
    
}

