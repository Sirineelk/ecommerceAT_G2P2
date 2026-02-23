package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.*;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.sogeti.ecommerce.configuration.DriverFactory;
import static org.junit.Assert.*;

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

    @Then("le logo doit être visible")
    public void le_logo_doit_etre_visible() {
        init();
        assertTrue("Le logo n'est pas visible", basePage.isLogoDisplayed());
    }

    @When("je clique sur le logo")
    public void je_clique_sur_le_logo() {
        init();
        basePage.clickLogo();
    }

    @Then("je suis redirigé vers la page d'accueil")
    public void je_suis_redirige_vers_la_page_accueil() {
        init();
        assertEquals("https://practice.automationtesting.in/", driver.getCurrentUrl());
    }
}

