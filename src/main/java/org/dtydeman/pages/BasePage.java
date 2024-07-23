package org.dtydeman.pages;

import org.dtydeman.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private static final By practiceMenuButton = By.xpath("//*[@id=\"menu-item-20\"]/a");
    private static final By contactMenuButton = By.xpath("//*[@id=\"menu-item-18\"]/a");

    public void clickPracticeButton() {
        getElement(practiceMenuButton).click();
    }
    public void clickContactButton() {
        getElement(contactMenuButton).click();
    }

    /**
     * Method to get the current WebDriver instance
     * @return WebDriver
     */
    WebDriver getDriverInstance() {
        return DriverFactory.getInstance().getDriver();
    }

    /**
     * Method to get a WebElement based on the provided selector. Using an explicitWait, this will ensure the WebDriver
     * will wait up to 10 seconds for the element with the corresponding locator to be visible
     *
     * @param locator Locator of the desired WebElement
     * @return WebElement
     */
    WebElement getElement(By locator) {
        return new WebDriverWait(getDriverInstance(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
