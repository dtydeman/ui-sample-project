package org.dtydeman.tests;

import org.dtydeman.pages.TestContactPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestContactPageTests extends BaseTest {

    private final TestContactPage contactPage = new TestContactPage();

    @BeforeEach
    void setURL() {
        driver.get(properties.getProperty("baseURL")+"contact/");
    }

    @Test
    void verifyAccessToContactPage() {
        assertEquals(properties.getProperty("contactPageTitle"), contactPage.getPageTitle());
        assertEquals(properties.getProperty("contactHeader"), contactPage.getPageHeader().getText());
        assertTrue(contactPage.getNameLabel().isDisplayed());
        assertEquals("Name *", contactPage.getNameLabel().getText());
        assertTrue(contactPage.getForenameField().isDisplayed());
        assertTrue(contactPage.getSurnameField().isDisplayed());
        assertTrue(contactPage.getEmailLabel().isDisplayed());
        assertEquals("Email *", contactPage.getEmailLabel().getText());
        assertTrue(contactPage.getEmailField().isDisplayed());
        assertTrue(contactPage.getCommentMessageLabel().isDisplayed());
        assertEquals("Comment or Message *", contactPage.getCommentMessageLabel().getText());
        assertTrue(contactPage.getCommentMessageField().isDisplayed());
        assertTrue(contactPage.getSubmitButton().isDisplayed());
        List<String> fieldErrors = List.of("forename", "surname", "email", "commentMessage");
        for(String field : fieldErrors) {
            assertTrue(contactPage.errorNotPresent(field));
        }
    }

    @Test
    void verifyErrorsWhenFieldsEmpty() {
        contactPage.clickSubmit();

        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getForenameFieldError().getText());
        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getSurnameFieldError().getText());
        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getEmailFieldError().getText());
        assertEquals(properties.getProperty("contactErrorMessage"), contactPage.getCommentMessageFieldError().getText());
    }

}
