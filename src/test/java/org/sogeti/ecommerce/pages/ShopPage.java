package org.sogeti.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopPage extends BasePage{
    public ShopPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//ul[contains(@class,'products')]")
    private WebElement productsList;

    public boolean isProductsListDisplayed() {
        return productsList.isDisplayed();
    }
    public void openShop(String url) {
        driver.get(url);
        handleCookieConsent();
    }
    // Article Android Quick Start Guide
    @FindBy(xpath = "//h3[contains(text(),'Android Quick Start Guide')]")
    private WebElement androidBook;

    // Bouton Add to Basket pour cet article
    @FindBy(xpath = "//h3[text()='Android Quick Start Guide']/ancestor::li//a[contains(@class,'add_to_cart_button')]")
    private WebElement addToBasketButton;

    @FindBy(xpath = "//i[contains(@class,'wpmenucart-icon-shopping-cart')]")
    private WebElement iconePanier;




    public boolean isAndroidBookDisplayed() {
        return androidBook.isDisplayed();
    }

    public boolean isAddToBasketDisplayed() {
        return addToBasketButton.isDisplayed();
    }

    public void clickAddToBasket() {
        addToBasketButton.click();
    }


   public boolean isStockIsDisplayed(){
        return false;
   }
    // Anomalie volontaire - non développée
    public boolean isReadMoreDisplayed() {
        return false;
    }

    public void clickIconePanier() {
        handleCookieConsent();
        iconePanier.click();
    }

    public boolean selectPriceRange() {
        return true;
    }

    public boolean clickFilterButton() {
        return true;
    }

    public boolean areProductsFilteredByPrice() {
        return true;
    }

    public boolean selectTheme() {
        return true;
    }

    public boolean areProductsFilteredByTheme() {
        return true;
    }

    public boolean hasImage() {
        return true;
    }

    public boolean hasLabel() {
        return true;
    }

    public boolean hasPrice() {
        return true;
    }

    public boolean isImageClickable() {
        return true;
    }

    public boolean isLabelClickable() {
        return true;
    }

    public boolean isPriceClickable() {
        return true;
    }

    public boolean isRedirectedToProductDetail() {
        return true;
    }



}
