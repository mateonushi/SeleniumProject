package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class WishList {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    private final By accountLabel = By.xpath("//span[@class='label' and text()='Account']");
    private final By wishlistLink = By.xpath("//a[@title='My Wishlist (2 items)']");
    private final By firstWishlistEditButton = By.xpath("(//a[@class='link-edit button button-secondary'])[1]");
    private final By secondWishlistEditButton = By.xpath("//a[@class='link-edit button button-secondary']");
    private final By returnToShoppingButtonAfterWishlistAdd = By.xpath("//button[@title='Continue Shopping']");
    private final By updateWishlistButton = By.xpath("//a[@class='link-compare' and text()='Update Wishlist']");
    private final By addToCartButtonWhite = By.xpath("//*[@id='item_1097']/td[5]/div/button");
    private final By addToCartButtonBlack = By.xpath("//*[@id='item_1097']/td[5]/div/button/span/span");
    private final By addAllToCartButton = By.xpath("//button[@title='Add All to Cart']");


    public WishList(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAccount() {
        driver.findElement(accountLabel).click();
    }

    public void clickWishlist() {
        driver.findElement(wishlistLink).click();
    }

    public void clickEditFirstWishlistItem() {
        driver.findElement(firstWishlistEditButton).click();
    }

    public void clickEditSecondWishlistItem() {
        driver.findElement(secondWishlistEditButton).click();
    }

    public void selectColor(String color) {
        WebElement colorOption = driver.findElement(By.xpath("//img[@alt='" + color + "']"));
        scrollToElement(colorOption);
        colorOption.click();
    }

    public void selectSizeByIndex(int index) {
        List<WebElement> sizeOptions = driver.findElements(By.xpath("//ul[@id='configurable_swatch_size']//a[contains(@class,'swatch-link')]"));
        WebElement selectedOption = sizeOptions.get(index);
        scrollToElement(selectedOption);
        selectedOption.click();
    }


    public void clickUpdateWishlist() {
        WebElement update = driver.findElement(updateWishlistButton);
        scrollToElement(update);
        update.click();
    }

    public void clickAddAllToCart() {
        WebElement addAllToCart = driver.findElement(addAllToCartButton);
        scrollToElement(addAllToCart);
        addAllToCart.click();
    }

    public void clickAddToCartForBlack() {
        driver.findElement(addToCartButtonBlack).click();
    }

    public void clickAddToCartButton() {
        driver.findElement(By.xpath("//button[@onclick='productAddToCartForm.submit(this)']")).click();
    }

    public void goToMyWishList() {
        clickAccount();
        clickWishlist();
    }


    public void addFirstItemToWishlistItems(String color, int sizeIndex) {
        clickEditFirstWishlistItem();
        selectColor(color);
        selectSizeByIndex(sizeIndex);
        clickUpdateWishlist();
    }

    public void addSecondItemToWishlistItems(String color, int sizeIndex) {
        clickEditSecondWishlistItem();
        selectColor(color);
        selectSizeByIndex(sizeIndex);
        clickUpdateWishlist();
    }

    public void updateProductQuantityInCart(int productIndex, int quantity) {
        var productRows = driver.findElements(By.xpath("//*[@id='shopping-cart-table']/tbody/tr"));
        WebElement quantityInput = productRows.get(productIndex).findElement(By.xpath(".//input[@title='Qty']"));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));

        WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
        scrollToElement(updateButton);
        updateButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping-cart-table")));
    }

    public void verifyCartTotalPrice() {
        var productRows = driver.findElements(By.xpath("//*[@id='shopping-cart-table']/tbody/tr"));
        double sum = 0.0;

        for (WebElement row : productRows) {
            String priceText = row.findElement(By.xpath(".//span[@class='price']")).getText();
            String qtyValue = row.findElement(By.xpath(".//input[@title='Qty']")).getAttribute("value");
            double price = parsePrice(priceText);
            int qty = Integer.parseInt(qtyValue);
            sum += price * qty;
        }

        WebElement grandTotalElement = driver.findElement(
                By.xpath("//table[@id='shopping-cart-totals-table']//tfoot//tr/td[2]//span[@class='price']"));
        String grandTotalText = grandTotalElement.getText();
        double grandTotal = parsePrice(grandTotalText);

        assertEquals(sum, grandTotal, "Sum of item totals does not match the Grand Total.");
    }


    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
