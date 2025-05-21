package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.WishList;

public class TC_07_ShoppingCart extends BasePage {
    @Test
    public void Section7Test() {
        WishList wishList = new WishList(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/men.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials("mmateondhushi222@gmail.com", "12345678");
        wishList.goToMyWishList();
        wishList.addFirstItemToWishlistItems("Taupe",0);
        wishList.addSecondItemToWishlistItems("Blue",0);
        wishList.clickAddAllToCart();
        wishList.updateProductQuantityInCart(0,2);
        wishList.verifyCartTotalPrice();


    }
}
