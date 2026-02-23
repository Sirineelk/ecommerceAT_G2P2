package org.sogeti.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Vérifier qu’on est sur la page checkout
    public boolean isOnCheckoutPage() {

        return driver.getCurrentUrl().contains("/checkout");
    }
}