package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class RegisterPage {
    WebDriver driver;
    JavascriptExecutor js;

    By firstName = By.xpath("//input[@id='firstname']");
    By midlename = By.xpath("//input[@id='middlename']");
    By lastname = By.id("lastname");
    By email = By.xpath("//input[@id='email_address']");
    By confirmPassword = By.xpath("//input[@id='password']");
    By reconfirmmPasword = By.xpath("//input[@id='confirmation']");
    By newsLetter = By.xpath("//input[@name='is_subscribed']");
    By rememberMe = By.xpath("//input[@name='persistent_remember_me']");
    By registerbutton = By.xpath("//button[@title='Register']");
    By confirmationMessage = By.xpath("//*[@id='top']/body/div[2]/div/div[2]/div/div[2]/div[2]/div/ul/li/ul/li/span");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void fillRegistrationForm(String fname, String lname, String LLname, String mail, String password) {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(midlename).sendKeys(lname);
        driver.findElement(lastname).sendKeys(LLname);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(confirmPassword).sendKeys(password);
        driver.findElement(reconfirmmPasword).sendKeys(password);
    }

    public void singinNewsletter() {
        WebElement newsletterElement = driver.findElement(newsLetter);
        js.executeScript("arguments[0].scrollIntoView(true);", newsletterElement);
        newsletterElement.click();
        driver.findElement(rememberMe).click();
    }


    public void submitRegisterButton() {
        driver.findElement(registerbutton).click();
        WebElement confirmation = driver.findElement(confirmationMessage);
        String actualText = confirmation.getText();
        String expectedText = "Thank you for registering with Tealium Ecommerce.";
        assertEquals(actualText, expectedText, "Registration was unsuccessful");
    }
}