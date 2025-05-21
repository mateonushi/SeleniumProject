
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Test2 {
    public static void main (String[]args) {

        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();


        driver.manage().window().maximize();

        String baseUrl = "http://qatechhub.com";


        /* launch Chrome and direct it to the Base URL
         *   get(String arg0): void – This method Load a new web page in the current browser window.
         *   Accepts String as a parameter and returns nothing.
         */
        driver.get(baseUrl);



        /*  getTitle(): String – This method fetches the Title of the current page.
         *   Accepts nothing as a parameter and returns a String value.
         */
        String title = driver.getTitle();
        System.out.println("The title of the page is: " + title);



        /*  getCurrentUrl(): String – This method fetches the string representing the Current URL which is opened in the browser.
         *  Accepts nothing as a parameter and returns a String value.
         */
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL is: " + currentURL);

        if (currentURL.equals(baseUrl)) {
            System.out.println("Verification Successful - The correct Url is opened.");
        } else {
            System.out.println("Verification Failed - An incorrect Url is opened.");

            //In case of Fail, you like to print the actual and expected URL for the record purpose
            System.out.println("Actual URL is : " + currentURL);
            System.out.println("Expected URL is : " + baseUrl);
        }



        /*  close(): void – This method Closes only the current window the WebDriver is currently controlling.
         *  Accepts nothing as a parameter and returns nothing.
         */
        driver.close();



        /*  quit(): void – This method Closes all windows opened by the WebDriver.
         *  Accepts nothing as a parameter and returns nothing.
         */
        driver.quit();

    }
}
