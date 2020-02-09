package MIFTests;

import Helpers.DriverManagers.ChromeWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class baseTest {

    public static final Logger logger = LogManager.getLogger(BookInfoCrawler.class.getName());
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    Actions action;

    protected String baseUrl = "https://www.mann-ivanov-ferber.ru/";
    protected String audioBook = "books/allbooks/?booktype=audiobook";

    FileWriter writer;
    File file;

    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    public void driverInitialize(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().setHeadless(false);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        action = new Actions(driver);
    }

    public void driverClose(){
        if (driver != null) {
            driver.quit();
        }

    }

    public void openStartPage(String url) {
        driver.get(url);
    }


    public ArrayList<String> getUrlList() throws InterruptedException {
        ArrayList<String> urls = new ArrayList<>();

        while (true){

            List<WebElement> oldElementsState = driver.findElements(By.cssSelector(".c-continuous-list .lego-book"));

            action.moveToElement(oldElementsState.get(oldElementsState.size() - 1)).perform();
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");

           // Wait for the list is load (find a more elegant solution)
            Thread.sleep(2000);

            List<WebElement> newElementsState = driver.findElements(By.cssSelector(".c-continuous-list .lego-book"));

            if(newElementsState.size() == oldElementsState.size()){
                logger.debug(newElementsState.size());
                for (WebElement webElement : newElementsState) {
                    String url = webElement.findElement(By.cssSelector("[href]")).getAttribute("href");
                    urls.add(url);
                }
                break;
            }
        }

        return urls;
    }

    public ArrayList<String> collectInfoOnThePage(String url){

      ArrayList<String> book = new ArrayList<>();

      driver.get(url);

      String bookLink = driver.getCurrentUrl();
      String bookName = driver.findElement(By.cssSelector("h1 span")).getAttribute("innerText");
      String bookAuthor = driver.findElement(By.cssSelector(".position-top .author.active")).getAttribute("innerText");
      String bookPrice = driver.findElement(By.cssSelector(".l-book-buy-panel-top [data-type='audiobook']")).getAttribute("data-price");
      String bookDescription = driver.findElement(By.cssSelector("#about .description")).getAttribute("innerText");

      book.add(bookLink + ";" + bookName + ";" + bookAuthor + ";" + bookPrice + ";" + bookDescription + ";");

      return book;
    }

    public void createCsvFile() throws IOException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss");

        file = new File(String.format("books/bookList_%s.csv", formatForDateNow.format(dateNow)));

        //Create the file
        if (file.createNewFile())
        {
            logger.debug("File is created!");
        }

        writer = new FileWriter(file);
        writer.write("Link;Name;Author;Price;Description");
        writer.write("\n");
        writer.close();

    }

    public void writeToCsvFile(ArrayList<String> array) throws IOException {
        writer = new FileWriter(file, true);
        for (String s : array) {
            writer.write(s);
            writer.write("\n");
        }
        writer.close();

    }



}
