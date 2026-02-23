package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.pages.EditAccountPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

public class EditAccountSteps {

    private EditAccountPage editAccountPage = new EditAccountPage(driver);

    @Then("je suis sur la page Edit Account")
    public void verifyEditAccountPage() {
        Assert.assertTrue(editAccountPage.isOnEditAccountPage());
    }

    @Then("je vois les informations de compte : nom, prénom et e-mail")
    public void verifyAccountFields() {
        Assert.assertTrue(editAccountPage.areAccountFieldsDisplayed());
    }


    @Then("je saisis first name {string}")
    public void enterFirstName(String firstName) {
        editAccountPage.enterFirstName(firstName);
    }

    @And("je saisis last name {string}")
    public void enterLastName(String lastName) {
        editAccountPage.enterLastName(lastName);
    }

    @When("je saisis mon mot de passe actuel {string}")
    public void enterCurrentPassword(String currentPassword) {
        editAccountPage.enterCurrentPassword(currentPassword);
    }

    @And("je saisis un nouveau mot de passe {string}")
    public void enterNewPassword(String newPassword) {
        editAccountPage.enterNewPassword(newPassword);
    }

    @And("je confirme le mot de passe {string}")
    public void confirmPassword(String confirmPassword) {
        editAccountPage.confirmNewPassword(confirmPassword);
    }

    @Then("je clique sur le bouton Save Changes")
    public void clickSave() {
        editAccountPage.clickSaveChanges();
    }

    @And("je vois un message Account details changed successfully")
    public void verifySuccessMessage() {
        Assert.assertTrue(editAccountPage.isSuccessMessageDisplayed());
    }

    @And("je vois un message derreur indiquant que le mot de passe actuel est incorrect.")
    public void verifyIncorrectCurrentPasswordMessage() {

        String actualMessage = editAccountPage.getPasswordErrorMessage();

        Assert.assertEquals(
                actualMessage,
                "Your current password is incorrect."
        );
    }

    @And("je vois un message derreur indiquant que les deux mots de passe ne correspondent pas.")
    public void verifyPasswordsDoNotMatchMessage() {

        String actualMessage = editAccountPage.getPasswordErrorMessageCurrentPAssword();

        Assert.assertEquals(
                actualMessage,
                "New passwords do not match."
        );
    }


}
