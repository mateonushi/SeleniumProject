package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import static Utils.BasePage.driver;

public class HandleCookies {
        public static void acceptCookies() {
            try {
                WebElement acceptCookiesButton = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]"));
                acceptCookiesButton.click();
                WebElement submitButton = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]"));
                submitButton.click();
            }catch (TimeoutException e){
                System.out.println("No cookies prompt found!");
            }
        }
    }
