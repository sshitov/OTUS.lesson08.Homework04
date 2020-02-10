package Helpers.TestSteps;

import Helpers.DriverManagers.ChromeWebDriver;
import Helpers.FileManager.FileManager;
import MIFTests.BookInfoCrawler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Steps {

    private static final Logger logger = LogManager.getLogger(BookInfoCrawler.class.getName());
    FileManager fileManager = new FileManager();
    ChromeWebDriver chromeWebDriver = new ChromeWebDriver();

    public void openPage(String url) {
                ChromeWebDriver.getDriver().get(url);
    }


    public ArrayList<String> getUrlList() throws InterruptedException {
        ArrayList<String> urls = new ArrayList<>();

        while (true){

            List<WebElement> oldElementsState = ChromeWebDriver.getDriver().findElements(By.cssSelector(".c-continuous-list .lego-book"));

            chromeWebDriver.getAction().moveToElement(oldElementsState.get(oldElementsState.size() - 1)).perform();
            ((JavascriptExecutor) ChromeWebDriver.getDriver()).executeScript("window.scrollBy(0, 500)");

           // Wait for the list is load (find a more elegant solution)
            Thread.sleep(2000);

            List<WebElement> newElementsState = ChromeWebDriver.getDriver().findElements(By.cssSelector(".c-continuous-list .lego-book"));

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

        ChromeWebDriver.getDriver().get(url);

      String bookLink = ChromeWebDriver.getDriver().getCurrentUrl();
      String bookName = ChromeWebDriver.getDriver().findElement(By.cssSelector("h1 span")).getAttribute("innerText");
      String bookAuthor = ChromeWebDriver.getDriver().findElement(By.cssSelector(".position-top .author.active")).getAttribute("innerText");
      String bookPrice = ChromeWebDriver.getDriver().findElement(By.cssSelector(".l-book-buy-panel-top [data-type='audiobook']")).getAttribute("data-price");
      String bookDescription = ChromeWebDriver.getDriver().findElement(By.cssSelector("#about .description")).getAttribute("innerText");

      book.add(bookLink + ";" + bookName + ";" + bookAuthor + ";" + bookPrice + ";" + bookDescription + ";");

      return book;
    }

    public void createCsvFile() throws IOException {
        fileManager.create();
        fileManager.writeHeader();

    }

    public void writeValuesToCsvFile(ArrayList<String> array) throws IOException {
        fileManager.writeNewLine(array);
    }



}
