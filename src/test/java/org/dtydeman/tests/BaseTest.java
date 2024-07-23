package org.dtydeman.tests;

import org.dtydeman.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    static final Properties properties = new Properties();
    WebDriver driver;

    /**
     * Before all tests run, pull the properties from the test.properties file
     * @throws IOException If the test.properties file cannot be found
     */
    @BeforeAll
    static void getTestProperties() throws IOException {
        try (InputStream input = TestLoginPageTests.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new IOException("Unable to find test.properties file");
            }
            properties.load(input);
        }
    }

    /**
     * Before each test, instantiate a new WebDriver instance of the defined browser type
     */
    @BeforeEach
    void startWebDriver() {
        DriverFactory.getInstance().setWebDriver(properties.getProperty("browser"));
        driver = DriverFactory.getInstance().getDriver();
    }

    /**
     * After each test, close down the WebDriver instance
     */
    @AfterEach
    void stopWebDriver() {
        DriverFactory.getInstance().closeDriver();
    }

}
