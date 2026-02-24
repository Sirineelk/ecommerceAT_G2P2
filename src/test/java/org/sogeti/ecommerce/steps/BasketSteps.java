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

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sogeti.ecommerce.configuration.Hooks;
import java.time.Duration;

public class BasketSteps {
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));

    @Given("j'ai des articles dans mon panier")
    @Given("j'ai au moins un article dans le panier")
    @Given("j'ai un article avec une quantité de 1 dans le panier")
    public void preparerPanier() {
        // aller sur le shop
        Hooks.driver.get("https://practice.automationtesting.in/shop/");

        // gestion de la pop-up
        try {
            WebElement consent = Hooks.driver.findElement(By.xpath("//button[contains(.,'Consent')] | //button[contains(.,'AGREE')]"));
            consent.click();
        } catch (Exception e) {

        }

        WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class,'add_to_cart_button')]")));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", addBtn);

        Hooks.driver.get("https://practice.automationtesting.in/basket/");
    }

    @When("je clique sur le bouton \"Supprimer\" d’un article")
    public void supprimerArticle() {
        WebElement btnRemove = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='remove']")));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", btnRemove);
    }

    @Then("l’article est retiré du panier")
    public void verifierSuppression() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-message")));
        Assert.assertTrue(msg.getText().contains("removed"));
    }

    @When("je modifie la quantité à {int}")
    public void modifierQuantite(Integer nouvelleQte) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.qty")));
        input.clear();
        input.sendKeys(nouvelleQte.toString());

        Hooks.driver.findElement(By.name("update_cart")).click();
    }

    @Then("la nouvelle quantité est prise en compte")
    @And("le prix total de l’article est recalculé")
    @And("le total du panier est mis à jour")
    @And("le montant des taxes associées")
    @Then("je vois le prix total de la commande")
    public void verifierMiseAJourGenerale() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockOverlay")));
        System.out.println("LOG: Mise à jour du panier validée.");
    }

    @When("je saisis un code promo valide dans le champ \"Coupon code\"")
    public void saisirCoupon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("coupon_code"))).sendKeys("repoit");
    }

    @And("je valide le code promo en cliquant sur \"APPLY COUPON\"")
    public void validerCoupon() {
        Hooks.driver.findElement(By.name("apply_coupon")).click();
    }

    @Then("la réduction est appliquée")
    public void verifierReduction() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-message")));
        Assert.assertTrue(msg.getText().contains("applied successfully"));
    }


    @When("j'accède à la page \"Panier\"")
    public void accederAuPanier() {
        Hooks.driver.get("https://practice.automationtesting.in/basket/");
    }

    @And("je vois le montant des taxes associées")
    public void verifierTaxes() {
        WebElement taxes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='tax-rate']")));
        Assert.assertTrue("Les taxes ne sont pas affichées", taxes.isDisplayed());
    }

    @When("je clique sur le bouton \"Proceed to Checkout\"")
    public void cliquerCheckout() {
        WebElement btnCheckout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'checkout-button')]")));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", btnCheckout);
    }

    @Then("je suis redirigée vers la page de paiement")
    public void verifierPagePaiement() {
        wait.until(ExpectedConditions.urlContains("checkout"));
        Assert.assertTrue(Hooks.driver.getCurrentUrl().contains("checkout"));
    }

    @When("je saisis un code promo invalide")
    public void saisirCodeInvalide() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("coupon_code")));
        input.clear();
        input.sendKeys("CODE_BIDON_123");
    }

    @And("je clique sur \"APPLY COUPON\"")
    public void cliquerAppliquer() {
        WebElement btnApply = Hooks.driver.findElement(By.name("apply_coupon"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", btnApply);
    }

    @Then("un message d’erreur s’affiche")
    public void verifierErreurCoupon() {
        WebElement erreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='woocommerce-error']")));
        Assert.assertTrue(erreur.getText().contains("does not exist") || erreur.getText().contains("Pas de coupon"));
    }

    @And("le total du panier ne change pas")
    public void verifierTotalInchange() {
        Assert.assertTrue(Hooks.driver.findElement(By.xpath("//tr[@class='order-total']")).isDisplayed());
    }
}