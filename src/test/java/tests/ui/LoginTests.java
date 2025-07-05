package tests.ui;

import base.TestBase;
import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends TestBase {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void validLoginTest(){
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginValidUser("Admin", "admin123");
        String currentURL = page.url();
        Assert.assertTrue(currentURL.contains("/dashboard"), "Login fail to reach Dashboard");
    }

    @Test
    public void inValidLoginTest(){
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginValidUser("Admin", "wrongpassword");
        Assert.assertEquals(loginPage.gerErrorMessage(), "Invalid credentials", "invalid user login");
    }
}
