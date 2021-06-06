package page_objects.ehs_page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;
import java.util.ArrayList;

public class SpecificItemPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\d*";
    final static private String TABLE_HEADER_XPATH = "/html/body/table/tbody/tr[1]/th";
    final static private String NAME_FIELD_CSS = "input[id='NameField']";
    final static private String MATERIAL_CSS = "input[id='MaterialField']";
    final static private String MANUFACTURER_CSS = "input[id='ManufacturerField']";
    final static private String RETAIL_PRICE_CSS = "input[id='RetailPriceField']";
    final static private String PRODUCT_ID_XPATH = "//*[@id='ProductId']";


    public SpecificItemPage(WebDriver driver) {
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = TABLE_HEADER_XPATH)
    private WebElement tableHeader;

    @FindBy(css = NAME_FIELD_CSS)
    private WebElement nameField;

    @FindBy(css = MATERIAL_CSS)
    private WebElement material;

    @FindBy(css = MANUFACTURER_CSS)
    private WebElement manufacturer;

    @FindBy(css = RETAIL_PRICE_CSS)
    private WebElement productPrice;

    @FindBy(xpath = PRODUCT_ID_XPATH)
    private WebElement productID;


    public WebElement getTableHeader() {
        return tableHeader;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getMaterial() {
        return material;
    }


    public WebElement getManufacturer() {
        return manufacturer;
    }

    public WebElement getProductPrice() {
        return productPrice;
    }

    public WebElement getProductID() {
        return productID;
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 30);

    private void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public Boolean verifyTableHeaderText(String title) {
        return getTableHeader().getText().equals(title);
    }

    public Boolean verifyProductContent(String[] rowItems) {
        waitForVisibility(getProductID());
        for (int i = 0; i < rowItems.length; i++) {
            if (getProductID().getText().equals(rowItems[i])) {
                return true;
            }
        }
        return false;
    }

    public Boolean verifyProductName() {
        waitForVisibility(getNameField());
        System.out.println(getNameField());
        return getNameField().isDisplayed();
    }

    public Boolean verifyProductMaterial() {
        return getMaterial().isDisplayed();
    }

    public Boolean verifyProductManufacturer() {
        return getManufacturer().isDisplayed();
    }

    public Boolean verifyProductPrice() {
        return getProductPrice().isDisplayed();
    }


}