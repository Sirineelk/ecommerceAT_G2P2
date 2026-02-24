package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.pages.MyAccountPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

/**
 *  Cette classe contient les étapes de test pour la page "My Account" du site e-commerce. Elle utilise Cucumber pour définir les étapes Given, When, Then et And, qui correspondent aux actions et vérifications effectuées sur la page. Les méthodes de cette classe interagissent avec la page My Account à travers la classe MyAccountPage
 *  qui encapsule les éléments et les actions spécifiques à cette page.
 *  <p></p>
 *  <p>Elle comporte les méthodes suivantes :</p>
 *  <p></p>
 *  <p>- openPage() : ouvre la page "My Account" en utilisant l'URL définie dans le fichier de configuration.</p>
 *  <p>- enterUsername(String username) : saisit un login dans le champ de connexion.</p>
 *  <p>- enterPassword(String password) : saisit un mot de passe dans le champ de connexion.</p>
 *  <p>- clickLoginButton() : clique sur le bouton de connexion pour tenter de se connecter à la page My Account.</p>
 *  <p>- checkDashboardLinks() : vérifie que l'utilisateur est connecté et que les liens du tableau de bord sont affichés.</p>
 *  <p>- checkLoginError() : vérifie que l'utilisateur n'est pas connecté et que le message d'erreur indiquant que les identifiants sont incorrects est affiché.</p>
 *  <p>- leaveLoginAndPasswordEmpty() : laisse les champs de login et de mot de passe vides et tente de se connecter.</p>
 *  <p>- checkRequiredFields() : vérifie que l'utilisateur n'est pas connecté et que les messages indiquant que les champs sont obligatoires sont affichés.</p>
 *  <p>- checkWrongUsername(String username) : vérifie que l'utilisateur n'est pas connecté et que le message d'erreur indiquant que le nom d'utilisateur est incorrect est affiché.</p>
 *  <p>- checkRememberMe() : coche la case "Remember Me" avant de tenter de se connecter.</p>
 *  <p>- clickLogout() : clique sur le lien de déconnexion pour se déconnecter de la page My Account.</p>
 *  <p>- checkLogout() : vérifie que l'utilisateur est déconnecté et que les pavés de connexion et d'inscription sont affichés sur la page My Account.</p>
 *  <p>- checkCredentialsPreFilled() : vérifie que les identifiants de connexion sont préremplis dans les champs de connexion après avoir coché la case "Remember Me" et s'être connecté avec succès.</p>
 *  <p>- clickOnAccountDetails() : clique sur le lien "Account Details" pour accéder à la page des détails du compte.</p>
 *  <p></p>
 *  Chaque méthode utilise des assertions pour vérifier les conditions attendues et lever des exceptions en cas d'échec, assurant ainsi la robustesse des tests.
 *  @see MyAccountPage pour les méthodes d'interaction avec la page My Account.
 */
public class MyAccountSteps {

    private MyAccountPage myAccountPage;
    private ConfigReader settings = new ConfigReader();

    /**
     * Implémentation de l'étape Given qui ouvre la page "My Account".
     * Cette méthode crée une instance de MyAccountPage
     * et utilise l'URL définie dans le fichier de configuration pour accéder à la page.
     */
    @Given("je suis sur la page My Account")
    public void openPage() {

        myAccountPage = new MyAccountPage(driver); // 👈 ici

        String url = settings.getProperty("my-account.url");
        myAccountPage.open(url);
    }

    /**
     * Implémentation de l'étape When
     * qui saisit un login dans le champ de connexion.
     * @param username
     */
    @When("Je saisis un login {string}")
    public void enterUsername(String username){
        myAccountPage.enterUsername(username);
    }

    /**
     * Implémentation de l'étape When
     * qui saisit un mot de passe dans le champ de connexion.
     * @param password
     */
    @When("Je saisis mon mot de passe {string}")
    public void enterPassword(String password){
        myAccountPage.enterPassword(password);
    }

    /**
     * Implémentation de l'étape When
     * qui clique sur le bouton de connexion pour tenter de se connecter à la page My Account.
     */
    @When("Je clique sur le bouton login")
    public void clickLoginButton(){
        myAccountPage.clickLogin();
    }


    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est connecté et que les liens du tableau de bord sont affichés.
     * Cette méthode utilise une assertion pour vérifier que les liens du tableau de bord (DashboardOrders, Download, Adresses, Account Details, Logout) sont visibles sur la page après une connexion réussie.
     * @throws AssertionError si les liens du tableau de bord ne sont pas affichés, indiquant que la connexion a échoué ou que la page n'est pas correctement chargée.
     */
    @Then("je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout")
    public void checkDashboardLinks(){
        Assert.assertTrue(myAccountPage.isDashboardLinksDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur n'est pas connecté et que le message d'erreur indiquant que les identifiants sont incorrects est affiché.
     * Cette méthode récupère le message d'erreur affiché sur la page après une tentative de connexion échouée
     * et utilise une assertion pour vérifier que ce message contient des indications sur des identifiants incorrects, que ce soit en anglais ("The password you entered") ou en français ("identifiants incorrects"). Si le message d'erreur ne contient pas ces indications, l'assertion échouera, indiquant que le comportement de la page n'est pas conforme aux attentes en cas de connexion avec des identifiants incorrects.
     * @throws AssertionError si le message d'erreur ne contient pas les indications sur des identifiants incorrects, indiquant que la page ne gère pas correctement les tentatives de connexion échouées.
     */
    @Then("Je ne suis pas connecté et je vois un message d'erreur indiquant que les identifiants sont incorrects")
    public void checkLoginError() {
        String errorText = myAccountPage.getLoginErrorMessage();
        Assert.assertTrue(errorText.contains("The password you entered") || errorText.contains("identifiants incorrects"));
    }

    /**
     * Implémentation de l'étape When qui laisse les champs de login et de mot de passe vides et tente de se connecter.
     */
    @When("Je laisse le login et le mot de passe vides")
    public void leaveLoginAndPasswordEmpty() {
        myAccountPage.loginWithEmptyFields();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur n'est pas connecté et que les messages indiquant que les champs sont obligatoires sont affichés.
     * Cette méthode utilise une assertion pour vérifier que les messages d'erreur indiquant que les champs de login et de mot de passe sont obligatoires
     * et sont visibles sur la page après une tentative de connexion avec des champs vides.
     * Si les messages d'erreur ne sont pas affichés, l'assertion échouera, indiquant que la page ne gère pas correctement les tentatives de connexion avec des champs vides.
     * @throws AssertionError si les messages d'erreur indiquant que les champs sont obligatoires ne sont pas affichés, indiquant que la page ne gère pas correctement les tentatives de connexion avec des champs vides.
     */
    @Then("Je ne suis pas connecté et je vois des messages indiquant que les champs sont obligatoires")
    public void checkRequiredFields() {
        Assert.assertTrue(myAccountPage.isUsernameRequiredMessageDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur n'est pas connecté et que le message d'erreur indiquant que le nom d'utilisateur est incorrect est affiché.
     * Cette méthode utilise une assertion pour vérifier que le message d'erreur indiquant que le nom d'utilisateur est incorrect est visible sur la page après une tentative de connexion avec un nom d'utilisateur incorrect.
     * Si le message d'erreur n'est pas affiché, l'assertion échouera, indiquant que la page ne gère pas correctement les tentatives de connexion avec un nom d'utilisateur incorrect.
     * @param username
     */
    @Then("Je ne suis pas connecté et je vois un message d'erreur indiquant que {string} est incorrect")
    public void checkWrongUsername(String username) {
        Assert.assertTrue(myAccountPage.isUsernameNotRegisteredMessageDisplayed());
    }

    /**
     * Implémentation de l'étape And qui coche la case "Remember Me" avant de tenter de se connecter.
     */
    @And("je coche Remember ME")
    public void checkRememberMe() {
        myAccountPage.checkRememberMe();
    }

    /**
     * Implémentation de l'étape When qui clique sur le lien de déconnexion pour se déconnecter de la page My Account.
     */
    @When("je clique sur le lien Logout")
    public void clickLogout() {
        myAccountPage.logout();
    }

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est déconnecté et que les pavés de connexion et d'inscription sont affichés sur la page My Account.
     * Cette méthode utilise une assertion pour vérifier que les pavés de connexion (Login) et d'inscription (Register) sont visibles sur la page après une déconnexion réussie. Si les pavés ne sont pas affichés, l'assertion échouera, indiquant que la déconnexion n'a pas été effectuée correctement ou que la page n'est pas correctement chargée après la déconnexion.
     * @throws AssertionError si les pavés de connexion et d'inscription ne sont pas affichés, indiquant que la déconnexion n'a pas été effectuée correctement ou que la page n'est pas correctement chargée après la déconnexion.
     */
    @Then("je suis déconnecté et la page my-account affiche les pavés Login et Register")
    public void checkLogout() {
        Assert.assertTrue(myAccountPage.isLoginAndRegisterDisplayed());
    }

    /**
     * Implémentation de l'étape And qui vérifie que les identifiants de connexion sont préremplis dans les champs de connexion après avoir coché la case "Remember Me" et s'être connecté avec succès.
     * Cette méthode utilise une assertion pour vérifier que les champs de connexion sont préremplis avec les identifiants de l'utilisateur après une connexion réussie avec la case "Remember Me" cochée. Si les champs ne sont pas préremplis, l'assertion échouera, indiquant que la fonctionnalité "Remember Me" ne fonctionne pas correctement.
     * @throws AssertionError si les champs de connexion ne sont pas préremplis, indiquant que la fonctionnalité "Remember Me" ne fonctionne pas correctement.
     */
    @And("je vois mes identifiants préremplis dans les champs de connexion")
    public void checkCredentialsPreFilled() {
        Assert.assertTrue(myAccountPage.areCredentialsPreFilled());
    }

    /**
     * Implémentation de l'étape When qui clique sur le lien "Account Details" pour accéder à la page des détails du compte.
     */
    @When("je clique sur le lien Account Details")
    public void clickOnAccountDetails() {
        myAccountPage.clickAccountDetails();
    }


}