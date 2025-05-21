package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.function.Function;

public class Test {

    public static void main(String args[]) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mateo\\Documents\\chromedriver.exe");
        // Instantiate a ChromeDriver class.
        WebDriver driver = new ChromeDriver();

        // Maximize the browser
        driver.manage().window().maximize();

        // Launch Website
        driver.get("");


        WebElement signInButton = driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]"));
        signInButton.click();













    }
}
