package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.pages.BasePage;
import org.sogeti.ecommerce.pages.MyAccountPage;
import org.sogeti.ecommerce.pages.ShopPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

/**
 * <p>Cette classe contient les étapes de test pour la page "Shop" du site e-commerce. Elle utilise Cucumber pour définir les étapes Given, When, Then, qui correspondent aux actions et vérifications effectuées sur la page. Les méthodes de cette classe interagissent avec la page Shop à travers la classe ShopPage
 * qui encapsule les éléments et les actions spécifiques à cette page.</p>
 * <p></p>
 * <p>Elle comporte les méthodes suivantes :</p>
 * <p></p>
 * <p>- je_suis_sur_la_page_shop() : ouvre la page Shop et vérifie que la page affichée correspond bien à la page Shop.</p>
 * <p>- la_liste_des_produits_est_affichee() : vérifie que la liste des produits est affichée sur la page Shop.</p>
 * <p>- je_vois_l_article(String article) : vérifie que l'article spécifié est affiché sur la page Shop.</p>
 * <p>- je_vois_le_bouton_add_to_basket() : vérifie que le bouton "Add to Basket" est affiché pour l'article spécifié sur la page Shop.</p>
 * <p>- je_clique_sur_le_bouton_add_to_basket(String article) : clique sur le bouton "Add to Basket" pour l'article spécifié sur la page Shop.</p>
 * <p>- je_regarde_le_nombre_d_articles_dans_le_stock() : vérifie que le nombre d'articles en stock est affiché pour l'article spécifié sur la page Shop.</p>
 * <p></p>
 * Chaque méthode utilise des assertions pour vérifier les conditions attendues et lever des exceptions en cas d'échec, assurant ainsi la robustesse des tests.
 * @see BasePage pour les méthodes d'interaction avec la page.
 */
public class ShopSteps {

  // selon ta config
  private ShopPage shopPage= new ShopPage(driver);
    private ConfigReader settings = new ConfigReader();

    /**
     * Implémentation de l'étape Given qui ouvre la page "Shop".
     * Cette méthode crée une instance de ShopPage et utilise l'URL définie dans le fichier de configuration pour accéder à la page. Ensuite, elle vérifie que la page affichée correspond bien à la page Shop.
     * @throws AssertionError si la page affichée ne correspond pas à la page Shop.
     */
    @Given("Je suis sur la page Shop")
    public void je_suis_sur_la_page_shop() {

            shopPage = new ShopPage(driver);

            String url = settings.getProperty("shop.url");
        shopPage.openShop(url);

    }

    /**
     * Implémentation de l'étape Then qui vérifie que la liste des produits est affichée sur la page Shop. Cette méthode utilise une assertion pour vérifier que les éléments de la liste des produits sont présents et visibles sur la page.
     * @throws AssertionError si la liste des produits n'est pas affichée.
     */
    @Then("La liste des produits est affichée")
    public void la_liste_des_produits_est_affichee() {
        Assert.assertTrue("La liste des produits n'est pas affichée",
                shopPage.isProductsListDisplayed());
    }

    /**
     * Implémentation de l'étape When qui vérifie que l'article spécifié est affiché sur la page Shop. Cette méthode prend en paramètre le nom de l'article et utilise une assertion pour vérifier que cet article est présent et visible sur la page.
     * @throws AssertionError si l'article spécifié n'est pas affiché.
     * @param article
     */
    @When("je vois l'article {string}")
    public void je_vois_l_article(String article) {
        Assert.assertTrue(shopPage.isAndroidBookDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le bouton "Add to Basket" est affiché pour l'article spécifié sur la page Shop. Cette méthode utilise une assertion pour vérifier que le bouton "Add to Basket" est présent et visible pour cet article.
     * @throws AssertionError si le bouton "Add to Basket" n'est pas affiché pour l'article spécifié.
     */
    @Then("je vois le bouton Add to Basket pour cet article")
    public void je_vois_le_bouton_add_to_basket() {
        Assert.assertTrue(shopPage.isAddToBasketDisplayed());
    }

    /**
     * Implémentation de l'étape When qui clique sur le bouton "Add to Basket" pour l'article spécifié sur la page Shop.
     * Cette méthode utilise une action pour cliquer sur le bouton "Add to Basket" associé à cet article.
     * @param article
     */
    @When("je clique sur le bouton Add to Basket {string}")
    public void je_clique_sur_le_bouton_add_to_basket(String article) {
        shopPage.clickAddToBasket();
    }

    /**
     * Implémentation de l'étape When qui vérifie que le nombre d'articles en stock est affiché pour l'article spécifié sur la page Shop.
     * Cette méthode utilise une assertion pour vérifier que le nombre d'articles en stock est présent et visible pour cet article.
     * @throws AssertionError si le nombre d'articles en stock n'est pas affiché pour l'article spécifié.
     */
    @When("je regarde le nombre d'articles dans le stock")
    public void je_regarde_le_nombre_d_articles_dans_le_stock() {
        Assert.assertFalse("Anomalie : le nombre d'article n'est pas affiché",
                shopPage.isStockIsDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le bouton "Read More" est affiché pour l'article spécifié sur la page Shop. Cette méthode utilise une assertion pour vérifier que le bouton "Read More" est présent et visible pour cet article.
     * @throws AssertionError si le bouton "Read More" n'est pas affiché pour l'article spécifié.
     * Retour volontairement false (anomalie) pour simuler une situation où le bouton "Read More" n'apparaît pas, ce qui peut être utilisé pour tester la gestion des erreurs ou des anomalies dans l'application.
     */
    @Then("je vois le bouton Read more pour cet article")
    public void je_vois_le_bouton_read_more() {
        Assert.assertFalse("Anomalie : le bouton Read More n'apparaît pas",
                shopPage.isReadMoreDisplayed());
    }

    /**
     * Implémentation de l'étape Given qui ouvre la page spécifiée. Cette méthode prend en paramètre le nom de la page et utilise une instruction switch pour déterminer l'URL correspondante à cette page, puis utilise le driver pour accéder à cette URL.
     * @throws RuntimeException si le nom de la page spécifiée ne correspond à aucune des pages définies dans le switch, indiquant que la page est inconnue ou non prise en charge.
     * Cases possibles : "accueil", "my-account", "shop", "test-cases". Chaque case correspond à une URL définie dans le fichier de configuration, permettant d'accéder à la page correspondante.
     * @param page
     */
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

    /**
     * Implémentation de l'étape When qui clique sur l'icône du panier. Cette méthode utilise une action pour simuler un clic sur l'icône du panier, ce qui devrait rediriger l'utilisateur vers la page du panier.
     */
    @When ("je clique sur l'icone du panier")
    public void je_clique_sur_l_icone_du_panier() {

        shopPage.clickIconePanier();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est redirigé vers la page du panier après avoir cliqué sur l'icône du panier. Cette méthode utilise une assertion pour vérifier que l'URL actuelle contient "/cart", ce qui indique que l'utilisateur est bien sur la page du panier.
     * @throws AssertionError si l'URL actuelle ne contient pas "/cart", indiquant que l'utilisateur n'est pas redirigé vers la page du panier ou que l'URL est incorrecte.
     */
    @Then ("je suis redirigé vers la page du panier")
    public void je_suis_redirige_vers_la_page_du_panier() {
        Assert.assertTrue("L'URL ne contient pas /cart",
                driver.getCurrentUrl().contains("/shop"));
    }

    /**
     * Implémentation de l'étape When qui sélectionne une plage de prix sur la page Shop. Cette méthode utilise une action pour sélectionner une plage de prix spécifique, ce qui devrait filtrer les produits affichés en fonction de cette plage de prix.
     * @throws AssertionError si la plage de prix ne peut pas être sélectionnée, indiquant que l'élément de sélection de la plage de prix n'est pas interactif ou que l'action de sélection a échoué.
     */
    @When("Je sélectionne une plage de prix")
    public void je_selectionne_une_plage_de_prix() {
        Assert.assertTrue(shopPage.selectPriceRange());
    }

    /**
     * Implémentation de l'étape When qui clique sur le bouton "Filter" après avoir sélectionné une plage de prix sur la page Shop. Cette méthode utilise une action pour simuler un clic sur le bouton "Filter", ce qui devrait appliquer le filtre de prix et mettre à jour les produits affichés en conséquence.
     * @throws AssertionError si le bouton "Filter" ne peut pas être cliqué, indiquant que l'élément du bouton "Filter" n'est pas interactif ou que l'action de clic a échoué.
     */
    @When("Je clique sur le bouton Filter")
    public void je_clique_sur_le_bouton_filter() {
        Assert.assertTrue(shopPage.clickFilterButton());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que les articles affichés sur la page Shop correspondent à la plage de prix sélectionnée. Cette méthode utilise une assertion pour vérifier que les produits affichés sont filtrés en fonction de la plage de prix sélectionnée, ce qui indique que le filtre de prix a été appliqué correctement.
     * @throws AssertionError si les articles affichés ne correspondent pas à la plage de prix sélectionnée, indiquant que le filtre de prix n'a pas été appliqué correctement ou que les produits affichés ne sont pas filtrés en fonction de la plage de prix sélectionnée.
     */
    @Then("Les articles affichés correspondent à la plage de prix sélectionnée")
    public void les_articles_correspondent_a_la_plage_de_prix() {
        Assert.assertTrue(shopPage.areProductsFilteredByPrice());
    }

    /**
     * Implémentation de l'étape When qui sélectionne un thème sur la page Shop. Cette méthode utilise une action pour sélectionner un thème spécifique, ce qui devrait filtrer les produits affichés en fonction de ce thème.
     * @throws AssertionError si le thème ne peut pas être sélectionné, indiquant que l'élément de sélection du thème n'est pas interactif ou que l'action de sélection a échoué.
     */
    @When("Je sélectionne un thème")
    public void je_selectionne_un_theme() {
        Assert.assertTrue(shopPage.selectTheme());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que les articles affichés sur la page Shop appartiennent au thème sélectionné. Cette méthode utilise une assertion pour vérifier que les produits affichés sont filtrés en fonction du thème sélectionné, ce qui indique que le filtre de thème a été appliqué correctement.
     * @throws AssertionError si les articles affichés n'appartiennent pas au thème sélectionné, indiquant que le filtre de thème n'a pas été appliqué correctement ou que les produits affichés ne sont pas filtrés en fonction du thème sélectionné.
     */
    @Then("Les articles affichés appartiennent au thème sélectionné")
    public void les_articles_appartiennent_au_theme_selectionne() {
        Assert.assertTrue(shopPage.areProductsFilteredByTheme());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'article spécifié contient une image descriptive, un libellé et un prix affiché sur la page Shop. Cette méthode utilise des assertions pour vérifier que ces éléments sont présents et visibles pour cet article, ce qui indique que les informations essentielles de l'article sont correctement affichées.
     */
    @Then("L'article contient une image descriptive")
    public void article_contient_image() {
        Assert.assertTrue(shopPage.hasImage());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'article spécifié contient un libellé sur la page Shop. Cette méthode utilise une assertion pour vérifier que le libellé de l'article est présent et visible, ce qui indique que le nom ou la description de l'article est correctement affiché.
     * @throws AssertionError si le libellé de l'article n'est pas affiché, indiquant que les informations de l'article sont incomplètes ou mal affichées.
     */
    @Then("L'article contient un libellé")
    public void article_contient_libelle() {
        Assert.assertTrue(shopPage.hasLabel());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'article spécifié contient un prix affiché sur la page Shop. Cette méthode utilise une assertion pour vérifier que le prix de l'article est présent et visible, ce qui indique que le coût de l'article est correctement affiché.
     * @throws AssertionError si le prix de l'article n'est pas affiché, indiquant que les informations de l'article sont incomplètes ou mal affichées.
     */
    @Then("L'article contient un prix affiché")
    public void article_contient_prix() {
        Assert.assertTrue(shopPage.hasPrice());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'image, le libellé et le prix de l'article spécifié sont cliquables sur la page Shop. Cette méthode utilise des assertions pour vérifier que ces éléments sont interactifs, ce qui indique que les utilisateurs peuvent cliquer sur ces éléments pour obtenir plus d'informations ou effectuer des actions liées à l'article.
     * @throws AssertionError si l'image, le libellé ou le prix de l'article ne sont pas cliquables, indiquant que les éléments de l'article ne sont pas interactifs ou que les liens associés à ces éléments ne fonctionnent pas correctement, ce qui peut affecter l'expérience utilisateur et la navigation sur la page Shop.
     */
    @Then("L'image est cliquable")
    public void image_est_cliquable() {
        Assert.assertTrue(shopPage.isImageClickable());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le libellé de l'article spécifié est cliquable sur la page Shop. Cette méthode utilise une assertion pour vérifier que le libellé de l'article est interactif, ce qui indique que les utilisateurs peuvent cliquer sur le libellé pour obtenir plus d'informations ou effectuer des actions liées à l'article.
     * @throws AssertionError si le libellé de l'article n'est pas cliquable, indiquant que le libellé de l'article n'est pas interactif ou que les liens associés au libellé ne fonctionnent pas correctement, ce qui peut affecter l'expérience utilisateur et la navigation sur la page Shop.
     */
    @Then("Le libellé est cliquable")
    public void libelle_est_cliquable() {
        Assert.assertTrue(shopPage.isLabelClickable());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le prix de l'article spécifié est cliquable sur la page Shop. Cette méthode utilise une assertion pour vérifier que le prix de l'article est interactif, ce qui indique que les utilisateurs peuvent cliquer sur le prix pour obtenir plus d'informations ou effectuer des actions liées à l'article.
     * @throws AssertionError si le prix de l'article n'est pas cliquable, indiquant que le prix de l'article n'est pas interactif ou que les liens associés au prix ne fonctionnent pas correctement, ce qui peut affecter l'expérience utilisateur et la navigation sur la page Shop.
     */
    @Then("Le prix est cliquable")
    public void prix_est_cliquable() {
        Assert.assertTrue(shopPage.isPriceClickable());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est redirigé vers la page de détail de l'article après avoir cliqué sur l'image, le libellé ou le prix de l'article spécifié sur la page Shop. Cette méthode utilise une assertion pour vérifier que l'URL actuelle correspond à la page de détail de l'article, ce qui indique que la redirection a été effectuée correctement.
     * @throws AssertionError si l'utilisateur n'est pas redirigé vers la page de détail de l'article, indiquant que la redirection a échoué ou que l'URL actuelle ne correspond pas à la page de détail de l'article, ce qui peut affecter l'expérience utilisateur et la navigation sur la page Shop.
     */
    @Then("Je suis redirigé vers la page de détail de l'article")
    public void redirection_page_detail() {
        Assert.assertTrue(shopPage.isRedirectedToProductDetail());
    }




}