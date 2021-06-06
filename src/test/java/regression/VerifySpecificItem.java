package regression;

import com.opencsv.CSVReader;
import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.ehs_page_objects.HomePage;
import page_objects.ehs_page_objects.ApplicationItemPage;
import page_objects.ehs_page_objects.SpecificItemPage;
import testUtils.TestBase;
import java.util.List;


import java.io.IOException;
import java.io.FileReader;


public class VerifySpecificItem extends TestBase {

    final static private String PAGE_TITLE = "EHS - Home";
    final static private String TABLE_HEADER_TITLE = "EHS - All Items";

    String filePath = "data/productsDetails.csv";

    @Parameters({"row"})
    @BeforeTest
    public String[] getItem(int row) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List allItems = reader.readAll();
        String[] rowItems = (String[]) allItems.get(row);
        return rowItems;
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

    @Parameters({"row"})
    @Test(priority = 3)
    public void openSpecificProduct(int row) throws IOException {
        new ApplicationItemPage(driver).clickOpenButton(row);
    }

    @Parameters({"row"})
    @Test(priority = 4)
    public void verifyProductID(int row) throws IOException {
        Assert.assertTrue(new SpecificItemPage(driver).verifyProductContent(getItem(row)));
    }

    @Test(priority = 5)
    public void verifySpecificProductName() throws IOException {
        Assert.assertTrue(new SpecificItemPage(driver).verifyProductName());
    }

    @Test(priority = 6)
    public void verifySpecificProductMaterial() throws IOException {
        Assert.assertTrue(new SpecificItemPage(driver).verifyProductMaterial());
    }

    @Test(priority = 7)
    public void verifySpecificProductManufacturer() throws IOException {
        Assert.assertTrue(new SpecificItemPage(driver).verifyProductManufacturer());
    }

    @Test(priority = 8)
    public void verifyProductPrice() throws IOException {
        Assert.assertTrue(new SpecificItemPage(driver).verifyProductPrice());
    }


}
