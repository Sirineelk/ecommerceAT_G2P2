package org.sogeti.ecommerce.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    private By logoImg = By.cssSelector("#site-logo a img");
    private By logoLink = By.cssSelector("#site-logo a");
    private By cookieButton = By.cssSelector(".fc-cta-consent");

    public void accepteCookiesIfPresent() {
        List<WebElement> buttons = driver.findElements(cookieButton);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
            System.out.println("Popup de consentement fermé !");
        } else {
            System.out.println("Aucun popup de consentement trouvé.");
        }
    }


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoDisplayed(){
        return driver.findElement(logoImg).isDisplayed();
    }

    public void clickLogo(){
        handleCookieConsent();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(logoLink));
        logo.click();
    }

    public void handleCookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement consentButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(@class,'fc-cta-consent')]")
                    )
            );
            consentButton.click();
            System.out.println("Popup de consentement fermé !");
        } catch (TimeoutException e) {
            System.out.println("Aucun popup de consentement trouvé.");
        }
    }

}