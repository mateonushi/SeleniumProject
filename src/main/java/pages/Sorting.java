package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Utils.Waits.waitForElement;
import static Utils.Waits.waitForUrl;
import static org.testng.Assert.assertEquals;

public class Sorting {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    By womenMenu = By.xpath("//li[@class='level0 nav-1 first parent']");
    By viewAllList = By.xpath("//nav[@id='nav']//a[contains(@href, '/women.html') and contains(text(), 'View All Women')]");
    By dropdown = By.xpath("(//div[@class='toolbar']//select[@title='Sort By'])[1]");
    By wishlistProduct2 = By.xpath("//a[contains(@data-url, '/wishlist/index/add/product/420/')]");
    By returnToShoppingButtonAfterWishlistAdd = By.linkText("here");
    By accountClick = By.xpath("//*[@id='header']/div/div[2]/div/a");
    By wishlistLocator = By.xpath("//*[@id='header-account']/div/ul/li[2]/a");

    public Sorting(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void hoverWomenClickViewAll() {
        Actions actions = new Actions(driver);
        WebElement womenElement = driver.findElement(womenMenu);
        wait.until(ExpectedConditions.visibilityOf(womenElement));
        actions.moveToElement(womenElement).perform();
        WebElement viewAllElement = driver.findElement(viewAllList);
        actions.moveToElement(viewAllElement).click().perform();
    }

    public void selectSortBy(String visibleText) {
        WebElement dropdownElement = driver.findElement(dropdown);
        wait.until(ExpectedConditions.visibilityOf(dropdownElement));
        new Select(dropdownElement).selectByVisibleText(visibleText);
    }


    public void checkProductSorted() {
        List<WebElement> priceElements = driver.findElements(By.className("product-info"));
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            List<WebElement> pricePerProductList = priceElement.findElements(By.className("price"));
            String pricePerProduct = pricePerProductList.get(pricePerProductList.size() - 1).getText().replace("$", "");

            if (pricePerProduct.isEmpty()) {
                throw new AssertionError("Product is missing");
            } else {
                System.out.println("Price for  product is: $" + pricePerProduct);
            }


            double price = Double.parseDouble(pricePerProduct);
            prices.add(price);
        }

        boolean sorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                sorted = false;
                break;
            }
        }

        if (!sorted) {
            throw new AssertionError("Prices are NOT sorted in ascending order.");
        } else {
            System.out.println("Prices are sorted in ascending order.");
        }
    }

    public void goToAccountVerifyProductsAreAdded(String productsQuantity) {
        waitForUrl(driver, "/women.html?dir=asc&order=price");
        WebElement account = driver.findElement(accountClick);
        account.click();
        WebElement wishlistElement = driver.findElement(wishlistLocator);
        waitForElement(driver, wishlistElement);
        String actualText = wishlistElement.getText();
        String expectedText = "My Wishlist ("+productsQuantity+" items)";
        assertEquals(actualText, expectedText, "Wishlist Incorrect Number of Elements");
    }


    public void addProductsToWishListByName(String... productNames) {
        for (String name : productNames) {
            By productWishlistButton = By.xpath("//div[contains(@class, 'product-info')][.//h2[@class='product-name']" +
                    "/a[contains(text(), '" + name + "')]]//a[contains(@data-url, '/wishlist/index/add')]");
            WebElement wishlistButton = driver.findElement(productWishlistButton);
            js.executeScript("arguments[0].scrollIntoView(true);", wishlistButton);
            wait.until(ExpectedConditions.visibilityOf(wishlistButton));
            wishlistButton.click();

            WebElement continueBtn = driver.findElement(returnToShoppingButtonAfterWishlistAdd);
            wait.until(ExpectedConditions.visibilityOf(continueBtn));
            continueBtn.click();
        }
    }

}
