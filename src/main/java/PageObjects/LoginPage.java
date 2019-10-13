package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private String URL;
    private WebDriver driver;

    public LoginPage(WebDriver driver, String URL) {
        this.driver = driver;
        this.URL = URL;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "username")
    private WebElement _username;

    @FindBy(how = How.ID, using = "password")
    private WebElement _password;

    @FindBy(how = How.ID, using = "logon-login-button")
    private WebElement _loginButton;


    private void enter_username(String username) {
        _username.sendKeys(username);
    }

    private void enter_password(String password) {
        _password.sendKeys(password);
    }

    private void click_loginButton() {
        _loginButton.click();
    }

    public void logIn(String username, String password) {
        enter_username(username);
        enter_password(password);
        click_loginButton();
    }

    public void open_LoginPage() {
        driver.get(URL);
    }
}