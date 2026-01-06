package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.en.*;
import PageObject.HomePage;
import PageObject.LoginPage;
import utilities.ExtentManager;
import utilities.TestContext;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class LoginStep {

    private static final Logger logger = LogManager.getLogger(LoginStep.class);

    LoginPage login;
    HomePage homePage;

    @Given("user is on the SauceDemo login page")
    public void user_is_on_the_sauce_demo_login_page() {
        logger.info("Navigating to login page: {}", baseClass.getProperty("url"));
        baseClass.getDriver().get(baseClass.getProperty("url"));
        login = new LoginPage(baseClass.getDriver());
        homePage = new HomePage(baseClass.getDriver());
        logger.info("Login page loaded successfully");
        // ExtentTest will be created in password step
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        TestContext.setTestData("username", username);
        logger.info("Entering username: {}", username);
        login.setUsername(username);
        logger.info("Username entered successfully");
        // ExtentTest will be created in password step
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        TestContext.setTestData("password", password);
        logger.info("Entering password: {}", password);
        login.enterPassword(password);
        logger.info("Password entered successfully");

        // Create ExtentTest now that we have both username and password
        String testName = TestContext.getDescriptiveTestName();
        ExtentManager.createTest(testName, testName);
        ExtentManager.getTest().info("Starting test: " + testName);
        ExtentManager.getTest().log(Status.INFO, "Entered password");
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        logger.info("Clicking login button");
        login.clickLogin();
        logger.info("Login button clicked");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Clicked login button");
        }
    }

      @Then("login result should be {string}")
    public void login_result_should_be(String expectedResult) {

        switch (expectedResult) {

            case "success":
                logger.info("Verifying login success");
                Assert.assertTrue(homePage.isProductsDisplayed());
                logger.info("Login successful - products displayed");
                break;

            case "invalid credentials":
                logger.info("Verifying error message for invalid credentials");
                String errorMsg = login.getErrorMessage();
                logger.info("Error message: {}", errorMsg);
                Assert.assertTrue(errorMsg.contains("Epic sadface"));
                logger.info("Invalid credentials validation passed");
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

