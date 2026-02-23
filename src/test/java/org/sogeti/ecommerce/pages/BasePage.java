package org.sogeti.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void handleCookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // On attend que le bouton "Consent" soit cliquable
            WebElement consentButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(@class,'fc-cta-consent')]")
                    )
            );
            consentButton.click();
            System.out.println("Popup de consentement fermé !");
        } catch (TimeoutException e) {
            // Le popup n'est pas présent, on continue normalement
            System.out.println("Aucun popup de consentement trouvé.");
        }
    }


}