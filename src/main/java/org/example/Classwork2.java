package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.MemberSubstitution;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Classwork2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://neptun.al/");
        String title = driver.getTitle();


        WebElement hyrButton= driver.findElement(By.xpath("//div[@class='user_not_logged_system']"));
        hyrButton.click();
        Thread.sleep(1000);
        WebElement rregjistrimi = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ctl00_cmscontent_CmsMain_ctl00_ctl00_authapp']/login/div/div[2]/div/div[2]/p/a/span"));
        rregjistrimi.click();
        Thread.sleep(1000);
        WebElement firstname = driver.findElement(By.xpath("//input[@name='FirstName']"));
        firstname.sendKeys("Mateo");
        WebElement lastname = driver.findElement(By.xpath("//input[@name='LastName']"));
        lastname.sendKeys("Nushi");
        WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys("mateonushh12@gmail.com");
        WebElement kodi = driver.findElement(By.xpath("//input[@name='NewPassword']"));
        kodi.sendKeys("123456789");
        WebElement kodi2 = driver.findElement(By.xpath("//input[@name='ConfirmNewPassword']"));
        kodi2.sendKeys("123456789");
        WebElement rregjistrohu = driver.findElement(By.xpath("//a[@id='registerButton']"));
        rregjistrohu.click();
        Assert.assertTrue(driver.findElement(By.xpath("  //*[@id='typeahead-65-3839']/div/a/span")).isDisplayed(), "Product not found ");

        Thread.sleep(5000);
        System.out.println( title);









    }
 }