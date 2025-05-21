package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class DashboardPage {
     WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    By account = By.xpath("//a[@class='skip-link skip-account']");
    By loginbutton = By.xpath("//a[@title='Log In']");
    By rememberMe = By.xpath("//input[@type='checkbox']");
    By Loginclick2 = By.xpath("//button[@id='send2']");
    By accountclick = By.xpath("//a[@data-target-element='#header-account']");
    By accountLogout = By.xpath("//a[@title='Log Out']");

    public void acountClick() {
        driver.findElement(account).click();
    }

    public void loginclick() {
        driver.findElement(loginbutton).click();
    }

    public void rememberMeClick() {
        driver.findElement(rememberMe).click();
    }

    public void isUserLoggedIn(String userName) {
        String actualText = driver.findElement(By.xpath("//p[@class='welcome-msg']")).getText();
        String expectedText = userName;
        assertEquals(actualText,expectedText,"Registration unsuccessful");
    }

    public void accountClickk() {
        driver.findElement(accountclick).click();
    }

    public void accountLogoutt() {
        acountClick();
        driver.findElement(accountLogout).click();
    }

}
