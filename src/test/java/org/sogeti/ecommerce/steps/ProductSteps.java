package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sogeti.ecommerce.configuration.Hooks;
import org.sogeti.ecommerce.pages.ProductPage;
import java.time.Duration;

public class ProductSteps {
    ProductPage productPage = new ProductPage();
    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));

    @Given("je suis sur la page de l'article {string}")
    public void allerPageArticle(String article) {
        Hooks.driver.get("https://practice.automationtesting.in/product/android-quick-start-guide/");

        try {
            new WebDriverWait(Hooks.driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(productPage.btnConsent)).click();
            System.out.println("LOG: Popup de consentement fermée.");
        } catch (Exception e) {
            System.out.println("LOG: Pas de popup de consentement détectée.");
        }
    }

    @Then("je vérifie la présence de la photographie,le descriptif,le prix et le nombre d'exemplaires")
    public void verifierDetails() {
        wait.until(ExpectedConditions.visibilityOf(productPage.imageProduit));
        Assert.assertTrue("Image non visible", productPage.imageProduit.isDisplayed());
        Assert.assertTrue("Prix non visible", productPage.prixProduit.isDisplayed());
        Assert.assertTrue("Champ quantité non visible", productPage.champQuantite.isDisplayed());
        Assert.assertTrue("Titre Description non visible", productPage.titreDescription.isDisplayed());
    }

    @And("j'indique le nombre d'exemplaire voulu")
    public void saisirQuantite() {
        wait.until(ExpectedConditions.visibilityOf(productPage.champQuantite));
        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js.executeScript("arguments[0].value='2';", productPage.champQuantite);
    }

    @When("je clique sur le bouton d'ajout au panier {string}")
    public void cliquerAjouter(String nomBouton) {
        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js.executeScript("arguments[0].click();", productPage.btnAjouterPanier);
    }

    @Then("un message “'libellé' has been added to your basket s'affiche\"")
    public void verifierConfirmation() {
        wait.until(ExpectedConditions.visibilityOf(productPage.messageConfirmation));
        String texte = productPage.messageConfirmation.getText();
        Assert.assertTrue("Message d'ajout non trouvé", texte.contains("added to your basket"));
    }
}