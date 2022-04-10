package com.oleh.selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.oleh.properties.PropertiesHolder;
import com.oleh.datamodel.TestEnvModel;
import com.oleh.utils.Utilities;
import com.oleh.utils.WaitCondition;
import com.oleh.utils.WebDriverUtilities;

import io.vavr.Predicates;
import lombok.Getter;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Paths;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.anyOf;
import static io.vavr.Predicates.isNull;

public class BaseTest {
    private static final String CONFIG_FILE_NAME_PROPERTY = "configFileName";
    private static final Logger LOG = LogManager.getLogger();
    private static Properties prop = Utilities.readFromPropertyFile("oldProject.properties");
    private static final String BLUEV_VERSION_PROP = "bluev.version";
    static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    @Getter
    protected static TestEnvModel testEnv;

    @BeforeClass
    public static void initTestSuiteParameters() throws ConfigurationException {
//        if (true) {
//            throw new RuntimeException("ghj");
//        }
        System.setProperty("webdriver.chrome.driver", "I:\\Bhasker-ShiroCode\\work\\chromedriver.exe");


        String testConfigFileName = System.getProperty(CONFIG_FILE_NAME_PROPERTY);
        LOG.info("Load config file: " + testConfigFileName);
        prop = Utilities.readFromPropertyFile("oldProject.properties");

//        String baseUrl = PropertiesHolder.projectProperties.baseUrl();
        String baseUrl = "https://www.google.com/";
        Configuration.baseUrl = baseUrl;

//        Configuration.browser="firefox";
//        Configuration.remote="http://test:test-password@ec2-34-231-225-212.compute-1.amazonaws.com:4444/wd/hub";
        LOG.info("Config file initialization is finished.");
    }

    @AfterClass
    public static void writeAllureEnvFile() {
        Properties allureEnv = new Properties();
        allureEnv.setProperty("BLUE Site URL", "Oleh test");
//        allureEnv.setProperty("Browser Name", prop.getProperty("browser.name"));
//        allureEnv.setProperty("Browser Version", prop.getProperty("browser.version"));
//        allureEnv.setProperty("OS Name", System.getProperty("os.name"));
//        allureEnv.setProperty("OS Version", System.getProperty("os.version"));
//        allureEnv.setProperty("BLUE (back) version", prop.getProperty(BLUEV_VERSION_PROP));
        Utilities.writeToPropertyFile(allureEnv, Paths.get(prop.getProperty("allure.resultdir"), "environment" +
                ".properties").toString(), false);
        System.out.println();
    }

    @BeforeMethod
    public void setUpTest() throws Exception {
        setupWebDriver();
    }

    static WebDriver getWebDriver(){
        if (webDriverThreadLocal.get()==null){
            webDriverThreadLocal.set(new  ChromeDriver());
        }
        return webDriverThreadLocal.get();
    }

    /**
     * Set up web driver environment.
     */
    private void setupWebDriver() throws Exception {
//        initializeWebDriver(testEnv);
//        WebDriver webDriver = WebDriverProvider.get();
//        webDriver.manage().deleteAllCookies();
//        LOG.info(String.format("Navigating to %s", testEnv.getAppUrl()));
//        if (prop.getProperty(BROWSER_NAME_PROP) == null || prop.getProperty(BROWSER_VERSION_PROP) == null) {
//            prop.setProperty(BROWSER_NAME_PROP, ((RemoteWebDriver) WebDriverProvider.get()).getCapabilities().getBrowserName());
//            prop.setProperty(BROWSER_VERSION_PROP, ((RemoteWebDriver) WebDriverProvider.get()).getCapabilities().getVersion());
//        }

        prop.setProperty(BLUEV_VERSION_PROP, "1.0");
//        System.setProperty("selenide.browser", "edge");

//        open("/test");
        System.out.println();
    }

public void getDriver(){
        Match(System.getProperty("browser")).of(
                Case($(String::isEmpty),()-> {throw new IllegalStateException();}),
                Case($((String s) ->s.equalsIgnoreCase("Chrome")),()-> new ChromeDriver()),
                Case($(anyOf(isNull())),()->{throw new RuntimeException();})

        );

//    WebDriverUtilities.waitFor(By.cssSelector(""),
//            WaitCondition.visible
////           ExpectedConditions::elementToBeClickable
//    );
}


    protected boolean isHeadlessModeEnabled() {
        return false;
    }

    protected <T> T at(Class<T> pageClass){
        return Selenide.page(pageClass);
    }
}
