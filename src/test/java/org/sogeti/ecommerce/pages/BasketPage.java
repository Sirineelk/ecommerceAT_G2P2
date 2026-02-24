package org.sogeti.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class BasketPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@class='remove']")
    public WebElement boutonSupprimer;
    @FindBy(css = "input[name*='qty']")
    public WebElement champqte;
    @FindBy(name = "update_cart")
    public WebElement boutonMiseAJour;
    @FindBy(id = "coupon_code")
    public WebElement champCoupon;
    @FindBy(name = "apply_coupon")
    public WebElement boutonAppliquerCoupon;
    @FindBy(xpath = "//tr[@class='order-total']//span")
    public WebElement totalFinal;
    @FindBy(xpath = "//tr[@class='tax-rate']//span")
    public WebElement taxes;
    @FindBy(xpath = "//a[contains(@class,'checkout-button')]")
    public WebElement boutonCheckout;
    @FindBy(xpath = "//ul[@class='woocommerce-error']")
    public WebElement messageErreur;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOnBasketPage() {
        return false;
    }

    public boolean isCorrectProduct(String productName) {
        return false;
    }
}