package org.sogeti.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement basketQuantityInput;

    // Vérifier nom produit
    public boolean isCorrectProduct(String productName) {
        return basketProductName.getText().equals(productName);
    }

    // Vérifier quantité
    public boolean isCorrectQuantity(String quantity) {
        return basketQuantityInput.getAttribute("value").equals(quantity);
    }
}