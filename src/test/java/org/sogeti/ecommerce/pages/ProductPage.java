package org.sogeti.ecommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sogeti.ecommerce.configuration.DriverFactory;
import org.sogeti.ecommerce.configuration.Hooks;

public class ProductPage {

    DriverFactory driver = new DriverFactory();
    public ProductPage() {
        PageFactory.initElements(Hooks.driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Consent')] | //button[contains(.,'AGREE')]")
    public WebElement btnConsent;

    @FindBy(xpath = "//img[contains(@class,'attachment-shop_single')]")
    public WebElement imageProduit;

    @FindBy(xpath = "//p[@class='price']")
    public WebElement prixProduit;

    @FindBy(name = "quantity")
    public WebElement champQuantite;

    @FindBy(xpath = "//button[@type='submit' and contains(@class,'single_add_to_cart_button')]")
    public WebElement btnAjouterPanier;

    @FindBy(xpath = "//div[@class='woocommerce-message']")
    public WebElement messageConfirmation;

    // On cible le titre H2 car le contenu du div 'tab-description' est parfois masqué techniquement
    @FindBy(xpath = "//h2[contains(text(),'Description')]")
    public WebElement titreDescription;
}}