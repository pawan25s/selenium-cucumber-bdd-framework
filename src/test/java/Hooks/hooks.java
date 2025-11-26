package Hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import BaseClass.baseClass;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class hooks extends baseClass {

    private static final Logger logger = LogManager.getLogger(hooks.class);

    @Before
    public void setup()
    {
        logger.info("Setting up test environment - starting browser");
        baseClass.startBrowser();
        logger.info("Test setup completed");

    }

     @After
     public void tearDown(){
         logger.info("Tearing down test environment - quitting browser");
         baseClass.quitBrowser();
         logger.info("Test teardown completed");
     }

}
