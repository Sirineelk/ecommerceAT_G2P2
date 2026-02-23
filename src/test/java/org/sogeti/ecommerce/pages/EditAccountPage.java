package org.sogeti.ecommerce.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditAccountPage extends BasePage  {

    public EditAccountPage(WebDriver driver) {
        super(driver);
    }


    public void open(String url) {
        driver.get(url);
    }

    // Champ prénom
    @FindBy(id = "account_first_name")
    private WebElement firstNameField;

    // Champ nom
    @FindBy(id = "account_last_name")
    private WebElement lastNameField;

    // Champ email
    @FindBy(id = "account_email")
    private WebElement emailField;


    // ====== Champs Password ======
    @FindBy(id = "password_current")
    private WebElement currentPasswordField;

    @FindBy(id = "password_1")
    private WebElement newPasswordField;

    @FindBy(id = "password_2")
    private WebElement confirmPasswordField;

    // ====== Bouton Save ======
    @FindBy(xpath = "//input[@name='save_account_details']")
    WebElement saveChangesButton;

    // ====== Message succès ======
    @FindBy(xpath = "//div[@class='woocommerce-message']")
    private WebElement successMessage;

    @FindBy(xpath = "//ul[contains(@class,'woocommerce-error')]//li")
    WebElement passwordErrorMessage;

    @FindBy(xpath = "//ul[contains(@class,'woocommerce-error')]//li")
    WebElement passwordErrorMessageCurrentPassword;


    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterCurrentPassword(String password) {
        currentPasswordField.clear();
        currentPasswordField.sendKeys(password);
    }

    public void enterNewPassword(String password) {
        newPasswordField.clear();
        newPasswordField.sendKeys(password);
    }

    public void confirmNewPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }
    public void clickSaveChanges() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(saveChangesButton)
        );

        // Scroll au centre
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        // Petit wait pour stabilité
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));

        // 🔥 JS Click (bypass iframe / overlay / pub)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed();
    }


    // Vérifier qu’on est sur la page Edit Account
    public boolean isOnEditAccountPage() {
        return driver.getCurrentUrl().contains("edit-account");
    }

    // Vérifier que les champs sont visibles
    public boolean areAccountFieldsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        wait.until(ExpectedConditions.visibilityOf(emailField));

        return firstNameField.isDisplayed()
                && lastNameField.isDisplayed()
                && emailField.isDisplayed();
    }

    public String getPasswordErrorMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));

        return passwordErrorMessage.getText();
    }

    public String getPasswordErrorMessageCurrentPAssword() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessageCurrentPassword));

        return passwordErrorMessageCurrentPassword.getText();
    }

}
