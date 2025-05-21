package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Utils.Waits.waitForUrl;
import static org.testng.Assert.assertEquals;

public class ShoppingCart {
    WebDriver driver;

    private By cartProductsNr = By.xpath("//span[@class='count']");
    private By firstRemoveButtonLocator = By.cssSelector("td.product-cart-remove a.btn-remove2");
    private By emptyCartMessageLocator = By.xpath("//p[@class='empty']");
    private By cartLocator = By.xpath("//a[contains(@class, 'skip-cart') and .//span[text()='Cart']]");
    private By shoppingCartLocator = By.xpath("//a[@class='cart-link' and contains(text(),'View Shopping Cart')]");




    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        String countText = driver.findElement(cartProductsNr).getText().trim();
        return Integer.parseInt(countText);
    }

    public void removeFirstCartItem() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("td.product-cart-remove a.btn-remove2"));
        removeButtons.get(0).click();
    }
    public void removeSecondCartItem() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("td.product-cart-remove a.btn-remove2"));
        removeButtons.get(0).click();
    }

    public void goToCart() {
        WebElement cartButton = driver.findElement(cartLocator);
        cartButton.click();
        WebElement shoppingCartButton = driver.findElement(shoppingCartLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shoppingCartButton);
        shoppingCartButton.click();
    }


    public void verifyCartItemCountDecreased(int previousNrOfItems) {
        int currentProductNr = getCartItemCount();
        assertEquals(currentProductNr, previousNrOfItems - 1, "Cart item are not reduced");
    }

    public void verifyCartIsEmpty() {
        waitForUrl(driver,"/checkout/cart/");
        String actualEmptyText = driver.findElement(emptyCartMessageLocator).getText().trim();
        String expectedEmptyText = "You have no items in your shopping cart.";
        assertEquals(actualEmptyText, expectedEmptyText, "Cart is not empty when it should be.");
    }

    public void removeAllItems() {
        int itemCount = getCartItemCount();
        while (itemCount > 0) {
            removeFirstCartItem();
            verifyCartItemCountDecreased(itemCount);
            itemCount = getCartItemCount();
        }
    }


}
