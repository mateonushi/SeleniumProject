package Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static Utils.HandleCookies.acceptCookies;

public class BasePage {
        protected static WebDriver driver;


        @BeforeMethod
        public void setUp() {

            WebDriverManager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        }

        public void navigateTo(String url) {
            driver.get(url);
            acceptCookies();
        }

        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }

        }
    }

