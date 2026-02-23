package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.pages.BasketPage;
import org.sogeti.ecommerce.pages.CheckoutPage;
import org.sogeti.ecommerce.pages.ProductPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class BasketSteps {

    private BasketPage basketPage = new BasketPage(driver);
    private CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Then("je suis redirigé vers la page du panier")
    public void je_suis_redirige_vers_page_panier() {
        Assert.assertTrue(basketPage.isOnBasketPage());
    }

    @And("je vérifie que le panier contient {string} exemplaires de {string}")
    public void je_verifie_le_panier(String quantity, String productName) {

        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
    }
    @And("je vérifie le récapitulatif du panier pour {string} avec quantité {string}, prix unitaire {string} et total {string}")
    public void je_verifie_le_recapitulatif(String productName, String quantity, String unitPrice, String total) {

        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
        Assert.assertTrue(basketPage.isCorrectUnitPrice(unitPrice));
        Assert.assertTrue(basketPage.isCorrectTotalPrice(total));
    }

    @Given("je suis sur la page panier avec {string} exemplaires de {string}")
    public void panier_avec_article(String quantity, String productName) {

        // On suppose que l’article est déjà ajouté avant
        Assert.assertTrue(basketPage.isOnBasketPage());
        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
    }

    @When("je modifie la quantité à {string}")
    public void je_modifie_la_quantite(String newQuantity) {
        basketPage.modifyQuantity(newQuantity);
    }

    @And("je clique sur le bouton Update Basket")
    public void je_clique_update_basket() {
        basketPage.clickUpdateBasket();
    }

    @Then("la quantité affichée devient {string}")
    public void quantite_affichee_devient(String expectedQuantity) {
        Assert.assertTrue(basketPage.isQuantityUpdated(expectedQuantity));
    }

    @Given("je suis sur la page panier avec un article")
    public void panier_avec_un_article() {
        Assert.assertTrue(basketPage.isOnBasketPage());
    }

    @When("je clique sur le bouton supprimer l'article")
    public void je_clique_supprimer_article() {
        basketPage.clickRemoveArticle();
    }

    @Then("un message de suppression est affiché")
    public void message_suppression_affiche() {
        Assert.assertTrue(basketPage.isRemovalMessageDisplayed());
    }

    // ----- RG3 : Vérifier le récapitulatif Basket Totals -----
    @And("je vérifie le récapitulatif Basket Totals avec subtotal {string}, taxe {string} et total {string}")
    public void je_verifie_basket_totals(String expectedSubtotal, String expectedTax, String expectedTotal) {
        Assert.assertEquals(expectedSubtotal, basketPage.getSubtotal());
        Assert.assertEquals(expectedTax, basketPage.getTax());
        Assert.assertEquals(expectedTotal, basketPage.getTotalAmount());
    }

    @When("je clique sur le bouton Proceed to Checkout")
    public void je_clique_proceed_to_checkout() {
        basketPage.clickProceedToCheckout();
    }

    @Then("je suis redirigé vers la page de checkout")
    public void je_suis_redirige_vers_page_checkout() {
        Assert.assertTrue(checkoutPage.isOnCheckoutPage());
    }

}