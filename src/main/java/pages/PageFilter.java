package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Utils.Waits.*;

public class PageFilter {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    private final By manMenu = By.xpath("//li[@class='level0 nav-2 parent']");
    private final By viewAllMenLink = By.xpath("//nav[@id='nav']//a[contains(@href, '/men.html') and contains(text(), 'View All Men')]");
    private final By btnRemove = By.xpath("//a[@class='btn-remove']");
    By priceFilters = By.cssSelector("dd.even ol li a");
    private final By productContainer = By.xpath("//div[@class='product-info']");
    By productItemsLocator = By.cssSelector("ul.products-grid li.item");
    private final By productPrice = By.cssSelector(".price");
    private final String dynamicColorXpathFormat = "//a[contains(@class,'swatch-link')]/span/img[@alt='%s']/ancestor::a";
    private final String priceFilterXpathFormat = "//a[contains(@href,'price=%s')]";


    public PageFilter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void hoverOverManClickViewAll() {
        WebElement man = driver.findElement(manMenu);
        WebElement viewAll = driver.findElement(viewAllMenLink);
        Actions actions = new Actions(driver);
        actions.moveToElement(man).perform();
        actions.moveToElement(viewAll).click().perform();
    }

    public void selectColor(String colorName) {
        By dynamicColorLocator = By.xpath(String.format(dynamicColorXpathFormat, colorName));
        WebElement colorElement = driver.findElement(dynamicColorLocator);
        wait.until(ExpectedConditions.visibilityOf(colorElement));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", colorElement);
        colorElement.click();
    }

    public void clickRemoveButton() {
        WebElement removeBtnElement = driver.findElement(btnRemove);
        removeBtnElement.click();
    }

    public void selectPriceRange(String priceRange) {
        String xpath = String.format("//dd[@class='even']//a[contains(@href, 'price=%s')]", priceRange);
        WebElement priceFilter = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(priceFilter));
        priceFilter.click();
    }


    public void verifyColorSelection() {
        waitForUrl(driver,"/men.html?color=20");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItemsLocator));
        List<WebElement> products = driver.findElements(productContainer);
        for (WebElement product : products) {
            WebElement blackColorSwatch = product.findElement(By.cssSelector("li[data-option-label='black'] .swatch-label"));
            String borderColor = blackColorSwatch.getCssValue("border-color");
            System.out.println("Border color: " + borderColor);
            if (!borderColor.equals("rgb(255, 255, 255)") && !borderColor.equals("#3399cc")) {
                throw new AssertionError("Black swatch not selected for a product");
            } else {
                System.out.println("Color is selected correctly.");
            }
        }
    }

    public void verifyPrices(double minPrice, double maxPrice) {
        waitForUrl(driver,"/men.html?price=-100");
        List<WebElement> products = driver.findElements(productContainer);
        for (WebElement product : products) {
            WebElement priceElement = product.findElement(productPrice);
            String priceText = priceElement.getText();
            System.out.println("Product price: " + priceText);
            double price = Double.parseDouble(priceText.replace("$", ""));
            if (price < minPrice || price > maxPrice) {
                throw new AssertionError("Unexpected price " + priceText);
            }
        }
    }

}
