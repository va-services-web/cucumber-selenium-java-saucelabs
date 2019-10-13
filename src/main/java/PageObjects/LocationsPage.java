package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LocationsPage {
    private WebDriver driver;
    private String location;

    public LocationsPage(WebDriver driver, String location) {
        this.location = location;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click_HilversumButton() {
        String locator = getLocator();
        driver.findElement(By.xpath(locator)).click();
    }

    private String getLocator(){
        return "//input[contains(@title, \"" + location + "\")]";
    }
}

