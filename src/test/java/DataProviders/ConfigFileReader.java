package DataProviders;

import Enums.DriverType;
import Enums.EnvironmentType;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;

    public ConfigFileReader() {
        properties = new Properties();
        String propertyFilePath = "Configs/Configuration.properties";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(propertyFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnvironmentType getEnvironment() {
        String environment = properties.getProperty("environment");
        if(environment == null || environment.equalsIgnoreCase("local")){
            return EnvironmentType.LOCAL;
        }
        else if(environment.equals("saucelabs")) {
            return EnvironmentType.SAUCELABS;
        }
        else throw new RuntimeException("'environment' not specified correctly in Configuration.properties. Expected [local|saucelabs] and got " + environment);
    }


    public String getChromeDriverPath(){
        String chromeDriverPath = properties.getProperty("chromeDriverPath");
        if(chromeDriverPath!= null) {
            return chromeDriverPath;
        }else {
            throw new RuntimeException("'chromeDriverPath' not specified in the Configuration.properties file.");
        }
    }


    public String getFirefoxDriverPath(){
        String firefoxDriverPath = properties.getProperty("firefoxDriverPath");
        if(firefoxDriverPath!= null) {
            return firefoxDriverPath;
        }else {
            throw new RuntimeException("'firefoxDriverPath' not specified in the Configuration.properties file.");
        }
    }

    public String getIeDriverPath(){
        String ieDriverPath = properties.getProperty("ieDriverPath");
        if(ieDriverPath!= null) {
            return ieDriverPath;
        }else {
            throw new RuntimeException("'ieDriverPath' not specified in the Configuration.properties file.");
        }
    }

    private DriverType getDriverType(String browserName){
        if(browserName == null || browserName.equals("firefox")){
            return DriverType.FIREFOX;
        }
        else if(browserName.equalsIgnoreCase("chrome")){
            return DriverType.CHROME;
        }
        else if(browserName.equals("ie")) {
            return DriverType.IE;
        }
        else throw new RuntimeException("'browser name' has an invalid value in Configuration.properties. Expected [chrome|firefox|ie]");
    }

    public DriverType getLocalBrowserName(){
        return getDriverType(properties.getProperty("localBrowserName"));
    }

    public DriverType getSauceBrowserName(){
        return getDriverType(properties.getProperty("sauceBrowserName"));
    }

    public Integer getImplicitlyWait(){
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait!= null) {
            return Integer.parseInt(implicitlyWait);
        }else {
            System.out.println("'implicitlyWait' not specified in the Configuration.properties file. Will be set to automatically to 10 seconds");
            return 10;
        }
    }

    public String getURL(){
        String url = properties.getProperty("url");
        if(url!= null) {
            return url;
        }else {
            throw new RuntimeException("'url' not specified in the Configuration.properties file.");
        }
    }

    public String getSauceUsername(){
        String sauceUsername = properties.getProperty("sauceUsername");
        if(sauceUsername!= null) {
            return sauceUsername;
        }else {
            throw new RuntimeException("'sauceUsername' not specified in the Configuration.properties file.");
        }
    }

    public String getSauceAccessKey(){
        String sauceAccessKey = properties.getProperty("sauceAccessKey");
        if(sauceAccessKey!= null) {
            return sauceAccessKey;
        }else {
            throw new RuntimeException("'sauceAccessKey' not specified in the Configuration.properties file.");
        }
    }

    public String getSauceRemoteWebDriverURL(){
        String sauceRemoteWebDriverURL = properties.getProperty("sauceRemoteWebDriverURL");
        if(sauceRemoteWebDriverURL!= null) {
            return sauceRemoteWebDriverURL;
        }else {
            throw new RuntimeException("'sauceRemoteWebDriverURL' not specified in the Configuration.properties file.");
        }
    }

    public Integer getSauceBrowserVersion(){
        String sauceBrowserVersion = properties.getProperty("sauceBrowserVersion");
        if(sauceBrowserVersion!= null) {
            return Integer.parseInt(sauceBrowserVersion);
        }else {
            throw new RuntimeException("'sauceBrowserVersion' not specified in the Configuration.properties file.");
        }
    }

    public String getSaucePlatformName(){
        String saucePlatformName = properties.getProperty("saucePlatformName");
        if(saucePlatformName!= null) {
            return saucePlatformName;
        }else {
            throw new RuntimeException("'saucePlatformName' not specified in the Configuration.properties file.");
        }
    }
}