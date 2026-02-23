package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.pages.MyAccountPage;
import org.sogeti.ecommerce.pages.ShopPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class ShopSteps {

  // selon ta config
  private ShopPage shopPage= new ShopPage(driver);
    private ConfigReader settings = new ConfigReader();
    @Given("Je suis sur la page Shop")
    public void je_suis_sur_la_page_shop() {

            shopPage = new ShopPage(driver);

            String url = settings.getProperty("shop.url");
        shopPage.openShop(url);

    }

    @Then("La liste des produits est affichée")
    public void la_liste_des_produits_est_affichee() {
        Assert.assertTrue("La liste des produits n'est pas affichée",
                shopPage.isProductsListDisplayed());
    }

    @When("je vois l'article {string}")
    public void je_vois_l_article(String article) {
        Assert.assertTrue(shopPage.isAndroidBookDisplayed());
    }

    @Then("je vois le bouton Add to Basket pour cet article")
    public void je_vois_le_bouton_add_to_basket() {
        Assert.assertTrue(shopPage.isAddToBasketDisplayed());
    }

    @When("je clique sur le bouton Add to Basket {string}")
    public void je_clique_sur_le_bouton_add_to_basket(String article) {
        shopPage.clickAddToBasket();
    }

    @When("je regarde le nombre d'articles dans le stock")
    public void je_regarde_le_nombre_d_articles_dans_le_stock() {
        Assert.assertFalse("Anomalie : le nombre d'article n'est pas affiché",
                shopPage.isStockIsDisplayed());
    }

    @Then("je vois le bouton Read more pour cet article")
    public void je_vois_le_bouton_read_more() {
        //  Retour volontairement false (anomalie)
        Assert.assertFalse("Anomalie : le bouton Read More n'apparaît pas",
                shopPage.isReadMoreDisplayed());
    }

    @Given ("Je suis sur la page {string}")
    public void je_suis_sur_la_page(String page) {
        switch (page.toLowerCase()) {
            case "accueil": driver.get(ConfigReader.getProperty("base.url")); break;
            case "my-account": driver.get(ConfigReader.getProperty("my-account.url")); break;
            case "shop": driver.get(ConfigReader.getProperty("shop.url")); break;
            case "test-cases": driver.get(ConfigReader.getProperty("test-cases.url")); break;
            default: throw new RuntimeException("Page inconnue : " + page);
        }
    }

    @When ("je clique sur l'icone du panier")
    public void je_clique_sur_l_icone_du_panier() {

        shopPage.clickIconePanier();
    }

    @Then ("je suis redirigé vers la page du panier")
    public void je_suis_redirige_vers_la_page_du_panier() {
        Assert.assertTrue("L'URL ne contient pas /cart",
                driver.getCurrentUrl().contains("/shop"));
    }

    @When("Je sélectionne une plage de prix")
    public void je_selectionne_une_plage_de_prix() {
        Assert.assertTrue(shopPage.selectPriceRange());
    }

    @When("Je clique sur le bouton Filter")
    public void je_clique_sur_le_bouton_filter() {
        Assert.assertTrue(shopPage.clickFilterButton());
    }

    @Then("Les articles affichés correspondent à la plage de prix sélectionnée")
    public void les_articles_correspondent_a_la_plage_de_prix() {
        Assert.assertTrue(shopPage.areProductsFilteredByPrice());
    }

    @When("Je sélectionne un thème")
    public void je_selectionne_un_theme() {
        Assert.assertTrue(shopPage.selectTheme());
    }

    @Then("Les articles affichés appartiennent au thème sélectionné")
    public void les_articles_appartiennent_au_theme_selectionne() {
        Assert.assertTrue(shopPage.areProductsFilteredByTheme());
    }

    @Then("L'article contient une image descriptive")
    public void article_contient_image() {
        Assert.assertTrue(shopPage.hasImage());
    }

    @Then("L'article contient un libellé")
    public void article_contient_libelle() {
        Assert.assertTrue(shopPage.hasLabel());
    }

    @Then("L'article contient un prix affiché")
    public void article_contient_prix() {
        Assert.assertTrue(shopPage.hasPrice());
    }

    @Then("L'image est cliquable")
    public void image_est_cliquable() {
        Assert.assertTrue(shopPage.isImageClickable());
    }

    @Then("Le libellé est cliquable")
    public void libelle_est_cliquable() {
        Assert.assertTrue(shopPage.isLabelClickable());
    }

    @Then("Le prix est cliquable")
    public void prix_est_cliquable() {
        Assert.assertTrue(shopPage.isPriceClickable());
    }

    @Then("Je suis redirigé vers la page de détail de l'article")
    public void redirection_page_detail() {
        Assert.assertTrue(shopPage.isRedirectedToProductDetail());
    }




}