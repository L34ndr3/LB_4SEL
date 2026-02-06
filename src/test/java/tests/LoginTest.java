package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import io.qameta.allure.*;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Login functionality")
public class LoginTest extends BaseTest {

    @Test
    @Story("Successful login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can log in with valid credentials")
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();

        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

       assertTrue(
                inventoryPage.isDisplayed(),
                "Inventory page should be displayed after successful login"
        );
    }

    @Test
    @Story("Login with locked user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a locked out user cannot log in and sees an error message")
    public void testLoginWithLockedUser() {
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed"
        );
        assertTrue(
                loginPage.getErrorMessage().toLowerCase().contains("locked out"),
                "Error message should mention that the user is locked out"
        );
    }

    @Test
    @Story("Login with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that login fails when using an invalid password")
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.login("standard_user", "wrong_password");

        assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid password"
        );
    }

    @Test
    @Story("Login with empty credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that login fails when username and password are empty")
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.login("", "");

        assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed when credentials are empty"
        );
    }
}
