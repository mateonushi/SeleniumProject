package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ShoppingCart;

public class TC_08_EmptyShoppingCart extends BasePage {
    @Test
    public void Section8Test()  {
        ShoppingCart shoppingCart = new ShoppingCart(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/men.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials("mmateondhushi222@gmail.com","12345678");
        shoppingCart.goToCart();
        shoppingCart.removeFirstCartItem();
        shoppingCart.verifyCartItemCountDecreased(2);
        shoppingCart.removeSecondCartItem();
        shoppingCart.verifyCartIsEmpty();
    }
}
