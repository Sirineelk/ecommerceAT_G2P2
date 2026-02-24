package org.sogeti.ecommerce.utils;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class BaseTools {

    public static final Logger log = LoggerFactory.getLogger(BaseTools.class);

    protected static final int TIME = 100;


    public File screenshotFile;
    private WebDriverWait wait;


    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public void waitAndClick(WebElement e) {
        wait.until(ExpectedConditions.elementToBeClickable(e)).click();
    }

    public void waitDisplayElement(WebElement e) {
        wait.until(ExpectedConditions.visibilityOf(e)).isDisplayed();
    }

    public static void waitEnableElementAndClick(WebDriver driver, WebElement e) {
        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(50))
                        .pollingEvery(Duration.ofMillis(1))
                        .ignoring(ElementNotInteractableException.class)
                        .ignoring(ElementClickInterceptedException.class);

        wait.until(ExpectedConditions.elementToBeClickable(e)).isEnabled();
        e.click();
    }

    public static void clearAndFill(WebElement e, String value) {
        e.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        e.sendKeys(value);
    }
}
