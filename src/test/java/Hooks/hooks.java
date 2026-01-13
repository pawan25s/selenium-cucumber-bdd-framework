package Hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import utilities.ExtentManager;
import utilities.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.Status;

public class hooks extends baseClass {

    private static final Logger logger = LogManager.getLogger(hooks.class);

    @Before
    public void setup(Scenario scenario)
    {
        logger.info("Setting up test environment - starting browser");
        baseClass.startBrowser();
        logger.info("Test setup completed");

        // Create ExtentTest for the scenario
        ExtentManager.createTest(scenario.getName(), scenario.getName());

        // Clear any previous test data
        TestContext.clear();
    }

     @After
      public void tearDown(Scenario scenario){
          logger.info("Tearing down test environment");

          // Capture screenshot if scenario failed
          if (scenario.isFailed()) {
              logger.error("Scenario failed: {}", scenario.getName());
              String testName = scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_");
              String screenshotPath = baseClass.captureScreenshot(testName);

              // Log failure to ExtentReports
              if (ExtentManager.getTest() != null) {
                  ExtentManager.getTest().log(Status.FAIL, "Scenario failed: " + scenario.getName());
                  ExtentManager.getTest().fail("Test Failed");
                  try {
                      ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                  } catch (Exception e) {
                      logger.error("Failed to attach screenshot to ExtentReports", e);
                  }
              }
          } else {
              if (ExtentManager.getTest() != null) {
                  ExtentManager.getTest().log(Status.PASS, "Scenario passed: " + scenario.getName());
                  ExtentManager.getTest().pass("Test Passed");
              }
          }

          baseClass.quitBrowser();
          logger.info("Test teardown completed");
      }

}
