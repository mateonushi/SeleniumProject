package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class WomenMenu {
    WebDriver driver;
    WebDriverWait wait;

    private final By womenMenuLocator = By.xpath("//li[@class='level0 nav-1 first parent']");
    private final By viewAllListLocator = By.xpath("//nav[@id='nav']//a[contains(@href, '/women.html') and contains(text(), 'View All Women')]");
    private final By allProductsLocator = By.xpath("//ul[contains(@class, 'products-grid')]//li[contains(@class, 'item')]");

    public WomenMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement getWomenMenu() {
        return driver.findElement(womenMenuLocator);
    }

    private WebElement getViewAllList() {
        return driver.findElement(viewAllListLocator);
    }

    private List<WebElement> getAllProducts() {
        return driver.findElements(allProductsLocator);
    }

    public void hoverWomenProduct() {
        Actions actions = new Actions(driver);
        WebElement womenMenu = getWomenMenu();
        wait.until(ExpectedConditions.visibilityOf(womenMenu));
        actions.moveToElement(womenMenu).perform();

        WebElement viewAllList = getViewAllList();
        wait.until(ExpectedConditions.elementToBeClickable(viewAllList));
        actions.moveToElement(viewAllList).click().perform();
    }

    public void hoverProductByTitleCheckStyle(String productTitle) {
        List<WebElement> products = getAllProducts();
        boolean found = false;

        for (WebElement product : products) {
            WebElement titleElement = product.findElement(By.xpath(".//h2[@class='product-name']/a"));
            if (!titleElement.getText().isEmpty()) {
                WebElement image = product.findElement(By.xpath(".//img[contains(@id, 'product-collection-image')]"));
                wait.until(ExpectedConditions.visibilityOf(image));
                new Actions(driver).moveToElement(image).perform();

                String borderColor = image.getCssValue("border-color");
                System.out.println("Hovered product: " + productTitle + " | Border color: " + borderColor);
                assertEquals(borderColor, "rgb(51, 153, 204)", "Border color does not match expected value.");
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("Product with title '" + productTitle + "' not found.");
        }
    }
}