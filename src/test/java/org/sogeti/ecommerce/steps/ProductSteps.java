package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.pages.ProductPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class ProductSteps {

    private ProductPage productPage = new ProductPage(driver);

    @Given("je suis sur l'article {string}")
    public void je_suis_sur_l_article(String articleName) {

        String slug = articleName.toLowerCase().replace(" ", "-");
        driver.get("https://practice.automationtesting.in/product/" + slug + "/");

        Assert.assertTrue(productPage.isOnProductPage(articleName));
    }

    @Then("je vérifie la présence de la photographie")
    public void je_verifie_la_presence_de_la_photographie() {
        Assert.assertTrue(productPage.isProductImageDisplayed());
    }

    @Then("je vérifie la présence du descriptif")
    public void je_verifie_la_presence_du_descriptif() {
        Assert.assertTrue(productPage.isProductDescriptionDisplayed());
    }

    @Then("je vérifie la présence du prix")
    public void je_verifie_la_presence_du_prix() {
        Assert.assertTrue(productPage.isProductPriceDisplayed());
    }


    @Then("je vérifie la présence du nombre d'exemplaires")
    public void je_verifie_la_presence_du_stock() {
        boolean stockVisible = productPage.isProductStockDisplayed(); // récupère le vrai élément sur la page
        Assert.assertTrue("Le stock attendu n'est pas affiché", stockVisible);
    }

    @Given("je suis sur la page de l'article {string}")
    public void je_suis_sur_la_page_de_l_article(String articleName) {

        String slug = articleName.toLowerCase().replace(" ", "-");
        driver.get("https://practice.automationtesting.in/product/" + slug + "/");

        Assert.assertTrue(productPage.isOnProductPage(articleName));
    }

    @Given("j'indique le nombre d'exemplaire voulu {string}")
    public void j_indique_le_nombre_d_exemplaire_voulu(String quantity) {
        productPage.setQuantity(quantity);
    }

    @When("je clique sur le bouton d'ajout au panier")
    public void je_clique_sur_le_bouton_d_ajout_au_panier() {
        productPage.clickAddToBasket();
    }

    @Then("le message d'ajout au panier s'affiche avec {string} et {string}")
    public void le_message_d_ajout_s_affiche(String quantity, String productName) {

        Assert.assertTrue(
                productPage.isProductAddedMessageCorrect(quantity, productName)
        );
    }

    @And("je clique sur le bouton View Basket")
    public void je_clique_sur_view_basket() {
        productPage.clickViewBasket();
    }



}