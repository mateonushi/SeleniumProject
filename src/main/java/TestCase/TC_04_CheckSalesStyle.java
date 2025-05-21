package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Sales;

public class TC_04_CheckSalesStyle extends BasePage {
    @Test
    public void viewSales() {
        Sales sales = new Sales(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/women.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials("mmateondhushi222@gmail.com", "12345678");
        sales.hoverSales();
        sales.verifyPriceAndColorAreShown();
        sales.verifyPriceStyle();
    }
}
