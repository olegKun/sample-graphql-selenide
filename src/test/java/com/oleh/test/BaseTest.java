package com.oleh.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.oleh.properties.PropertiesHolder;
import com.oleh.datamodel.TestEnvModel;
import com.oleh.utils.Utilities;
import lombok.Getter;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Paths;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    private static final String CONFIG_FILE_NAME_PROPERTY = "configFileName";
    private static final Logger LOG = LogManager.getLogger();
    private static Properties prop = Utilities.readFromPropertyFile("oldProject.properties");
    private static final String BLUEV_VERSION_PROP = "bluev.version";

    @Getter
    protected static TestEnvModel testEnv;

    @BeforeClass
    public static void initTestSuiteParameters() throws ConfigurationException {
//        if (true) {
//            throw new RuntimeException("ghj");
//        }
        String testConfigFileName = System.getProperty(CONFIG_FILE_NAME_PROPERTY);
        LOG.info("Load config file: " + testConfigFileName);
        prop = Utilities.readFromPropertyFile("oldProject.properties");

        String baseUrl = PropertiesHolder.projectProperties.baseUrl();
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




    protected boolean isHeadlessModeEnabled() {
        return false;
    }

    protected <T> T at(Class<T> pageClass){
        return Selenide.page(pageClass);
    }
}
