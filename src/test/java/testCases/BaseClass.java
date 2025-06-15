/*

package testCases;
import com.github.javafaker.App;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;  //Log4j

    @BeforeClass(groups = {"Sanity", "Regression","Master","Datadriven"})
    @Parameters({"browsers","os"})

    public void setup(String br, String OS) throws InterruptedException, IOException {
   //     System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");

        logger = LogManager.getLogger(this.getClass()); //Log4j2 for logging
        System.out.println("Logger class: " + logger.getClass().getName());

        logger.info("===== Starting test setup for browser: " + br + ", OS: " + OS + " =====");
        System.out.println("###########Running environment: #############" + ConfigReader.readEnv());


        if (ConfigReader.readEnv().equalsIgnoreCase("remote")) {

            String hubUrl = "http://localhost:4444/wd/hub";

            if (!waitForGrid(hubUrl)) {
                throw new RuntimeException("Selenium Grid is not ready after multiple attempts");
            }
            switch (br.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
            //        chromeOptions.setPlatformName("Linux"); // optional, or remove
                    chromeOptions.addArguments("--headless=new");

                    driver = new RemoteWebDriver(new URL(hubUrl), chromeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
               //     firefoxOptions.setPlatformName("Linux"); // optional
                    firefoxOptions.addArguments("--headless");
                    driver = new RemoteWebDriver(new URL(hubUrl), firefoxOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                //    edgeOptions.setPlatformName("Linux"); // optional
                    edgeOptions.addArguments("--headless=new");
                    driver = new RemoteWebDriver(new URL(hubUrl), edgeOptions);
                    break;
                default:
                    System.out.println("No known browser");
                    return;
            }
        }

        if(ConfigReader.readEnv().equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.info("**********No Browser Present************");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
       // driver.get("https://demo.opencart.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    //    driver.get("https://tutorialsninja.com/demo/");
        driver.get(ConfigReader.readURL());
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    @AfterClass(groups={"Sanity","Regression","Master","Datadriven"})
    public void tearDown(){
        logger.info("===== Test teardown complete. Closing browser. =====");
        driver.close();

    }

    public static String captureScreen(String tname) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+ "_"+ timestamp;
        File targetFile = new File(targetFilePath);
        FileUtils.copyFile(sourceFile, targetFile);
        return targetFilePath;
    }

    private boolean waitForGrid(String hubUrl) {
        String statusUrl = hubUrl.replace("/wd/hub", "/status");
        int retries = 0;
        while (retries < 20) {
            try {
                URL url = new URL(statusUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);

                if (connection.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Look for "ready": true
                    if (response.toString().contains("\"ready\":true")) {
                        logger.info("Selenium Grid is ready.");
                        return true;
                    }
                    System.out.println("**********Grid status response: " + response);

                }
            } catch (IOException e) {
                logger.warn("Waiting for Selenium Grid to be ready...");
            }

            retries++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
        }
        return false;
    }



}
*/

package testCases;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseClass {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    public static final Logger logger = LogManager.getLogger(BaseClass.class);
    private static final Map<String, Boolean> gridReadyMap = new ConcurrentHashMap<>();

    protected WebDriver getDriver() {
        return driverThread.get();
    }

    @Parameters({"browsers"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        String env = System.getProperty("env", "remote").toLowerCase();
        String gridUrl = "http://localhost:4444/wd/hub";
        logger.info("ENV = " + env);
        logger.info("Starting browser setup for: " + browser);

        try {
            if (env.equals("remote")) {
                waitForGridAndSlot(gridUrl, browser.toLowerCase());
                switch (browser.toLowerCase()) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driverThread.set(new RemoteWebDriver(new URL(gridUrl), chromeOptions));
                        break;
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driverThread.set(new RemoteWebDriver(new URL(gridUrl), firefoxOptions));
                        break;
                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        driverThread.set(new RemoteWebDriver(new URL(gridUrl), edgeOptions));
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            } else {
                throw new RuntimeException("Local mode not supported in this version.");
            }
        } catch (Exception e) {
            logger.error("Driver setup failed: " + e.getMessage(), e);
            throw new RuntimeException("Failed to start WebDriver", e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            logger.info("Quitting browser...");
            driver.quit();
            driverThread.remove();
        }
    }

    public static String captureScreen(String tname) throws IOException {
        WebDriver driver = driverThread.get();
        if (driver == null) return null;
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timestamp + ".png";
        FileUtils.copyFile(srcFile, new File(path));
        return path;
    }

    private void waitForGridAndSlot(String hubUrl, String browserName) {
        if (gridReadyMap.getOrDefault(browserName, false)) return;

        synchronized (gridReadyMap) {
            if (!gridReadyMap.getOrDefault(browserName, false)) {
                boolean ready = waitUntilSlotIsAvailable(hubUrl, browserName);
                if (!ready) {
                    throw new RuntimeException("Selenium Grid is not ready or has no available slots for: " + browserName);
                }
                logger.info("✅ Grid is ready and slot available for: " + browserName);
                gridReadyMap.put(browserName, true);
            }
        }
    }

    private boolean waitUntilSlotIsAvailable(String hubUrl, String browserName) {
        String statusUrl = hubUrl.replace("/wd/hub", "/status");
        int retries = 0;

        while (retries < 30) { // ~60s
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(statusUrl).openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                if (conn.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();

                    JSONObject json = new JSONObject(response.toString());
                    JSONObject value = json.getJSONObject("value");
                    if (value.getBoolean("ready")) {
                        logger.info("Grid returned ready = true. Assuming slots are warming up...");
                        return true;
                    }
                }
            } catch (Exception e) {
                logger.warn("Waiting for Selenium Grid and available slots (attempt {}): {}", retries + 1, e.getMessage());
            }

            retries++;
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        }
        return false;
    }
}
