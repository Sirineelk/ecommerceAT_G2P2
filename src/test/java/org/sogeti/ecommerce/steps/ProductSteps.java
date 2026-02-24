package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.pages.BasePage;
import org.sogeti.ecommerce.pages.ProductPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

/**
 * Cette classe contient les étapes de test pour la page "Product" du site e-commerce. Elle utilise Cucumber pour définir les étapes Given, When, Then et And, qui correspondent aux actions et vérifications effectuées sur la page. Les méthodes de cette classe interagissent avec la page Product à travers la classe ProductPage
 * qui encapsule les éléments et les actions spécifiques à cette page.
 * <p></p>
 * <p>Elle comporte les méthodes suivantes :</p>
 * <p></p>
 * <p>- je_suis_sur_l_article(String articleName) : ouvre la page d'un article spécifique en fonction du nom de l'article fourni en paramètre, puis vérifie que la page affichée correspond bien à l'article demandé.</p>
 * <p>- je_verifie_la_presence_de_la_photographie() : vérifie que l'image du produit est affichée correctement sur la page de l'article.</p>
 * <p>- je_verifie_la_presence_du_descriptif() : vérifie que le descriptif du produit est affiché correctement sur la page de l'article.</p>
 * <p>- je_verifie_la_presence_du_prix() : vérifie que le prix du produit est affiché correctement sur la page de l'article.</p>
 * <p>- je_verifie_la_presence_du_stock() : vérifie que le stock du produit est affiché correctement sur la page de l'article.</p>
 * <p>- je_suis_sur_la_page_de_l_article(String articleName) : ouvre la page d'un article spécifique en fonction du nom de l'article fourni en paramètre, puis vérifie que la page affichée correspond bien à l'article demandé.</p>
 * <p>- j_indique_le_nombre_d_exemplaire_voulu(String quantity) : indique le nombre d'exemplaires souhaité pour l'article en utilisant la méthode setQuantity de la classe ProductPage pour définir cette quantité sur la page de l'article.</p>
 * <p>- je_clique_sur_le_bouton_d_ajout_au_panier() : simule un clic sur le bouton d'ajout au panier en utilisant la méthode clickAddToBasket de la classe ProductPage pour simuler un clic sur le bouton d'ajout au panier sur la page de l'article.</p>
 * <p>- le_message_d_ajout_s_affiche(String quantity, String productName) : vérifie que le message d'ajout au panier s'affiche correctement avec la quantité et le nom du produit en utilisant une assertion pour vérifier que le message affiché correspond à ces informations.</p>
 * <p>- je_clique_sur_view_basket() : simule un clic sur le bouton "View Basket" pour accéder au panier après avoir ajouté un produit en utilisant la méthode clickViewBasket de la classe ProductPage pour simuler un clic sur le bouton "View Basket" sur la page de l'article.</p>
 * <p></p>
 * Chaque méthode utilise des assertions pour vérifier les conditions attendues et lever des exceptions en cas d'échec, assurant ainsi la robustesse des tests.
 * @see BasePage pour les méthodes d'interaction avec la page.
 */
public class ProductSteps {

    private ProductPage productPage = new ProductPage(driver);

    /**
     * Implémentation de l'étape Given qui ouvre la page d'un article spécifique.
     * Cette méthode prend en paramètre le nom de l'article, génère le slug correspondant et utilise ce slug pour accéder à la page de l'article. Ensuite, elle vérifie que la page affichée correspond bien à l'article demandé.
     * @throws AssertionError si la page affichée ne correspond pas à l'article demandé.
     * @param articleName le nom de l'article à afficher
     */
    @Given("je suis sur l'article {string}")
    public void je_suis_sur_l_article(String articleName) {

        String slug = articleName.toLowerCase().replace(" ", "-");
        driver.get("https://practice.automationtesting.in/product/" + slug + "/");

        Assert.assertTrue(productPage.isOnProductPage(articleName));
    }

    /**
     * Implémentation de l'étape Then qui vérifie la présence de la photographie de l'article sur la page. Cette méthode utilise une assertion pour vérifier que l'image du produit est affichée correctement.
     * @throws AssertionError si l'image du produit n'est pas affichée.
     */
    @Then("je vérifie la présence de la photographie")
    public void je_verifie_la_presence_de_la_photographie() {
        Assert.assertTrue(productPage.isProductImageDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie la présence du descriptif de l'article sur la page. Cette méthode utilise une assertion pour vérifier que le descriptif du produit est affiché correctement.
     * @throws AssertionError si le descriptif du produit n'est pas affiché.
     */
    @Then("je vérifie la présence du descriptif")
    public void je_verifie_la_presence_du_descriptif() {
        Assert.assertTrue(productPage.isProductDescriptionDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie la présence du prix de l'article sur la page. Cette méthode utilise une assertion pour vérifier que le prix du produit est affiché correctement.
     * @throws AssertionError si le prix du produit n'est pas affiché.
     */
    @Then("je vérifie la présence du prix")
    public void je_verifie_la_presence_du_prix() {
        Assert.assertTrue(productPage.isProductPriceDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie la présence du nombre d'exemplaires disponibles pour l'article sur la page. Cette méthode utilise une assertion pour vérifier que le stock du produit est affiché correctement.
     * @throws AssertionError si le stock du produit n'est pas affiché.
     */
    @Then("je vérifie la présence du nombre d'exemplaires")
    public void je_verifie_la_presence_du_stock() {
        Assert.assertTrue(productPage.isProductStockDisplayed());
    }

    /**
     * Implémentation de l'étape Given qui ouvre la page d'un article spécifique. Cette méthode prend en paramètre le nom de l'article, génère le slug correspondant et utilise ce slug pour accéder à la page de l'article. Ensuite, elle vérifie que la page affichée correspond bien à l'article demandé.
     * @throws AssertionError si la page affichée ne correspond pas à l'article demandé.
     * @param articleName
     */
    @Given("je suis sur la page de l'article {string}")
    public void je_suis_sur_la_page_de_l_article(String articleName) {

        String slug = articleName.toLowerCase().replace(" ", "-");
        driver.get("https://practice.automationtesting.in/product/" + slug + "/");

        Assert.assertTrue(productPage.isOnProductPage(articleName));
    }

    /**
     * Implémentation de l'étape Given qui indique le nombre d'exemplaires souhaité pour l'article.
     * Cette méthode prend en paramètre la quantité souhaitée et utilise la méthode setQuantity de la classe ProductPage pour définir cette quantité sur la page de l'article.
     * @param quantity
     */
    @Given("j'indique le nombre d'exemplaire voulu {string}")
    public void j_indique_le_nombre_d_exemplaire_voulu(String quantity) {
        productPage.setQuantity(quantity);
    }

    /**
     * Implémentation de l'étape When qui clique sur le bouton d'ajout au panier. Cette méthode utilise la méthode clickAddToBasket de la classe ProductPage pour simuler un clic sur le bouton d'ajout au panier sur la page de l'article.
     */
    @When("je clique sur le bouton d'ajout au panier")
    public void je_clique_sur_le_bouton_d_ajout_au_panier() {
        productPage.clickAddToBasket();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le message d'ajout au panier s'affiche correctement avec la quantité et le nom du produit.
     * Cette méthode prend en paramètre la quantité et le nom du produit, puis utilise une assertion pour vérifier que le message affiché correspond à ces informations.
     * @param quantity
     * @param productName
     * @throws AssertionError si le message d'ajout au panier ne correspond pas à la quantité et au nom du produit spécifiés, indiquant que le message n'est pas affiché correctement ou que les informations affichées sont incorrectes.
     */
    @Then("le message d'ajout au panier s'affiche avec {string} et {string}")
    public void le_message_d_ajout_s_affiche(String quantity, String productName) {

        Assert.assertTrue(
                productPage.isProductAddedMessageCorrect(quantity, productName)
        );
    }

    /**
     * Implémentation de l'étape And qui clique sur le bouton "View Basket" pour accéder au panier après avoir ajouté un produit. Cette méthode utilise la méthode clickViewBasket de la classe ProductPage pour simuler un clic sur le bouton "View Basket" sur la page de l'article.
     */
    @And("je clique sur le bouton View Basket")
    public void je_clique_sur_view_basket() {
        productPage.clickViewBasket();
    }



}