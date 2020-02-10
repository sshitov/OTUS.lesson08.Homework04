package MIFTests;

import Helpers.DriverManagers.ChromeWebDriver;
import Helpers.TestSteps.Steps;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;


public class BookInfoCrawler {
    ChromeWebDriver chromeWebDriver = new ChromeWebDriver();
    Steps step = new Steps();

    @BeforeClass
    public static void setupWebDriver() {
        ChromeWebDriver.driverLoad();
    }

    @Before
    public void createWebDriver() {
        chromeWebDriver.create();
    }

    @After
    public void closeWebDriver() {
        ChromeWebDriver.quit();
    }

    @Test
    public void getBookCollection() throws IOException, InterruptedException {

        step.createCsvFile();

        step.openStartPage(step.getBaseUrl() + step.getAudioBook());

        ArrayList<String> bookLinkList = new ArrayList<>(step.getUrlList());

        for (String s : bookLinkList) {

           step.writeToCsvFile(step.collectInfoOnThePage(s));
        }

    }

}
