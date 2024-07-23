package org.dtydeman.tests;

import org.dtydeman.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestE2ETests extends BaseTest {

    private final BasePage basePage = new BasePage();
    private final TestPracticePage practicePage = new TestPracticePage();
    private final TestLoginPage loginPage = new TestLoginPage();
    private final TestLoggedInSuccessfullyPage loggedInPage = new TestLoggedInSuccessfullyPage();
    private final TestContactPage contactPage = new TestContactPage();

    @BeforeEach
    void setURL() {
        driver.get(properties.getProperty("baseURL"));
    }

    @Test
    void verifyFullFlowTest() {
        basePage.clickPracticeButton();
        practicePage.clickTestLoginPage();
        loginPage.enterUsername(properties.getProperty("validUsername"));
        loginPage.enterPassword(properties.getProperty("validPassword"));
        loginPage.clickSubmit();

        assertEquals(properties.getProperty("successPageTitle"), loggedInPage.getPageTitle());
        assertEquals(properties.getProperty("successHeader"),loggedInPage.getPageHeader().getText());
        assertEquals(properties.getProperty("successMessage"),loggedInPage.getContent().getText());

        loggedInPage.clickContactButton();
        contactPage.clickSubmit();

        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getForenameFieldError().getText());
        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getSurnameFieldError().getText());
        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getEmailFieldError().getText());
        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getCommentMessageFieldError().getText());
    }
}
