package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class TC_01_User_Registration extends BasePage {
    @Test
    public void registrationForm()  {

        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/");
        homePage.clickAccount();
        homePage.clickRregister();
        registerPage.fillRegistrationForm("Mateo", "Test", "Nushi", "mmateondhushi222@gmail.com", "12345678");
        registerPage.singinNewsletter();
        registerPage.submitRegisterButton();
        homePage.clickAccount();
        homePage.clickLogout();


    }
}