package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.*;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.sogeti.ecommerce.configuration.DriverFactory;
import static org.junit.Assert.*;

/**
 * <p>Cette classe contient les étapes de test pour vérifier la visibilité du logo du site e-commerce et la redirection vers la page d'accueil lorsqu'on clique sur le logo. Elle utilise Cucumber pour définir les étapes Given, When et Then, qui correspondent aux actions et vérifications effectuées sur la page. Les méthodes de cette classe interagissent avec la page à travers la classe BasePage
 * qui encapsule les éléments et les actions spécifiques à la page, notamment la vérification de la visibilité du logo et le clic sur le logo.</p>
 * <p></p>
 * <p>Elle comporte les méthodes suivantes :
 * <p>- je_suis_sur_la_page(String page) : ouvre la page spécifiée en paramètre.</p>
 * <p>- le_logo_doit_etre_visible() : vérifie que le logo du site est visible sur la page.</p>
 * <p>- je_clique_sur_le_logo() : simule un clic sur le logo du site.</p>
 * <p>- je_suis_redirige_vers_la_page_accueil() : vérifie que l'utilisateur est redirigé vers la page d'accueil après avoir cliqué sur le logo.
 * <p></p>
 * Chaque méthode utilise des assertions pour vérifier les conditions attendues et lever des exceptions en cas d'échec, assurant ainsi la robustesse des tests.
 * @see BasePage pour les méthodes d'interaction avec la page.
 */
public class LogoSteps {

    private WebDriver driver;
    private BasePage basePage;

    private void init(){
        driver = DriverFactory.getDriver();
        basePage = new BasePage(driver);
    }
/*
    @Given("je suis sur la page d'accueil")
    public void je_suis_sur_la_page_accueil() {
        init();
        String url = new ConfigReader().getProperty("base.url");
        driver.get(url);
    }*/

    /**
     * Implémentation de l'étape Given qui ouvre la page spécifiée en paramètre. Cette méthode utilise un switch pour déterminer l'URL à ouvrir en fonction du nom de la page fourni. Si le nom de la page ne correspond à aucune des options définies, une exception est levée pour indiquer que la page est inconnue.
     * Case "accueil" : ouvre la page d'accueil du site e-commerce.
     * Case "my account" : ouvre la page "My Account" du site e-commerce.
     * Case "produits" : ouvre la page "Shop" du site e-commerce.
     * throws RuntimeException si le nom de la page fourni ne correspond à aucune des options définies.
     * @param page
     */
    @Given("je suis sur la page {string}")
    public void je_suis_sur_la_page(String page) {
        init();
        switch (page.toLowerCase()) {
            case "accueil": driver.get(ConfigReader.getProperty("base.url")); break;
            case "my account": driver.get(ConfigReader.getProperty("my-account.url")); break;
            case "produits": driver.get(ConfigReader.getProperty("shop.url")); break;
            default: throw new RuntimeException("Page inconnue : " + page);
        }
    }

    /**
     * Implémentation de l'étape Then qui vérifie que le logo du site est visible sur la page. Cette méthode utilise une assertion pour vérifier que l'élément du logo est affiché correctement. Si le logo n'est pas visible, une assertion échoue avec un message d'erreur indiquant que le logo n'est pas visible.
     * @throws AssertionError si le logo n'est pas visible.
     */
    @Then("le logo doit être visible")
    public void le_logo_doit_etre_visible() {
        init();
        assertTrue("Le logo n'est pas visible", basePage.isLogoDisplayed());
    }

    /**
     * Implémentation de l'étape When qui simule un clic sur le logo du site. Cette méthode utilise la méthode clickLogo() de la classe BasePage pour effectuer le clic sur l'élément du logo. Après l'exécution de cette étape, l'utilisateur devrait être redirigé vers la page d'accueil du site e-commerce.
     */
    @When("je clique sur le logo")
    public void je_clique_sur_le_logo() {
        init();
        basePage.clickLogo();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est redirigé vers la page d'accueil après avoir cliqué sur le logo. Cette méthode utilise une assertion pour vérifier que l'URL actuelle du navigateur correspond à l'URL de la page d'accueil définie dans le fichier de configuration. Si l'URL actuelle ne correspond pas à celle de la page d'accueil, une assertion échoue avec un message d'erreur indiquant que la redirection a échoué.
     * @throws AssertionError si l'URL actuelle ne correspond pas à celle de la page d'accueil, indiquant que la redirection a échoué.
     */
    @Then("je suis redirigé vers la page d'accueil")
    public void je_suis_redirige_vers_la_page_accueil() {
        init();
        assertEquals("https://practice.automationtesting.in/", driver.getCurrentUrl());
    }
}

