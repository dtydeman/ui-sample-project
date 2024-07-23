package org.dtydeman.tests;

import org.dtydeman.pages.TestLoggedInSuccessfullyPage;
import org.dtydeman.pages.TestLoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoggedInSuccessfullyPageTests extends BaseTest {

    private final TestLoginPage loginPage = new TestLoginPage();
    private final TestLoggedInSuccessfullyPage loggedInPage = new TestLoggedInSuccessfullyPage();

    @BeforeEach
    void setURL() {
        driver.get(properties.getProperty("baseURL")+"practice-test-login/");
    }

    @Test
    void verifyAccessToSuccessfulLoginPage() {
        loginPage.enterUsername(properties.getProperty("validUsername"));
        loginPage.enterPassword(properties.getProperty("validPassword"));
        loginPage.clickSubmit();

        assertEquals(properties.getProperty("successPageTitle"), loggedInPage.getPageTitle());
        assertTrue(loggedInPage.getPageHeader().isDisplayed());
        assertEquals(properties.getProperty("successHeader"), loggedInPage.getPageHeader().getText());
        assertTrue(loggedInPage.getContent().isDisplayed());
        assertEquals(properties.getProperty("successMessage"), loggedInPage.getContent().getText());
        assertTrue(loggedInPage.getLogoutButton().isDisplayed());
    }


}
