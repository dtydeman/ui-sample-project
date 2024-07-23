package org.dtydeman.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestPracticePage extends BasePage {

    private final static By pageHeader = By.xpath("//*[@id=\"loop-container\"]/div/article/div[1]/h1");
    private static final By testLoginPageLink = By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div[1]/div[1]/p/a");
    private static final By testExceptionsPageLink = By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div[2]/div[1]/p/a");

    public String getPageTitle() {
        return getDriverInstance().getTitle();
    }

    public WebElement getPageHeader() {
        return getElement(pageHeader);
    }

    public WebElement getTestLoginPageLink() {
        return getElement(testLoginPageLink);
    }

    public WebElement getTestExceptionsPageLink() {
        return getElement(testExceptionsPageLink);
    }

    public void clickTestLoginPage() {
        getTestLoginPageLink().click();
    }
    public void clickTestExceptionsPage() {
        getTestExceptionsPageLink().click();
    }
}
