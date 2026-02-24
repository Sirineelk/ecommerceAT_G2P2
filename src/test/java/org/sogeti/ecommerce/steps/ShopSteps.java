package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.configuration.Hooks;
import org.sogeti.ecommerce.pages.BasePage;
import org.sogeti.ecommerce.pages.ShopPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class ShopSteps {

    private ShopPage shopPage;

    @Given("je suis sur la page \"Shop\"")
    public void jeSuisSurLaPageShop(){
        shopPage = new ShopPage(Hooks.driver);
        shopPage.jeSuisSurLaPageShop();
    }

    @And("j'ai ajouté un article au panier")
    public void jaiAjouteUnArticleAuPanier(){
        shopPage.jaiAjouteUnArticleAuPanier();
    }

    @When("je clique sur le lien \"VIEW BASKET\" qui apparaît sous l'article")
    public void jeCliqueSurLeLienViewBasketQuiApparaîtSousLarticle(){
        shopPage.jeCliqueSurLeLienViewBasketQuiApparaîtSousLarticle();
    }

    @Then("je suis redirigé vers la page \"Basket\"")
    public void jeSuisRedirigéVersLaPageBasket(){
        shopPage.jeSuisRedirigéVersLaPageBasket();
    }

    @When("je filtre les articles par theme")
    public void jeFiltreLesArticlesParTheme(){
        shopPage.jeFiltreLesArticlesParTheme();
    }

    @Then("la vue affiche les articles du thème choisi")
    public void laVueAfficheLesArticlesAuThèmeChoisi(){
        shopPage.laVueAfficheLesArticlesAuThèmeChoisi();
    }

    @When("je filtre les articles par prix")
    public void jeFiltreLesArticlesParPrix(){
        shopPage.jeFiltreLesArticlesParPrix();
    }

    @Then("la vue affiche les articles dans l'ordre correspondant")
    public void laVueAfficheLesArticlesDansLordreCorrespondant(){
        shopPage.laVueAfficheLesArticlesDansLordreCorrespondant();
    }

    @Then("une photo descriptive, un libellé et le prix affiché au-dessus")
    public void unePhotoDescriptiveUnLibelléEtLePrixAffichéAuDessus(){
        shopPage.unePhotoDescriptiveUnLibelléEtLePrixAffichéAuDessus();
    }

    @And("un lien actif sur un de ces element redirige vers le détail de l'article")
    public void unLienActifSurUnDeCesElementRedirigeVersLeDétailDeLarticle(){
        shopPage.unLienActifSurUnDeCesElementRedirigeVersLeDétailDeLarticle();
    }

    @When("je clique sur l'icône du panier située en entête")
    public void jeCliqueSurLicôneDuPanierSituéeEnEntête(){
        shopPage.jeCliqueSurLicôneDuPanierSituéeEnEntête();
    }




}
