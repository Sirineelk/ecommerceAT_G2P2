package org.sogeti.ecommerce.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sogeti.ecommerce.pages.BasePage;
import org.sogeti.ecommerce.pages.EditAccountPage;

import static org.sogeti.ecommerce.configuration.DriverFactory.driver;

/**
 * <p>Cette classe contient les étapes de test pour la page "Edit Account" du site e-commerce. Elle utilise Cucumber pour définir les étapes Given, When, Then et And, qui correspondent aux actions et vérifications effectuées sur la page. Les méthodes de cette classe interagissent avec la page Edit Account à travers la classe EditAccountPage
 * qui encapsule les éléments et les actions spécifiques à cette page. Les étapes incluent la vérification de la présence des champs de compte, la saisie des informations de compte, la validation du mot de passe actuel et la confirmation du nouveau mot de passe, ainsi que la vérification des messages de succès ou d'erreur après la tentative de modification des détails du compte.
 * Elle comporte les méthodes suivantes :</p>
 * <p></p>
 * <p>- verifyEditAccountPage() : vérifie que l'utilisateur est sur la page Edit Account.</p>
 * <p>- verifyAccountFields() : vérifie que les champs de compte (nom, prénom et e-mail) sont affichés.</p>
 * <p>- enterFirstName(String firstName) : saisit le prénom dans le champ correspondant.</p>
 * <p>- enterLastName(String lastName) : saisit le nom de famille dans le champ correspondant.</p>
 * <p>- enterCurrentPassword(String currentPassword) : saisit le mot de passe actuel dans le champ correspondant.</p>
 * <p>- enterNewPassword(String newPassword) : saisit le nouveau mot de passe dans le champ correspondant.</p>
 * <p>- confirmPassword(String confirmPassword) : saisit la confirmation du nouveau mot de passe dans le champ correspondant.</p>
 * <p>- clickSave() : clique sur le bouton "Save Changes" pour tenter de sauvegarder les modifications du compte.</p>
 * <p>- verifySuccessMessage() : vérifie que le message de succès "Account details changed successfully" est affiché après la tentative de modification des détails du compte.</p>
 * <p>- verifyIncorrectCurrentPasswordMessage() : vérifie que le message d'erreur "Your current password is incorrect." est affiché lorsque le mot de passe actuel saisi est incorrect.</p>
 * <p>- verifyPasswordsDoNotMatchMessage() : vérifie que le message d'erreur "New passwords do not match." est affiché lorsque les nouveaux mots de passe saisis ne correspondent pas.</p>
 * Chaque méthode utilise des assertions pour vérifier les conditions attendues et lever des exceptions en cas d'échec, assurant ainsi la robustesse des tests.
 * @see BasePage pour les méthodes d'interaction avec la page.
 */
public class EditAccountSteps {

    private EditAccountPage editAccountPage = new EditAccountPage(driver);

    /**
     * Implémentation de l'étape Then qui vérifie que l'utilisateur est sur la page Edit Account. Cette méthode utilise une assertion pour vérifier que l'URL actuelle correspond à celle de la page Edit Account. Si l'utilisateur n'est pas sur la page Edit Account, une assertion échoue avec un message d'erreur indiquant que l'utilisateur n'est pas sur la page Edit Account.
     * @throws AssertionError si l'utilisateur n'est pas sur la page Edit Account.
     */
    @Then("je suis sur la page Edit Account")
    public void verifyEditAccountPage() {
        Assert.assertTrue(editAccountPage.isOnEditAccountPage());
    }

    /**
     * Implémentation de l'étape Then qui vérifie que les champs de compte (nom, prénom et e-mail) sont affichés sur la page Edit Account. Cette méthode utilise une assertion pour vérifier que les éléments correspondants aux champs de compte sont visibles sur la page. Si les champs de compte ne sont pas affichés, une assertion échoue avec un message d'erreur indiquant que les champs de compte ne sont pas visibles.
     * @throws AssertionError si les champs de compte ne sont pas affichés.
     */
    @Then("je vois les informations de compte : nom, prénom et e-mail")
    public void verifyAccountFields() {
        Assert.assertTrue(editAccountPage.areAccountFieldsDisplayed());
    }

    /**
     * Implémentation de l'étape Then qui saisit le prénom dans le champ correspondant sur la page Edit Account. Cette méthode prend en paramètre une chaîne de caractères représentant le prénom à saisir, puis utilise la méthode enterFirstName() de la classe EditAccountPage pour remplir le champ du prénom avec la valeur fournie.
     * @param firstName
     */
    @Then("je saisis first name {string}")
    public void enterFirstName(String firstName) {
        editAccountPage.enterFirstName(firstName);
    }

    /**
     * Implémentation de l'étape And qui saisit le nom de famille dans le champ correspondant sur la page Edit Account. Cette méthode prend en paramètre une chaîne de caractères représentant le nom de famille à saisir, puis utilise la méthode enterLastName() de la classe EditAccountPage pour remplir le champ du nom de famille avec la valeur fournie.
     * @param lastName
     */
    @And("je saisis last name {string}")
    public void enterLastName(String lastName) {
        editAccountPage.enterLastName(lastName);
    }

    /**
     * Implémentation de l'étape When qui saisit le mot de passe actuel dans le champ correspondant sur la page Edit Account. Cette méthode prend en paramètre une chaîne de caractères représentant le mot de passe actuel à saisir, puis utilise la méthode enterCurrentPassword() de la classe EditAccountPage pour remplir le champ du mot de passe actuel avec la valeur fournie.
     * @param currentPassword
     */
    @When("je saisis mon mot de passe actuel {string}")
    public void enterCurrentPassword(String currentPassword) {
        editAccountPage.enterCurrentPassword(currentPassword);
    }

    /**
     * Implémentation de l'étape And qui saisit le nouveau mot de passe dans le champ correspondant sur la page Edit Account. Cette méthode prend en paramètre une chaîne de caractères représentant le nouveau mot de passe à saisir, puis utilise la méthode enterNewPassword() de la classe EditAccountPage pour remplir le champ du nouveau mot de passe avec la valeur fournie.
     * @param newPassword
     */
    @And("je saisis un nouveau mot de passe {string}")
    public void enterNewPassword(String newPassword) {
        editAccountPage.enterNewPassword(newPassword);
    }

    /**
     * Implémentation de l'étape And qui saisit la confirmation du nouveau mot de passe dans le champ correspondant sur la page Edit Account. Cette méthode prend en paramètre une chaîne de caractères représentant la confirmation du nouveau mot de passe à saisir, puis utilise la méthode confirmNewPassword() de la classe EditAccountPage pour remplir le champ de confirmation du nouveau mot de passe avec la valeur fournie.
     * @param confirmPassword
     */
    @And("je confirme le mot de passe {string}")
    public void confirmPassword(String confirmPassword) {
        editAccountPage.confirmNewPassword(confirmPassword);
    }

    /**
     * Implémentation de l'étape Then qui clique sur le bouton "Save Changes" pour tenter de sauvegarder les modifications du compte sur la page Edit Account. Cette méthode utilise la méthode clickSaveChanges() de la classe EditAccountPage pour simuler un clic sur le bouton "Save Changes". Après l'exécution de cette étape, les modifications du compte seront soumises et le système affichera soit un message de succès, soit un message d'erreur en fonction des informations saisies.
     */
    @Then("je clique sur le bouton Save Changes")
    public void clickSave() {
        editAccountPage.clickSaveChanges();
    }

    /**
     * Implémentation de l'étape And qui vérifie que le message de succès "Account details changed successfully" est affiché après la tentative de modification des détails du compte sur la page Edit Account. Cette méthode utilise une assertion pour vérifier que le message de succès est visible sur la page. Si le message de succès n'est pas affiché, une assertion échoue avec un message d'erreur indiquant que le message de succès n'est pas visible.
     * @throws AssertionError si le message de succès "Account details changed successfully" n'est pas affiché, indiquant que la modification des détails du compte n'a pas été effectuée avec succès ou que le message de confirmation n'est pas visible sur la page.
     */
    @And("je vois un message Account details changed successfully")
    public void verifySuccessMessage() {
        Assert.assertTrue(editAccountPage.isSuccessMessageDisplayed());
    }

    /**
     * Implémentation de l'étape And qui vérifie que le message d'erreur "Your current password is incorrect." est affiché lorsque le mot de passe actuel saisi est incorrect sur la page Edit Account. Cette méthode utilise une assertion pour vérifier que le message d'erreur est visible sur la page. Si le message d'erreur n'est pas affiché, une assertion échoue avec un message d'erreur indiquant que le message d'erreur n'est pas visible.
     * @throws AssertionError si le message d'erreur "Your current password is incorrect." n'est pas affiché, indiquant que la validation du mot de passe actuel ne fonctionne pas correctement ou que le message d'erreur n'est pas visible sur la page.
     */
    @And("je vois un message derreur indiquant que le mot de passe actuel est incorrect.")
    public void verifyIncorrectCurrentPasswordMessage() {

        String actualMessage = editAccountPage.getPasswordErrorMessage();

        Assert.assertEquals(
                actualMessage,
                "Your current password is incorrect."
        );
    }

    /**
     * Implémentation de l'étape And qui vérifie que le message d'erreur "New passwords do not match." est affiché lorsque les nouveaux mots de passe saisis ne correspondent pas sur la page Edit Account. Cette méthode utilise une assertion pour vérifier que le message d'erreur est visible sur la page. Si le message d'erreur n'est pas affiché, une assertion échoue avec un message d'erreur indiquant que le message d'erreur n'est pas visible.
     * @throws AssertionError si le message d'erreur "New passwords do not match." n'est pas affiché, indiquant que la validation de la correspondance des nouveaux mots de passe ne fonctionne pas correctement ou que le message d'erreur n'est pas visible sur la page.
     */
    @And("je vois un message derreur indiquant que les deux mots de passe ne correspondent pas.")
    public void verifyPasswordsDoNotMatchMessage() {

        String actualMessage = editAccountPage.getPasswordErrorMessageCurrentPAssword();

        Assert.assertEquals(
                actualMessage,
                "New passwords do not match."
        );
    }


}
