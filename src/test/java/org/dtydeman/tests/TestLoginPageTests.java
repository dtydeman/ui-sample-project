package org.dtydeman.tests;

import org.dtydeman.pages.TestLoggedInSuccessfullyPage;
import org.dtydeman.pages.TestLoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class TestLoginPageTests extends BaseTest {

    private final TestLoginPage loginPage = new TestLoginPage();
    private final TestLoggedInSuccessfullyPage loggedInPage = new TestLoggedInSuccessfullyPage();

    @BeforeEach
    void setURL() {
        driver.get(properties.getProperty("baseURL")+"practice-test-login/");
    }

    @Test
    void verifyAccessToTestLoginPage() {
        assertEquals(properties.getProperty("loginPageTitle"), loginPage.getPageTitle());
        assertEquals(properties.getProperty("loginHeader"), loginPage.getPageHeader().getText());
        assertTrue(loginPage.getUsernameLabel().isDisplayed());
        assertEquals("Username", loginPage.getUsernameLabel().getText());
        assertTrue(loginPage.getUsernameField().isDisplayed());
        assertTrue(loginPage.getPasswordLabel().isDisplayed());
        assertEquals("Password", loginPage.getPasswordLabel().getText());
        assertTrue(loginPage.getPasswordField().isDisplayed());
        assertTrue(loginPage.getSubmitButton().isDisplayed());
        assertTrue(loginPage.errorNotPresent());
    }

    @Test
    void verifySuccessfulLogin() {
        loginPage.enterUsername(properties.getProperty("validUsername"));
        loginPage.enterPassword(properties.getProperty("validPassword"));
        loginPage.clickSubmit();

        assertEquals(properties.getProperty("successPageTitle"), loggedInPage.getPageTitle());
        assertTrue(loggedInPage.getPageHeader().isDisplayed());
        assertTrue(loggedInPage.getContent().isDisplayed());
        assertTrue(loggedInPage.getLogoutButton().isDisplayed());
    }

    @ParameterizedTest
    @MethodSource("invalidLogins")
    void verifyInvalidLoginAttempt(String username, String password, String errorMessage) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();

        assertTrue(loginPage.getError().isDisplayed());
        assertEquals(errorMessage, loginPage.getError().getText(), loginPage.getError().getText());

    }

    private static Stream<Arguments> invalidLogins() {
        return Stream.of(
                Arguments.of("", "", "Your username is invalid!"),
                Arguments.of("invalid", "", "Your username is invalid!"),
                Arguments.of("", "invalid", "Your username is invalid!"),
                Arguments.of("invalid", "invalid", "Your username is invalid!"),
                Arguments.of(properties.getProperty("validUsername"), "", "Your password is invalid!"),
                Arguments.of(properties.getProperty("validUsername"), "invalid", "Your password is invalid!"),
                Arguments.of("", properties.getProperty("validPassword"), "Your username is invalid!"),
                Arguments.of("invalid", properties.getProperty("validPassword"), "Your username is invalid!"));
    }

}
