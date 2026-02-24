package org.sogeti.ecommerce.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "CHROME").toUpperCase();

            try {
                switch (browser) {
                    case "CHROME":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        Map<String, Object> prefs = new HashMap<>();
                        prefs.put("profile.default_content_setting_values.notifications", 2);
                        prefs.put("credentials_enable_service", false);
                        prefs.put("profile.password_manager_enabled", false);
                        prefs.put("profile.default_content_settings.popups", 0);
                        chromeOptions.addArguments("--incognito");
                        chromeOptions.setExperimentalOption("prefs", prefs);
                        driver = new RemoteWebDriver(
                                new URL("http://admin:admin@10.188.46.132:4444/wd/hub"),
                                chromeOptions
                        );
                        break;
                    case "FIREFOX":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments("-private");
                        driver = new RemoteWebDriver(
                                new URL("http://admin:admin@10.188.46.132:4444/wd/hub"),
                                firefoxOptions
                        );
                        break;

                    case "EDGE":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        driver = new RemoteWebDriver(
                                new URL("http://admin:admin@10.188.46.132:4444/wd/hub"),
                                edgeOptions
                        );
                        break;

                    default:
                        throw new RuntimeException("Navigateur non supporté: " + browser);
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException("URL Grid invalide", e);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
