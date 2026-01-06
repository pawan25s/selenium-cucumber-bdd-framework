package BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;

public class baseClass {

    private static final Logger logger = LogManager.getLogger(baseClass.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static Properties prop;

    // -------------------- LOAD CONFIG --------------------
    public static void loadConfig() {
        logger.info("Loading configuration properties from Configuration/config.properties");
        prop = new Properties();
        try (FileInputStream ip = new FileInputStream("Configuration/config.properties")) {
            prop.load(ip);
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Unable to load config.properties file!", e);
            throw new RuntimeException("Unable to load config.properties file!", e);
        }
    }
    public static String getProperty(String key) { //getter for config value as prop is private
    return prop.getProperty(key);
}

    // -------------------- START BROWSER --------------------
    public static void startBrowser() {
        loadConfig();
        String browser = prop.getProperty("browser", "chrome").trim().toLowerCase();
        String headless = prop.getProperty("headless", "true");
        logger.info("Starting browser: {}, Headless: {}", browser, headless);

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless.equalsIgnoreCase("true")) {
                    firefoxOptions.addArguments("--headless");
                }
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver.set(new EdgeDriver(edgeOptions));
                break;

            default:
                logger.error("Browser not supported: {}", browser);
                throw new RuntimeException("❌ Browser not supported: " + browser);
        }

        // Window + Timeouts
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait", "10"))));
        logger.info("Browser {} started successfully with implicit wait: {} seconds", browser, prop.getProperty("implicitWait", "10"));
    }

    // -------------------- GET DRIVER --------------------
    public static WebDriver getDriver() {
        return driver.get();
    }

    // -------------------- CAPTURE SCREENSHOT --------------------
    public static String captureScreenshot(String testName) {
        if (getDriver() == null) {
            logger.warn("Driver is null, cannot capture screenshot");
            return null;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = "target/screenshots/" + testName + "_" + timestamp + ".png";
            File destination = new File(fileName);
            destination.getParentFile().mkdirs(); // Create screenshots directory if it doesn't exist
            FileUtils.copyFile(source, destination);
            logger.info("Screenshot captured: {}", fileName);
            return fileName; // Return the relative path for ExtentReports
        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }

    // -------------------- QUIT BROWSER --------------------
    public static void quitBrowser() {
        logger.info("Quitting browser and cleaning up driver");
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            logger.info("Browser quit successfully");
        } else {
            logger.warn("Driver is null, nothing to quit");
        }
    }

    
}
