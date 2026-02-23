package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.configuration.ConfigReader;
import org.sogeti.ecommerce.pages.MyAccountPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class MyAccountSteps {

    private MyAccountPage myAccountPage;
    private ConfigReader settings = new ConfigReader();

    @Given("je suis sur la page My Account")
    public void openPage() {

        myAccountPage = new MyAccountPage(driver); // 👈 ici

        String url = settings.getProperty("my-account.url");
        myAccountPage.open(url);
    }

    @When("Je saisis un login {string}")
    public void enterUsername(String username){
        myAccountPage.enterUsername(username);
    }

    @When("Je saisis mon mot de passe {string}")
    public void enterPassword(String password){
        myAccountPage.enterPassword(password);
    }

    @When("Je clique sur le bouton login")
    public void clickLoginButton(){
        myAccountPage.clickLogin();
    }

    @Then("je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout")
    public void checkDashboardLinks(){
        Assert.assertTrue(myAccountPage.isDashboardLinksDisplayed());
    }


    @Then("Je ne suis pas connecté et je vois un message d'erreur indiquant que les identifiants sont incorrects")
    public void checkLoginError() {
        String errorText = myAccountPage.getLoginErrorMessage();
        Assert.assertTrue(errorText.contains("The password you entered") || errorText.contains("identifiants incorrects"));
    }

    @When("Je laisse le login et le mot de passe vides")
    public void leaveLoginAndPasswordEmpty() {
        myAccountPage.loginWithEmptyFields();
    }

    @Then("Je ne suis pas connecté et je vois des messages indiquant que les champs sont obligatoires")
    public void checkRequiredFields() {
        Assert.assertTrue(myAccountPage.isUsernameRequiredMessageDisplayed());
    }

    @Then("Je ne suis pas connecté et je vois un message d'erreur indiquant que {string} est incorrect")
    public void checkWrongUsername(String username) {
        Assert.assertTrue(myAccountPage.isUsernameNotRegisteredMessageDisplayed());
    }

    @And("je coche Remember ME")
    public void checkRememberMe() {
        myAccountPage.checkRememberMe();
    }

    @When("je clique sur le lien Logout")
    public void clickLogout() {
        myAccountPage.logout();
    }

    @Then("je suis déconnecté et la page my-account affiche les pavés Login et Register")
    public void checkLogout() {
        Assert.assertTrue(myAccountPage.isLoginAndRegisterDisplayed());
    }

    @And("je vois mes identifiants préremplis dans les champs de connexion")
    public void checkCredentialsPreFilled() {
        Assert.assertTrue(myAccountPage.areCredentialsPreFilled());
    }

}