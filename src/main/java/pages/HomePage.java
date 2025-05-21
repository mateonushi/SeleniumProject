package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utils.Waits.waitForUrl;
import static org.testng.AssertJUnit.assertEquals;


public class HomePage {
    WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By account = By.xpath("//span[@class='label' and text()='Account']");
    By registerButton = By.xpath("//a[@title='Register']");
    By logOut = By.xpath("//a[@title='Log Out']");


    public void clickAccount() {
        driver.findElement(account).click();
    }

    public RegisterPage clickRregister() {
        driver.findElement(registerButton).click();
        waitForUrl(driver,"/customer/account/create/");
        String title = driver.getTitle();
        assertEquals("Title did not match ","Create New Customer Account",title);
        return new RegisterPage(driver);
    }


    public void clickLogout() {
        driver.findElement(logOut).click();
    }



}
