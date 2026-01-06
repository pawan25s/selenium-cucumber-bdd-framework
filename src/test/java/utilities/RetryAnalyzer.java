package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Maximum number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.warn("Retrying test {} for the {} time. Failure reason: {}",
                        result.getName(), retryCount, result.getThrowable().getMessage());
            return true; // Retry the test
        }
        logger.error("Test {} failed after {} retries", result.getName(), maxRetryCount);
        return false; // Do not retry further
    }
}