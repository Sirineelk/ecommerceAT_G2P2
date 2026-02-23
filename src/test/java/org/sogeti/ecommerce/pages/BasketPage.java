package org.sogeti.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    // Vérifier qu’on est sur la page panier
    public boolean isOnBasketPage() {
        return driver.getCurrentUrl().contains("/basket");
    }

    // Ligne produit dans le panier
    @FindBy(xpath = "//td[@class='product-name']/a")
    private WebElement basketProductName;

    // Champ quantité dans le panier
    @FindBy(xpath = "//input[contains(@class,'qty')]")
    public WebElement basketQuantityInput;

    // Prix unitaire
    @FindBy(xpath = "//td[@class='product-price']//span[@class='woocommerce-Price-amount amount']")
    private WebElement unitPrice;

    // Prix total ligne
    @FindBy(xpath = "//td[@class='product-subtotal']//span[@class='woocommerce-Price-amount amount']")
    private WebElement totalPrice;


    // Bouton Remove (X)
    @FindBy(xpath = "//a[@class='remove']")
    private WebElement removeButton;

    // Bouton Update Basket
    @FindBy(xpath = "//input[@name='update_cart']")
    private WebElement updateBasketButton;

    // Message WooCommerce (succès ou suppression)
    @FindBy(xpath = "//div[contains(@class,'woocommerce-message')]")
    private WebElement basketMessage;

    // ----- RG3 : Basket Totals -----
    @FindBy(xpath = "//td[@data-title='Subtotal']/span[contains(@class,'amount')]")
    private WebElement subtotalAmount;

    @FindBy(xpath = "//td[@data-title='Tax']/span[contains(@class,'amount')]")
    private WebElement taxAmount;

    @FindBy(xpath = "//td[@data-title='Total']/strong/span[contains(@class,'amount')]")
    private WebElement totalAmount;

    // ----- RG4 : Proceed to Checkout -----
    @FindBy(xpath = "//a[contains(@class,'checkout-button')]")
    private WebElement proceedToCheckoutButton;

    // Méthodes RG3
    // Méthode pour scroll et retirer popups si nécessaire
    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public String getSubtotal() {
        scrollIntoView(subtotalAmount);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(subtotalAmount));
        return subtotalAmount.getText().replace("₹", "").replace(",", "").trim();
    }

    public String getTax() {
        scrollIntoView(taxAmount);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(taxAmount));
        return taxAmount.getText().replace("₹", "").replace(",", "").trim();
    }

    public String getTotalAmount() {
        scrollIntoView(totalAmount);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(totalAmount));
        return totalAmount.getText().replace("₹", "").replace(",", "").trim();
    }
    // Méthode RG4
    public void clickProceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    // Vérifier nom produit
    public boolean isCorrectProduct(String productName) {
        return basketProductName.getText().equals(productName);
    }

    // Vérifier quantité
    public boolean isCorrectQuantity(String quantity) {
        return basketQuantityInput.getAttribute("value").equals(quantity);
    }

    public boolean isCorrectUnitPrice(String expectedPrice) {
        String priceText = unitPrice.getText().replace("₹", "").replace(",", "").trim();
        return priceText.equals(expectedPrice);
    }

    public boolean isCorrectTotalPrice(String expectedTotal) {
        String totalText = totalPrice.getText().replace("₹", "").replace(",", "").trim();
        return totalText.equals(expectedTotal);
    }

    public void modifyQuantity(String quantity) {

        basketQuantityInput.click();
        basketQuantityInput.clear();
        basketQuantityInput.sendKeys(quantity);
    }

    public void clickUpdateBasket() {
        updateBasketButton.click();
    }

    public boolean isQuantityUpdated(String expectedQuantity) {
        return basketQuantityInput.getAttribute("value").equals(expectedQuantity);
    }

    public void clickRemoveArticle() {
        removeButton.click();
    }
    public boolean isRemovalMessageDisplayed() {
        return basketMessage.isDisplayed();
    }


}