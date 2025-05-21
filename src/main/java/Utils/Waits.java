package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    public static WebDriverWait getWait(WebDriver driver){
        final int TIMEOUT = 7;
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        
    }
    public static void waitForUrl(WebDriver driver, String url){
        getWait(driver).until(d -> urlContainsValue(d, url));
    }
    public static boolean urlContainsValue(WebDriver driver, String url){
        return getWait(driver).until(ExpectedConditions.urlContains(url));
    }

    public static void waitForElement(WebDriver driver, WebElement element){
        getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }


}
