package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PageFilter;

public class TC_05_CheckPageFilters extends BasePage {
    @Test
    public void Sector5() {
        PageFilter pageFilter5 = new PageFilter(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/sale.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials("mmateondhushi222@gmail.com","12345678");
        pageFilter5.hoverOverManClickViewAll();
        pageFilter5.selectColor("Black");
        pageFilter5.verifyColorSelection();
        pageFilter5.clickRemoveButton();
        pageFilter5.selectPriceRange("-100");
        pageFilter5.verifyPrices(0,100);
    }
}
