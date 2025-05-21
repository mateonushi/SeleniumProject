package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Sales {
    WebDriver driver;
    WebDriverWait wait;

    By salesMenu = By.xpath("//a[@class='level0 has-children' and text()='Sale']");
    By viewAllList = By.xpath("//a[@class='level0 has-children' and text()='Sale']/following-sibling::ul//a[text()='View All Sale']");
//    By pricesBox = By.xpath("//div[@class='price-box']");
//    By cmimiMeUlje = By.xpath("//span[@id='old-price-423']");
//    By cmimiOrigjinal = By.xpath("//span[@id='product-price-423']");

    public Sales(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverSales()  {
        WebElement salesElement = driver.findElement(salesMenu);
        wait.until(ExpectedConditions.visibilityOf(salesElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(salesElement).perform();
        WebElement viewAllElement = driver.findElement(viewAllList);
        actions.moveToElement(viewAllElement).click().perform();
    }

    public List<WebElement> verifyProductAreShown() {
        List<WebElement> products = driver.findElements(By.className("product-info"));

        if (products.size() != 4) {
            throw new AssertionError("Expected 4 products, but found: " + products.size());
        }

        return products;
    }

    public void verifyPriceAndColorAreShown() {
        List<WebElement> products = verifyProductAreShown();

        for (WebElement product : products) {

            List<WebElement> originalPrices = product.findElements(By.cssSelector(".old-price .price"));
            List<WebElement> discountedPrices = product.findElements(By.cssSelector(".special-price .price"));

            if (originalPrices.isEmpty() || discountedPrices.isEmpty()) {
                throw new AssertionError("Product does not show prices ");
            }

            WebElement originalPrice = originalPrices.get(0);
            WebElement discountedPrice = discountedPrices.get(0);

            if (originalPrice.getText().isEmpty() || discountedPrice.getText().isEmpty()) {
                throw new AssertionError("Price text is missing ");
            }

            String color = discountedPrice.getCssValue("color");
            if (!color.contains("51, 153, 204, 1")) {
                throw new AssertionError("Discounted price color is missing");
            } else {
                System.out.println("Discounted price color is shown ");
            }
        }
    }

    public void verifyPriceStyle() {
        List<WebElement> products = verifyProductAreShown();

        for (WebElement product : products) {
            WebElement originalPrice = product.findElement(By.cssSelector(".old-price .price"));

            String originalPriceText = originalPrice.getText();
            if (originalPriceText.isEmpty()) {
                throw new AssertionError("Original price is missing ");
            }

            String textDecoration = originalPrice.getCssValue("text-decoration-line");
            if (!textDecoration.contains("line-through")) {
                throw new AssertionError("Original price is not strikethrough for product");
            } else {
                System.out.println("Original price has strikethrough " );
            }
        }
    }

}