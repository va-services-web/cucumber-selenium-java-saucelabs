package WebDriverFactory;

import Enums.DriverType;
import Enums.EnvironmentType;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class WebDriverFactory {
    private WebDriver driver;
    public EnvironmentType environmentType;

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";


    public WebDriverFactory() {
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if(driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case SAUCELABS: driver = createRemoteDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createRemoteDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //set your user name and access key to run tests in Sauce. You can find them in SauceLabs under User Settings
        capabilities.setCapability("username", FileReaderManager.getInstance().getConfigReader().getSauceUsername());
        capabilities.setCapability("accessKey", FileReaderManager.getInstance().getConfigReader().getSauceAccessKey());
        // configure tests to run on specific browser, browser version and platform.
        // See saucelabs documentation https://saucelabs.com/platform/supported-browsers-devices and https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options for help
        capabilities.setCapability("browserName", FileReaderManager.getInstance().getConfigReader().getSauceBrowserName());
        capabilities.setCapability("version", FileReaderManager.getInstance().getConfigReader().getSauceBrowserVersion());
        capabilities.setCapability("platform", FileReaderManager.getInstance().getConfigReader().getSaucePlatformName());

        try {
            driver = new RemoteWebDriver(new URL(FileReaderManager.getInstance().getConfigReader().getSauceRemoteWebDriverURL()), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Exception occured!!!");
            e.printStackTrace();
        }
        return driver;
    }

    private WebDriver createLocalDriver() {
        DriverType driverType = FileReaderManager.getInstance().getConfigReader().getLocalBrowserName();
        switch (driverType) {
            case FIREFOX :
                System.setProperty(FIREFOX_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getFirefoxDriverPath());
                driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            case IE :
                System.setProperty(IE_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getIeDriverPath());
                driver = new InternetExplorerDriver();
                break;
        }
        return driver;
    }

    public void closeDriver() {
        driver.close();
    }
}