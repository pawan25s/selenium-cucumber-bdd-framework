package StepDefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.LoginPage;
import org.testng.Assert;

public class LoginStep {

    private static final Logger logger = LogManager.getLogger(LoginStep.class);

    LoginPage login;


    @Given("user is on login page")
    public void user_is_on_login_page() {
        logger.info("Navigating to login page: {}", baseClass.getProperty("url"));
        baseClass.getDriver().get(baseClass.getProperty("url"));
        login = new LoginPage(baseClass.getDriver());
        logger.info("Login page loaded successfully");
    }

    @When("user enters username")
    public void user_enters_username() {
        String username = baseClass.getProperty("username");
        logger.info("Entering username: {}", username);
        login.setUsername(username);
        logger.info("Username entered successfully");
    }

    @When("user enters password")
    public void user_enters_password() {
        logger.info("Entering password");
        login.enterPassword(baseClass.getProperty("password"));
        logger.info("Password entered successfully");
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        logger.info("Clicking login button");
        login.clickLogin();
        logger.info("Login button clicked");
    }

    @Then("user should navigate to home page")
    public void user_should_navigate_to_home_page() {
        String url = baseClass.getDriver().getCurrentUrl();
        logger.info("Current URL after login: {}", url);
        Assert.assertTrue(url.contains("inventory"));
        logger.info("Login successful - navigated to home page");
    }
}

