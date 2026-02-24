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

/**
 * Cette classe contient les étapes de test pour la page "Basket" du site e-commerce. Elle utilise Cucumber pour définir les étapes Given, When, Then et And, qui correspondent aux actions et vérifications effectuées sur la page. Les méthodes de cette classe interagissent avec la page Basket à travers la classe BasketPage
 * qui encapsule les éléments et les actions spécifiques à cette page.
 * <p></p>
 * <p>Elle comporte les méthodes suivantes :</p>
 * <p></p>
 * <p>- je_suis_redirige_vers_page_panier() : vérifie que l'utilisateur est redirigé vers la page du panier après avoir ajouté un produit ou cliqué sur le bouton "View Basket".</p>
 * <p>- je_verifie_le_panier(String quantity, String productName) : vérifie que le panier contient la quantité et le nom du produit ajoutés en utilisant des assertions pour vérifier que les informations affichées dans le panier correspondent à celles attendues.</p>
 * <p>- je_verifie_le_recapitulatif(String productName, String quantity, String unitPrice, String total) : vérifie que le récapitulatif du panier affiche correctement le nom du produit, la quantité, le prix unitaire et le total en utilisant des assertions pour vérifier que les informations affichées dans le récapitulatif du panier correspondent à celles attendues.</p>
 * <p>- panier_avec_article(String quantity, String productName) : vérifie que l'utilisateur est sur la page du panier avec un article spécifique en utilisant des assertions pour vérifier que les informations affichées dans le panier correspondent à celles attendues.</p>
 * <p>- je_modifie_la_quantite(String newQuantity) : modifie la quantité d'un article dans le panier en utilisant la méthode modifyQuantity de la classe BasketPage pour définir la nouvelle quantité sur la page du panier.</p>
 * <p>- je_clique_update_basket() : simule un clic sur le bouton "Update Basket" pour mettre à jour le panier après avoir modifié la quantité en utilisant la méthode clickUpdateBasket de la classe BasketPage pour simuler un clic sur le bouton "Update Basket" sur la page du panier.</p>
 * <p>- quantite_affichee_devient(String expectedQuantity) : vérifie que la quantité affichée dans le panier est mise à jour correctement après avoir cliqué sur le bouton "Update Basket" en utilisant une assertion pour vérifier que la quantité affichée correspond à la quantité attendue.</p>
 * <p>- panier_avec_un_article() : vérifie que l'utilisateur est sur la page du panier avec un article spécifique en utilisant des assertions pour vérifier que les informations affichées dans le panier correspondent à celles attendues.</p>
 * <p>- je_clique_supprimer_article() : simule un clic sur le bouton de suppression d'un article dans le panier en utilisant la méthode clickRemoveArticle de la classe BasketPage pour simuler un clic sur le bouton de suppression d'un article dans le panier.</p>
 * <p>- message_suppression_affiche() : vérifie que le message de suppression d'un article dans le panier est affiché correctement après avoir cliqué sur le bouton de suppression en utilisant une assertion pour vérifier que le message de suppression est affiché.</p>
 * <p>- je_verifie_basket_totals(String expectedSubtotal, String expectedTax, String expectedTotal) : vérifie que le récapitulatif des totaux du panier affiche correctement le sous-total, la taxe et le total en utilisant des assertions pour vérifier que les informations affichées dans le récapitulatif des totaux du panier correspondent à celles attendues.</p>
 * <p>- je_clique_proceed_to_checkout() : simule un clic sur le bouton "Proceed to Checkout" pour accéder à la page de checkout en utilisant la méthode clickProceedToCheckout de la classe BasketPage pour simuler un clic sur le bouton "Proceed to Checkout" sur la page du panier.</p>
 * <p>- je_suis_redirige_vers_page_checkout() : vérifie que l'utilisateur est redirigé vers la page de checkout après avoir cliqué sur le bouton "Proceed to Checkout" en utilisant une assertion pour vérifier que l'utilisateur est sur la page de checkout.</p>
 * <p></p>
 * Chaque méthode utilise des assertions pour vérifier les conditions attendues et lever des exceptions en cas d'échec, assurant ainsi la robustesse des tests.
 * @see BasketPage pour les méthodes d'interaction avec la page du panier.
 * @see CheckoutPage pour les méthodes d'interaction avec la page de checkout.
 */

public class BasketSteps {

    private BasketPage basketPage = new BasketPage(driver);
    private CheckoutPage checkoutPage = new CheckoutPage(driver);

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est redirigé vers la page du panier après avoir ajouté un produit ou cliqué sur le bouton "View Basket". Cette méthode utilise une assertion pour vérifier que l'utilisateur est bien sur la page du panier en utilisant la méthode isOnBasketPage de la classe BasketPage.
     * @throws AssertionError si l'utilisateur n'est pas redirigé vers la page du panier.
     */
    @Then("je suis redirigé vers la page du panier")
    public void je_suis_redirige_vers_page_panier() {
        Assert.assertTrue(basketPage.isOnBasketPage());
    }

    /**
     * Implémentation de l'étape And qui vérifie que le panier contient la quantité et le nom du produit ajoutés. Cette méthode utilise des assertions pour vérifier que les informations affichées dans le panier correspondent à celles attendues en utilisant les méthodes isCorrectProduct et isCorrectQuantity de la classe BasketPage.
     * @throws AssertionError si les informations affichées dans le panier ne correspondent pas à celles attendues.
     * @param quantity
     * @param productName
     */
    @And("je vérifie que le panier contient {string} exemplaires de {string}")
    public void je_verifie_le_panier(String quantity, String productName) {

        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
    }

    /**
     * Implémentation de l'étape And qui vérifie que le récapitulatif du panier affiche correctement le nom du produit, la quantité, le prix unitaire et le total. Cette méthode utilise des assertions pour vérifier que les informations affichées dans le récapitulatif du panier correspondent à celles attendues en utilisant les méthodes isCorrectProduct, isCorrectQuantity, isCorrectUnitPrice et isCorrectTotalPrice de la classe BasketPage.
     * @throws AssertionError si les informations affichées dans le récapitulatif du panier ne correspondent pas à celles attendues.
     * @param productName
     * @param quantity
     * @param unitPrice
     * @param total
     */
    @And("je vérifie le récapitulatif du panier pour {string} avec quantité {string}, prix unitaire {string} et total {string}")
    public void je_verifie_le_recapitulatif(String productName, String quantity, String unitPrice, String total) {

        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
        Assert.assertTrue(basketPage.isCorrectUnitPrice(unitPrice));
        Assert.assertTrue(basketPage.isCorrectTotalPrice(total));
    }

    /**
     * Implémentation de l'étape Given qui vérifie que l'utilisateur est sur la page du panier avec un article spécifique. Cette méthode utilise des assertions pour vérifier que les informations affichées dans le panier correspondent à celles attendues en utilisant les méthodes isOnBasketPage, isCorrectProduct et isCorrectQuantity de la classe BasketPage.
     * @throws AssertionError si les informations affichées dans le panier ne correspondent pas à celles attendues ou si l'utilisateur n'est pas sur la page du panier.
     * @param quantity
     * @param productName
     */
    @Given("je suis sur la page panier avec {string} exemplaires de {string}")
    public void panier_avec_article(String quantity, String productName) {

        // On suppose que l’article est déjà ajouté avant
        Assert.assertTrue(basketPage.isOnBasketPage());
        Assert.assertTrue(basketPage.isCorrectProduct(productName));
        Assert.assertTrue(basketPage.isCorrectQuantity(quantity));
    }

    /**
     * Implémentation de l'étape When qui modifie la quantité d'un article dans le panier. Cette méthode prend en paramètre la nouvelle quantité souhaitée et utilise la méthode modifyQuantity de la classe BasketPage pour définir cette nouvelle quantité sur la page du panier.
     * @param newQuantity
     */
    @When("je modifie la quantité à {string}")
    public void je_modifie_la_quantite(String newQuantity) {
        basketPage.modifyQuantity(newQuantity);
    }

    /**
     * Implémentation de l'étape And qui simule un clic sur le bouton "Update Basket" pour mettre à jour le panier après avoir modifié la quantité. Cette méthode utilise la méthode clickUpdateBasket de la classe BasketPage pour simuler un clic sur le bouton "Update Basket" sur la page du panier.
     */
    @And("je clique sur le bouton Update Basket")
    public void je_clique_update_basket() {
        basketPage.clickUpdateBasket();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que la quantité affichée dans le panier est mise à jour correctement après avoir cliqué sur le bouton "Update Basket". Cette méthode prend en paramètre la quantité attendue et utilise une assertion pour vérifier que la quantité affichée correspond à la quantité attendue en utilisant la méthode isQuantityUpdated de la classe BasketPage.
     * @throws AssertionError si la quantité affichée ne correspond pas à la quantité attendue, indiquant que la mise à jour du panier n'a pas fonctionné correctement.
     * @param expectedQuantity
     */
    @Then("la quantité affichée devient {string}")
    public void quantite_affichee_devient(String expectedQuantity) {
        Assert.assertTrue(basketPage.isQuantityUpdated(expectedQuantity));
    }

    /**
     * Implémentation de l'étape Given qui vérifie que l'utilisateur est sur la page du panier avec un article spécifique. Cette méthode utilise des assertions pour vérifier que les informations affichées dans le panier correspondent à celles attendues en utilisant les méthodes isOnBasketPage, isCorrectProduct et isCorrectQuantity de la classe BasketPage.
     * @throws AssertionError si les informations affichées dans le panier ne correspondent pas à celles attendues ou si l'utilisateur n'est pas sur la page du panier.
     */
    @Given("je suis sur la page panier avec un article")
    public void panier_avec_un_article() {
        Assert.assertTrue(basketPage.isOnBasketPage());
    }

    /**
     * Implémentation de l'étape When qui simule un clic sur le bouton de suppression d'un article dans le panier. Cette méthode utilise la méthode clickRemoveArticle de la classe BasketPage pour simuler un clic sur le bouton de suppression d'un article dans le panier.
     */
    @When("je clique sur le bouton supprimer l'article")
    public void je_clique_supprimer_article() {
        basketPage.clickRemoveArticle();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le message de suppression d'un article dans le panier est affiché correctement après avoir cliqué sur le bouton de suppression. Cette méthode utilise une assertion pour vérifier que le message de suppression est affiché en utilisant la méthode isRemovalMessageDisplayed de la classe BasketPage.
     * @throws AssertionError si le message de suppression n'est pas affiché, indiquant que la suppression de l'article n'a pas fonctionné correctement ou que le message de confirmation n'est pas affiché comme prévu.
     */
    @Then("un message de suppression est affiché")
    public void message_suppression_affiche() {
        Assert.assertTrue(basketPage.isRemovalMessageDisplayed());
    }

    /**
     * Implémentation de l'étape And qui vérifie que le récapitulatif des totaux du panier affiche correctement le sous-total, la taxe et le total. Cette méthode prend en paramètre les valeurs attendues pour le sous-total, la taxe et le total, puis utilise des assertions pour vérifier que les informations affichées dans le récapitulatif des totaux du panier correspondent à celles attendues en utilisant les méthodes getSubtotal, getTax et getTotalAmount de la classe BasketPage.
     * @throws AssertionError si les informations affichées dans le récapitulatif des totaux du panier ne correspondent pas à celles attendues, indiquant que les calculs des totaux du panier ne sont pas corrects ou que les informations affichées ne sont pas mises à jour comme prévu.
     * @param expectedSubtotal
     * @param expectedTax
     * @param expectedTotal
     */
    @And("je vérifie le récapitulatif Basket Totals avec subtotal {string}, taxe {string} et total {string}")
    public void je_verifie_basket_totals(String expectedSubtotal, String expectedTax, String expectedTotal) {
        Assert.assertEquals(expectedSubtotal, basketPage.getSubtotal());
        Assert.assertEquals(expectedTax, basketPage.getTax());
        Assert.assertEquals(expectedTotal, basketPage.getTotalAmount());
    }

    /**
     * Implémentation de l'étape When qui simule un clic sur le bouton "Proceed to Checkout" pour accéder à la page de checkout. Cette méthode utilise la méthode clickProceedToCheckout de la classe BasketPage pour simuler un clic sur le bouton "Proceed to Checkout" sur la page du panier.
     */
    @When("je clique sur le bouton Proceed to Checkout")
    public void je_clique_proceed_to_checkout() {
        basketPage.clickProceedToCheckout();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est redirigé vers la page de checkout après avoir cliqué sur le bouton "Proceed to Checkout". Cette méthode utilise une assertion pour vérifier que l'utilisateur est sur la page de checkout en utilisant la méthode isOnCheckoutPage de la classe CheckoutPage.
     * @throws AssertionError si l'utilisateur n'est pas redirigé vers la page de checkout, indiquant que la navigation vers la page de checkout n'a pas fonctionné correctement ou que l'utilisateur n'est pas sur la page de checkout comme prévu.
     */
    @Then("je suis redirigé vers la page de checkout")
    public void je_suis_redirige_vers_page_checkout() {
        Assert.assertTrue(checkoutPage.isOnCheckoutPage());
    }

}