package org.dtydeman.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final DriverFactory instance = new DriverFactory();
    private ChromeDriverService chromeDriverService;

    protected DriverFactory() {
    }

    /**
     * Method to get the current instance of the DriverFactory
     * @return Instance of this class
     */
    public static DriverFactory getInstance() {
        return instance;
    }


    /**
     * Get the current instance's WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Method to close the instance's WebDriver
     */
    public void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
        //Needed top ensure release of resources by ChromeDriver
        if (chromeDriverService != null && chromeDriverService.isRunning()) {
            chromeDriverService.stop();
        }
    }

    /**
     * Method to identify the desired browser type and instantiate a new WebDriver instance based on this
     *
     * @param browser The desired browser type
     */
    public void setWebDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome" -> driver = createChromeDriver(false);
            case "headless chrome" -> driver = createChromeDriver(true);
            case "firefox" -> driver = createFirefoxDriver(false);
            case "headless firefox" -> driver = createFirefoxDriver(true);
            case "edge" -> driver = new EdgeDriver();
            default ->
                    throw new IllegalArgumentException("Cannot create new driver for %s web browser.".formatted(browser));
        }
        driverThreadLocal.set(driver);
    }

    /**
     * Method to create a new instance of a ChromeDriver. If specified, this will provide the option to set the browser
     * in headless mode (no visible UI). It also starts a ChromeDriverService to encapsulate the resources in use, as
     * ChromeDriver is known to leave proceses running after a WebDriver instance has been stopped.
     *
     * @param headless Whether to run the WebDriver in headless mode or not
     * @return A ChromeDriver instance
     */
    private ChromeDriver createChromeDriver(boolean headless) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments("--headless");
        }
        try {
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to start ChromeDriverService", e);
        }
        return new ChromeDriver(chromeDriverService, chromeOptions);
    }

    /**
     * Method to create a new instance of a FirefoxDriver. If specified, this will provide the option to set the browser
     * in headless mode (no visible UI)
     *
     * @param headless Whether to run the WebDriver in headless mode or not
     * @return A FirefoxDriver instance
     */
    private FirefoxDriver createFirefoxDriver(boolean headless) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless) {
            firefoxOptions.addArguments("--headless");
        }
        return new FirefoxDriver(firefoxOptions);
    }
}
