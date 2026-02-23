package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.sogeti.ecommerce.pages.BasketPage;
import org.sogeti.ecommerce.pages.ProductPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class BasketSteps {

    private ProductPage productPage = new ProductPage(driver);
    private BasketPage basketPage = new BasketPage(driver);



 /*   @Then("je suis redirigé vers la page du panier")
    public void je_suis_redirige_vers_page_panier() {
        Assert.assertTrue(basketPage.isOnBasketPage());
    }

    @And("je vérifie que le panier contient {string} exemplaires de {string}")
    public void je_verifie_le_panier(String quantity, String productName) {

        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
    }*/
}