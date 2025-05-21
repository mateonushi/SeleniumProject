package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.UUID;

public class Claswork {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String email = "mateo" + UUID.randomUUID().toString().substring(0, 5) + "@gmail.com";
        String password = "mateo1234";

        try {
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");

            Cookie cfCookie = new Cookie.Builder("cf_clearance", "seSgJyb7s0awlEO6mCszPf3_fiB0yls.0p0WZZdAS18-1746027792-1.2.1.1-40YrNpWvkMOUfotqmiq0GcX1l1dFXCsnrjte9pKMnQ60Dk.eWU3kDUbopFiyYm1bNrldZ6lgK6AiQ1f3vcL7NmVhpSl2gMXcq2a9dzPCHL32yc62zhcDO7ag9INnOWD5ZQ9nItNM_n41GRWianmJi5IgrS3gSJye4wuLHhnyBluRCoi2wtvdwTJ.NLWyOUBhC7SxPTrQXlG5dBFuIEC5ozdzyr_183GG9U4whfI15iCVaXU3vykOJMpdNlV7eKpnPUHRSHPtsh_GA1owch9vE9VqNunWIgq2HMxW_4jtlI8rfN_A9aF_AY9ZpZUVprj.3wYnlfjS2FnBXf0YFHDNATQUxPzzjTU1yECGdctVFkE")
                    .domain("demo.nopcommerce.com")
                    .path("/")
                    .isHttpOnly(true)
                    .isSecure(true)
                    .expiresOn(new java.util.Date(System.currentTimeMillis() + Duration.ofHours(1).toMillis()))
                    .build();


            driver.manage().addCookie(cfCookie);
            driver.navigate().refresh();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-login']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and text()='Register']"))).click();

            Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Register", "Register page title not correct");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='gender-male']"))).click();
            driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Mateo");
            driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Nushi");
            driver.findElement(By.xpath("//input[@data-val-regex='Wrong email']")).sendKeys(email);
            driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Lufthansa");
            driver.findElement(By.xpath("//input[@type='password' and @name='Password']")).sendKeys(password);
            driver.findElement(By.xpath("//input[@type='password' and @name='ConfirmPassword']")).sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='register-button' and @type='submit']"))).click();

            Thread.sleep(2000);

            Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).isDisplayed(), "Registration success message not displayed");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-logout' and text()='Log out']"))).click();


            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-login' and text()='Log in']"))).click();

            driver.findElement(By.xpath("//input[@class='email']")).sendKeys(email);
            driver.findElement(By.xpath("//input[@class='password']")).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button-1 login-button']"))).click();

            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed(), "Logout button not displayed after login");


            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main']/div/div[2]/div[1]/div[2]/ul/li[1]/ul/li[1]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[@class='product-title']/a[contains(text(),'Lenovo IdeaCentre')]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-to-cart-button-3']"))).click();
            Thread.sleep(500);
            Assert.assertTrue(driver.findElement(By.xpath("//p[@class='content' and contains(text(),'The product has been added to your')]")).isDisplayed(), "Add to cart confirmation not displayed");

            driver.findElement(By.className("close")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cart-label']"))).click();

            Assert.assertTrue(driver.findElement(By.xpath("//td[@class='product']/a[contains(text(),'Lenovo IdeaCentre')]")).isDisplayed(), "Product not found in the cart");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Test completed successfully.");
            driver.quit();
        }
    }
}