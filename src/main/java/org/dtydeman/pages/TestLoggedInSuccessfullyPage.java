package org.dtydeman.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestLoggedInSuccessfullyPage extends BasePage  {

    private final static By pageHeader = By.xpath("//*[@id=\"loop-container\"]/div/article/div[1]/h1");
    private static final By content = By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/p[1]/strong");
    private static final By logoutButton = By.xpath("//*[@id=\"loop-container\"]/div/article/div[2]/div/div/div/a");


    public String getPageTitle() {
        return getDriverInstance().getTitle();
    }

    public WebElement getPageHeader() {
        return getElement(pageHeader);
    }

    public WebElement getContent() {
        return getElement(content);
    }

    public WebElement getLogoutButton() {
        return getElement(logoutButton);
    }


    public void clickLogout() {
        getLogoutButton().click();
    }
}
