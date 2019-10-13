package StepDefinitions;

import PageObjects.LocationsPage;
import PageObjects.StartPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

import WebDriverFactory.WebDriverFactory;
import PageObjects.LoginPage;
import Enums.EnvironmentType;

public class AnimanaSteps {
    private static WebDriver driver;
    WebDriverFactory wdf;
    LoginPage loginPage;
    LocationsPage locationsPage;
    StartPage startPage;
    boolean hasPassed; // Todo ducktape option for SouceLabs !!!

    @Before
    public void Setup() {
        System.out.println("-- Running Setup ");
        wdf = new WebDriverFactory();
        driver = wdf.getDriver();
        hasPassed = true;
    }

    @Given("^Open Page \"([^\"]*)\"$")
    public void openPageUrl(String url) {
        System.out.println("---- Step1");
        loginPage = new LoginPage(driver, url);
        loginPage.open_LoginPage();
    }

    @Given("^Login with username=\"([^\"]*)\" and password=\"([^\"]*)\"$")
    public void login_with_username_and_password(String username, String password) {
        System.out.println("---- Step2");
        loginPage.logIn(username,password);
    }

    @When("^location=\"([^\"]*)\" is selected$")
    public void selected_location(String location) {
        System.out.println("---- Step3");
        locationsPage = new LocationsPage(driver, location);
        locationsPage.click_HilversumButton();
    }

    @Then("^new contact icon is present on the page$")
    public void new_contact_icon_is_present_on_the_page() {
        System.out.println("---- Step4");
        startPage = new StartPage(driver);
        if(!startPage.isUserAddIconPresent()){
            hasPassed = false;
        }
        assertTrue("---- new contact icon is NOT present on the page", startPage.isUserAddIconPresent());
    }

    @When("^selecting patient category from drop down box and searching for \"([^\"]*)\"$")
    public void search_for_Diensthond(String searchTerm) {
        System.out.println("---- Step5");
        startPage.searchPatient(searchTerm);
    }

    @Then("^search should displays only one result$")
    public void search_displays_only_one_result() {
        System.out.println("---- Step6");
        if(!startPage.resultCheck()){
            hasPassed = false;
        }
        assertTrue("---- Diensthond is on the page", startPage.resultCheck());
    }

    @After
    public void Teardown() {
        System.out.println("-- Teardown ");
        if(wdf.environmentType == EnvironmentType.SAUCELABS)
        {
            if (hasPassed){
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
            }
            else {
                ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
            }
        }
        wdf.closeDriver();
        hasPassed = true;
    }
}