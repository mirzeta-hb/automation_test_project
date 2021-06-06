package page_objects.ehs_page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;
import java.util.List;

public class HomePage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/d*";
    final static private String EHS_TITLE_XPATH = "/html/body/table/tbody/tr[1]/th";
    final static private String SEARCH_FIELD_CSS= "input[id='ProductIdField']";
    final static private String FIND_BUTTON_CSS = "input[id='FindItemButton']";
    final static private String LIST_ALL_ITEMS_CSS = "input[id='ListAllItemsButton']";

    public HomePage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    @FindBy(xpath = EHS_TITLE_XPATH)
    private WebElement ehsTitle;

    @FindBy(css = SEARCH_FIELD_CSS)
    private WebElement searchField;

    @FindBy(css = FIND_BUTTON_CSS)
    private WebElement findButton;

    @FindBy(css = LIST_ALL_ITEMS_CSS)
    private WebElement listAllItems;

    public WebElement getEhsTitle() {
        return ehsTitle;
    }

    public WebElement getSearchField(){
        return searchField;
    }

    public WebElement getFindButton(){
        return findButton;
    }

    public WebElement getListAllItems() {
        return listAllItems;
    }

    public Boolean verifyHomepageTitle(String title){
        return  getEhsTitle().getText().equals(title);
    }

    public ApplicationItemPage clickListAllButton(){
        waitForElementToBeClickable(getListAllItems());
        getListAllItems().click();
        return new ApplicationItemPage(getDriver());
    }

    public SpecificItemPage searchForProduct(String productID){
           waitForVisibility(getSearchField());
           getSearchField().sendKeys(productID);
           getFindButton().click();
           return new SpecificItemPage(getDriver());
    }

}
