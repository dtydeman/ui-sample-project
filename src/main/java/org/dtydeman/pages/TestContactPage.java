package org.dtydeman.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestContactPage extends BasePage {

    private final static By pageHeader = By.xpath("//*[@id=\"loop-container\"]/div/article/div[1]/h1");
    private static final By nameLabel = By.xpath("//*[@id=\"wpforms-161-field_0-container\"]/label");
    private static final By forenameField = By.id("wpforms-161-field_0");
    private static final By forenameFieldError = By.id("wpforms-161-field_0-error");
    private static final By surnameField = By.id("wpforms-161-field_0-last");
    private static final By surnameFieldError = By.id("wpforms-161-field_0-last-error");
    private static final By emailLabel = By.xpath("//*[@id=\"wpforms-161-field_1-container\"]/label");
    private static final By emailField = By.id("wpforms-161-field_1");
    private static final By emailFieldError = By.id("wpforms-161-field_1-error");
    private static final By commentMessageLabel = By.xpath("//*[@id=\"wpforms-161-field_2-container\"]/label");
    private static final By commentMessageField = By.id("wpforms-161-field_2");
    private static final By commentMessageFieldError = By.id("wpforms-161-field_2-error");
    private static final By submitButton = By.id("wpforms-submit-161");

    public String getPageTitle() {
        return getDriverInstance().getTitle();
    }

    public WebElement getPageHeader() {
        return getElement(pageHeader);
    }

    public WebElement getNameLabel() {
        return getElement(nameLabel);
    }

    public WebElement getForenameField() {
        return getElement(forenameField);
    }
    public WebElement getForenameFieldError() {
        return getElement(forenameFieldError);
    }
    public WebElement getSurnameField() {
        return getElement(surnameField);
    }
    public WebElement getSurnameFieldError() {
        return getElement(surnameFieldError);
    }

    public WebElement getEmailLabel() {
        return getElement(emailLabel);
    }

    public WebElement getEmailField() {
        return getElement(emailField);
    }
    public WebElement getEmailFieldError() {
        return getElement(emailFieldError);
    }
    public WebElement getCommentMessageLabel() {
        return getElement(commentMessageLabel);
    }
    public WebElement getCommentMessageField() {
        return getElement(commentMessageField);
    }
    public WebElement getCommentMessageFieldError() {
        return getElement(commentMessageFieldError);
    }

    public WebElement getSubmitButton() {
        return getElement(submitButton);
    }

    public boolean errorNotPresent(String fieldName) {
        By selector = switch (fieldName.toLowerCase()) {
            case "forename" -> forenameFieldError;
            case "surname" -> surnameFieldError;
            case "email" -> emailFieldError;
            case "commentmessage", "comment", "message" -> commentMessageFieldError;
            default -> throw new IllegalArgumentException("Invalid field name, please check the input used.");
        };
        return new WebDriverWait(getDriverInstance(), Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

    public void enterForename(String forename) {
        getForenameField().clear();
        getForenameField().sendKeys(forename);
    }

    public void enterSurname(String surname) {
        getSurnameField().clear();
        getSurnameField().sendKeys(surname);
    }

    public void enterEmail(String email) {
        getEmailField().clear();
        getEmailField().sendKeys(email);
    }

    public void enterCommentMessage(String commentMessage) {
        getCommentMessageField().clear();
        getCommentMessageField().sendKeys(commentMessage);
    }

    public void clickSubmit() {
        getSubmitButton().click();
    }
}
