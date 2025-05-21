package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Utils.Waits.waitForElement;

public class LoginPage {
    WebDriver driver;

    By account = By.xpath("//span[@class='label' and text()='Account']");
    By loginLink = By.xpath("//a[@title='Log In']");
    By emailField = By.xpath("//input[@id='email']");
    By passwordField = By.xpath("//input[@id='pass']");
    By rememberCheckbox = By.xpath("//input[@type='checkbox']");
    By loginButton = By.xpath("//button[@id='send2']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAccount() {
        WebElement acc = driver.findElement(account);
        waitForElement(driver,acc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", acc);
        acc.click();
    }

    public void clickLoginLink() {
        WebElement login = driver.findElement(loginLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);
        login.click();
    }

    public DashboardPage loginWithCredentials(String email, String password) {
        clickAccount();
        clickLoginLink();

        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);

        WebElement remember = driver.findElement(rememberCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", remember);
        remember.click();

        WebElement loginBtn = driver.findElement(loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        loginBtn.click();
        return new DashboardPage(driver);

    }

    public void rememberMe() {
        WebElement remember = driver.findElement(rememberCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", remember);
        remember.click();
    }

    public void clickLoginButton() {
        WebElement loginBtn = driver.findElement(loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        loginBtn.click();
    }
}