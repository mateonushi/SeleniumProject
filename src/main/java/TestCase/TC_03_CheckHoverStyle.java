package TestCase;
import Utils.BasePage;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.WomenMenu;

public class TC_03_CheckHoverStyle extends BasePage {

    @Test
    public void Section3() {

        WomenMenu womenMenu = new WomenMenu(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials("mateonushi222@gmail.com","12345678");
        womenMenu.hoverWomenProduct();
        womenMenu.hoverProductByTitleCheckStyle("Elizabeth Knit Top");

    }
}
