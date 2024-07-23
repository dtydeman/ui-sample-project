package org.dtydeman.tests;

import org.dtydeman.pages.TestLoginPage;
import org.dtydeman.pages.TestPracticePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPracticePageTests extends BaseTest {

    private final TestPracticePage practicePage = new TestPracticePage();
    private final TestLoginPage loginPage = new TestLoginPage();

    @BeforeEach
    void setURL() {
        driver.get(properties.getProperty("baseURL")+"practice/");
    }

    @Test
    void verifyAccessToPracticePage() {
        assertEquals(properties.getProperty("practicePageTitle"), practicePage.getPageTitle());
        assertEquals(properties.getProperty("practiceHeader"), practicePage.getPageHeader().getText());
        assertTrue(practicePage.getTestLoginPageLink().isDisplayed());
        assertTrue(practicePage.getTestExceptionsPageLink().isDisplayed());
    }

    @Test
    void verifyNavigateToTestLoginPage() {
        practicePage.clickTestLoginPage();

        assertEquals(properties.getProperty("loginPageTitle"), loginPage.getPageTitle());
        assertEquals(properties.getProperty("loginHeader"), loginPage.getPageHeader().getText());
        assertTrue(loginPage.getUsernameLabel().isDisplayed());
        assertTrue(loginPage.getUsernameField().isDisplayed());
        assertTrue(loginPage.getPasswordLabel().isDisplayed());
        assertTrue(loginPage.getPasswordField().isDisplayed());
        assertTrue(loginPage.getSubmitButton().isDisplayed());
        assertTrue(loginPage.errorNotPresent());
    }
}
