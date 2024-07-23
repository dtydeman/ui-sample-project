package org.dtydeman.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestLoginPage extends BasePage {

    private final static By pageHeader = By.xpath("//*[@id=\"login\"]/h2");
    private static final By usernameLabel = By.xpath("//*[@id=\"form\"]/div[1]/label");
    private static final By usernameField = By.id("username");
    private static final By passwordLabel = By.xpath("//*[@id=\"form\"]/div[2]/label");
    private static final By passwordField = By.id("password");
    private static final By submitButton = By.id("submit");
    private static final By error = By.id("error");

    public String getPageTitle() {
        return getDriverInstance().getTitle();
    }

    public WebElement getPageHeader() {
        return getElement(pageHeader);
    }

    public WebElement getUsernameLabel() {
        return getElement(usernameLabel);
    }

    public WebElement getUsernameField() {
        return getElement(usernameField);
    }

    public WebElement getPasswordLabel() {
        return getElement(passwordLabel);
    }

    public WebElement getPasswordField() {
        return getElement(passwordField);
    }

    public WebElement getError() {
        return getElement(error);
    }

    public boolean errorNotPresent() {
        return new WebDriverWait(getDriverInstance(), Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(error));
    }

    public WebElement getSubmitButton() {
        return getElement(submitButton);
    }

    public void enterUsername(String username) {
        getUsernameField().clear();
        getUsernameField().sendKeys(username);
    }

    public void enterPassword(String password) {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
    }

    public void clickSubmit() {
        getSubmitButton().click();
    }
}
