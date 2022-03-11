package webdriverprovider;


import com.codeborne.selenide.WebDriverProvider;
import com.oleh.datamodel.TestEnvModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RemoteDriverManager implements WebDriverProvider {

    private static final Logger LOG = LogManager.getLogger();



    public WebDriver createDriver(TestEnvModel env, String testName, boolean headlessMode) throws Exception {
        try {
            RemoteWebDriver webDriver = new RemoteWebDriver(new URL(env.getHubUrl()), getChromeOptions(testName,
                    headlessMode));
            webDriver.setFileDetector(new LocalFileDetector());
            return webDriver;
        } catch (Exception e) {
            LOG.error("Unable to init RemoteWebDriver: " + e.getMessage() + " thread: " + Thread.currentThread()
                    .getId());
            throw e;
        }
    }

    private DesiredCapabilities getChromeOptions(String testName, boolean headlessMode) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("headless", headlessMode);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--no-sandbox", "--disable-dev-shm-usage");
        options.setCapability("name", testName);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        chromePrefs.put("download.default_directory", "/home/selenium/Downloads");
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", false);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("plugins.plugins_disabled", new ArrayList<String>() {
            {
                add("Chrome PDF Viewer");
            }
        });
        options.setExperimentalOption("prefs", chromePrefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setVersion("80.0");
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", false);
//        desiredCapabilities.setCapability("headless", headlessMode);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--no-sandbox", "--disable-dev-shm-usage");
//        options.setCapability("name", testName);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        chromePrefs.put("download.default_directory", "/home/selenium/Downloads");
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", false);
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("plugins.plugins_disabled", new ArrayList<String>() {
            {
                add("Chrome PDF Viewer");
            }
        });
        options.setExperimentalOption("prefs", chromePrefs);
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver remoteWebDriver = null;
        try {
            remoteWebDriver = new RemoteWebDriver(
                    URI.create(
                            "http://a993d321ff246439883fa3ff008e27bb-1065285762.us-east-1.elb.amazonaws.com:4444/wd/hub")
                            .toURL(),
                    desiredCapabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return remoteWebDriver;
    }
}
