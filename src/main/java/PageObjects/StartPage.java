package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StartPage {

    private WebDriver driver;
    private WebElement _popup;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "i.icon-user-add")
    private WebElement _user_add_icon;

    @FindBy(id = "topMenuBar-searchDropdown-button")
    private WebElement _search_dropdown_button;

    @FindBy(how = How.XPATH, using = "//a[@beanname=\"patient\"]")
    private WebElement _search_category;

    @FindBy(id = "headerSearchInput")
    private WebElement _search_term;

    @FindBy(id = "mainSearchButton")
    private WebElement _search_main_button;

    @FindBy(xpath = "//table[@id='findResult-searchResult-table']//td")
    private List<WebElement> _tableCells;

    String searchTerm = null;


    private void click_DropdownButton() {
        _search_dropdown_button.click();
    }

    private void select_Category() {
        _search_category.click();
    }

    private void switch_to_iframe(){
        driver.switchTo().frame("mana");
    }

    private void click_mainButton(){
        _search_main_button.click();
    }

    public void searchPatient(String patient) {
        //save search term to asert later. not the best solution !!!
        searchTerm = patient;

        switch_to_iframe();
        click_DropdownButton();
        select_Category();
        enter_searchTerm(patient);
        click_mainButton();
    }

    public boolean resultCheck(){
        if(_tableCells.size() == 0){
            return false;
        } else {
            for (WebElement tableCell : _tableCells) {
                if(tableCell.getText().trim().toLowerCase().equals(searchTerm.toLowerCase())){
                    return true;
                }
            }
            return false;
        }
    }

    private void enter_searchTerm(String search_term) {
        _search_term.sendKeys(search_term);
    }

    public boolean isUserAddIconPresent(){
        return isWebElementPresent(_user_add_icon);
    }

    private boolean isWebElementPresent(WebElement element) {
        return element != null;
    }


}