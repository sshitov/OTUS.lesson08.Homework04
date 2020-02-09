package Helpers.DriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ChromeWebDriver {

    protected static WebDriver driver;
    protected WebDriverWait wait;
    Actions action;

    public static void driverLoad(){
        WebDriverManager.chromedriver().setup();
    }

    public void create() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().setHeadless(false);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        action = new Actions(driver);
    }

    public static void quit(){
        if (driver != null) {
            driver.quit();
        }
    }
}
