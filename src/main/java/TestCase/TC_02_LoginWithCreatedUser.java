package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.*;


public class TC_02_LoginWithCreatedUser extends BasePage {

    @Test
    public void Login2() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithCredentials("mmateondhushi222@gmail.com", "12345678");
        dashboardPage.isUserLoggedIn("WELCOME, MATEO TEST NUSHI!");
        dashboardPage.accountLogoutt();

    }
}
