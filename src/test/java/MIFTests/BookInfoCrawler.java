package MIFTests;

import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;


public class BookInfoCrawler extends baseTest {

    @BeforeClass
    public static void setupWebDriver() {
        setup();
    }

    @Before
    public void createWebDriver() {
        driverInitialize();
    }

    @After
    public void closeWebDriver() {
        driverClose();
    }

    @Test
    public void getBookCollection() throws IOException, InterruptedException {

        createCsvFile();

        openStartPage(baseUrl + audioBook);

        ArrayList<String> bookLinkList = new ArrayList<>(getUrlList());

        for (String s : bookLinkList) {

           writeToCsvFile(collectInfoOnThePage(s));
        }

    }

}
