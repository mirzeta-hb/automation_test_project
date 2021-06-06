package page_objects.ehs_page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;
import java.util.ArrayList;

public class ApplicationItemPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\d*";
    final static private String TABLE_HEADER_XPATH = "/html/body/table/tbody/tr/th";
    final static private String DISPLAYED_ITEMS_XPATH = "/html/body/table/tbody/tr/td[1]";
    final static private String OPEN_BUTTON_XPATH = "html/body/table/tbody/tr/td[2]";

    public ApplicationItemPage(WebDriver driver) {
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = TABLE_HEADER_XPATH)
    private WebElement tableHeader;

    @FindBy(xpath = DISPLAYED_ITEMS_XPATH)
    private List<WebElement> displayedItems;

    @FindBy(xpath = OPEN_BUTTON_XPATH)
    private List<WebElement> openButton;


    public WebElement getTableHeader() {
        return tableHeader;
    }

    public List<WebElement> getDisplayedItems() {
        return displayedItems;
    }

    public List<WebElement> getOpenButton() {
        return openButton;
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 30);

    private void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean verifyTableHeaderText(String title) {
        return getTableHeader().getText().equals(title);
    }

    public Boolean checkListedItemsInTable(String[] secondList) {
        if (getDisplayedItems().size() != secondList.length) {
            return false;
        }

        int index = 0;
        for (WebElement getTableRow : getDisplayedItems()) {
            if (!(getTableRow.getText().equals(secondList[index]))) {
                return false;
            }
            index++;
        }
        return true;

    }

    public SpecificItemPage clickOpenButton(int index) {
        getOpenButton().get(index).click();
        return new SpecificItemPage(getDriver());
    }

}

