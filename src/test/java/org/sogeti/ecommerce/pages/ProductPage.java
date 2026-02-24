package org.sogeti.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductPage(String articleName) {
        String slug = articleName.toLowerCase().replace(" ", "-");
        return driver.getCurrentUrl().equals(
                "https://practice.automationtesting.in/product/" + slug + "/"
        );

    }

    // ========================
    // ELEMENTS
    // ========================

    // Photographie
    @FindBy(xpath = "//div[@class='images']//img")
    private WebElement productImage;

    // Description
    @FindBy(xpath = "//div[@itemprop='description']")
    private WebElement productDescription;

    // Prix
    @FindBy(xpath = "//ins//span[@class='woocommerce-Price-amount amount']")
    private WebElement productPrice;

    // Champ quantité
    @FindBy(xpath = "//div[@class='quantity']//input[@type='number']")
    private WebElement quantityInput;

    // Bouton Add to basket
    @FindBy(xpath = "//button[contains(@class,'single_add_to_cart_button')]")
    private WebElement addToBasketButton;

    // Message de confirmation
    @FindBy(xpath = "//div[contains(@class,'woocommerce-message')]")
    private WebElement successMessage;

    // Bouton View Basket
    @FindBy(xpath = "//a[contains(@class,'wc-forward')]")
    private WebElement viewBasketButton;



    // ========================
    // METHODS
    // ========================

    public boolean isProductImageDisplayed() {
        handleCookieConsent();
        return productImage.isDisplayed();
    }

    public boolean isProductDescriptionDisplayed() {
        return productDescription.isDisplayed();
    }

    public boolean isProductPriceDisplayed() {
        return productPrice.isDisplayed();
    }

    public boolean isProductStockDisplayed() {
        return false ; // Anomalie volontaire - non développée
    }


    // RG2 - Saisir quantité
    public void setQuantity(String quantity) {

        WebElement qty = driver.findElement(By.xpath("//input[@name='quantity']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + quantity + "';", qty);
    }

    // RG2 - Cliquer sur Add to basket
    public void clickAddToBasket() {
        addToBasketButton.click();
    }

    // RG3 - Vérifier message dynamique
    public boolean isProductAddedMessageCorrect(String quantity, String productName) {

        String messageText = successMessage.getText();

        if (Integer.parseInt(quantity) > 1) {
            return messageText.contains(quantity + " ×")
                    && messageText.contains(productName)
                    && messageText.contains("have been added to your basket");
        } else {
            return messageText.contains(productName)
                    && messageText.contains("has been added to your basket");
        }
    }

    public void clickViewBasket() {
        viewBasketButton.click();
    }
}