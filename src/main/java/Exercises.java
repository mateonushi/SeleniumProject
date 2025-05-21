import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exercises {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
        String title = driver.getTitle();
        Actions actions = new Actions(driver);


        WebElement firstname = driver.findElement(By.xpath("//input[@placeholder='Full Name']"));
        firstname.sendKeys("Mateo Nushi");
        WebElement  email = driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        email.sendKeys("mateonushi@gmail.com");
        WebElement adres = driver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));
        adres.sendKeys("Tirana");
        WebElement adres2 = driver.findElement(By.xpath("//textarea[@id='permanentAddress']")) ;

        actions.click(adres)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .sendKeys("c")
                .keyUp(Keys.CONTROL)
                .perform();

        actions.click(adres2)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();


    }
}


