package Helpers.TestSteps;

import Helpers.DriverManagers.ChromeWebDriver;
import Helpers.FileManager.FileManager;
import Helpers.MIFPageElements.BookPage;
import Helpers.MIFPageElements.MainPage;
import MIFTests.BookInfoCrawler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Steps {

    // Initialize logger
    private static final Logger logger = LogManager.getLogger(BookInfoCrawler.class.getName());
    // Initialize Helpers
    FileManager fileManager = new FileManager();

    // Open page method
    public void openPage(String url) {
                ChromeWebDriver.getDriver().get(url);
    }

    // The method that get all audio book list element and return URL array
    public ArrayList<String> getUrlList() throws InterruptedException {

        // MainPage helper initialize with PageFactory
        MainPage mainPage = PageFactory.initElements(ChromeWebDriver.getDriver(), MainPage.class);

        ArrayList<String> urls = new ArrayList<>();

        while (true){

            List<WebElement> oldElementsState = ChromeWebDriver.getDriver().findElements(mainPage.getBooksOldStateInList());

            // Scroll to last element in List WebElement
            ChromeWebDriver.getAction().moveToElement(mainPage.getLoader()).perform();

           // Wait for the list is load
            Thread.sleep(2000); // This is not an elegant solution, but it is necessary, because without "Thread.sleep",
                                      // the site does not have time to load and freezes. Website Feature that we can't fix.

            List<WebElement> newElementsState = mainPage.getBookList();

            if(newElementsState.size() == oldElementsState.size()){
                logger.debug(newElementsState.size());
                for (WebElement webElement : newElementsState) {
                    String url = mainPage.getBookURL(webElement);
                    urls.add(url);
                }
                break;
            }
        }

        return urls;
    }

    public ArrayList<String> collectInfoOnThePage(String url){
        // BookPage helper initialize with PageFactory
        BookPage bookPage = PageFactory.initElements(ChromeWebDriver.getDriver(), BookPage.class);

        ArrayList<String> book = new ArrayList<>();

        // Open book page
        openPage(url);

        // Collect information from page
        String bookLink = bookPage.getPageUrl();
        String bookName = bookPage.getBookName();
        String bookAuthor = bookPage.getBookAuthor();
        String bookPrice = bookPage.getBookPrice();

        // Add found elements to the array
        book.add(bookLink + ";" + bookName + ";" + bookAuthor + ";" + bookPrice);

        return book;
    }

    public void createCsvFile() throws IOException {
        // Create new csv file
        fileManager.create();
        // Add header to file
        fileManager.writeHeader();
    }

    public void writeValuesToCsvFile(ArrayList<String> array) throws IOException {
        fileManager.writeNewLine(array);
    }
}
