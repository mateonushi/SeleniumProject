package TestCase;

import Utils.BasePage;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Sorting;



public class TC_06_CheckSorting extends BasePage {
    @Test
    public void Sorting(){
        Sorting sorting = new Sorting(driver);
        navigateTo("https://ecommerce.tealiumdemo.com/men.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials("mmateondhushi222@gmail.com","12345678");
        sorting.hoverWomenClickViewAll();
        sorting.selectSortBy("Price");
        sorting.checkProductSorted();
        sorting.addProductsToWishListByName("Park Avenue Pleat Front Trousers", "Lafayette Convertible Dress");
        sorting.goToAccountVerifyProductsAreAdded("2");

    }

}
