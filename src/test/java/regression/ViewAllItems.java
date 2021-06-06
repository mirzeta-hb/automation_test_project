package regression;

import com.opencsv.CSVReader;
import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.ehs_page_objects.HomePage;
import page_objects.ehs_page_objects.ApplicationItemPage;
import testUtils.TestBase;
import java.io.IOException;
import java.io.FileReader;


public class ViewAllItems extends TestBase {

    final static private String PAGE_TITLE = "EHS - Home";
    final static private String TABLE_HEADER_TITLE = "EHS - All Items";
    private static final String ITEMS = "items";


    String filePath = "data/listedItems.csv";


    @Parameters()
    @BeforeTest
    public String[] getItems() throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] allItems = reader.readNext();
        return allItems;

    }

    @Test(priority = 0)
    public void verifyHomepage() {
        Assert.assertTrue((new HomePage(driver)).verifyHomepageTitle(PAGE_TITLE));
    }

    @Test(priority = 1)
    public void clickViewItemsButton() {
        new HomePage(driver).clickListAllButton();
    }

    @Test(priority = 2)
    public void verifyDisplayedTableTitle() {
        Assert.assertTrue(new ApplicationItemPage(driver).verifyTableHeaderText(TABLE_HEADER_TITLE));
    }

    @Test(priority = 3)
    public void checkAllItemsInTable() throws IOException {
        Assert.assertTrue(new ApplicationItemPage(driver).checkListedItemsInTable(getItems()));
    }

}
