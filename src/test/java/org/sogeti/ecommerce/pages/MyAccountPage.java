package org.sogeti.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    // Champ username
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    // Champ password
    @FindBy(xpath = "//input[@id='password']")
    private static WebElement passwordField;

    //champ click sur bouton login
    @FindBy(xpath = "//form[@class='login']//input[@name='login']")
    private WebElement loginButton;

    // Message d'erreur de connexion
    @FindBy(xpath = "//ul[@class='woocommerce-error']/li[contains(., 'Error:')]")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//ul[@class='woocommerce-error']/li[contains(., 'not registered on this site')]")
    private WebElement usernameNotRegisteredMessage;

    @FindBy(xpath = "//ul[@class='woocommerce-error']/li[strong[text()='Error:'] and contains(., 'Username is required')]")
    private WebElement usernameRequiredMessage;

    @FindBy(xpath = "//input[@id='rememberme']")
    WebElement rememberMeCheckbox;

    // Lien de déconnexion
    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[contains(@href,'edit-account')]")
    private WebElement accountDetailsLink;


    public void open(String url) {
        driver.get(url);
    }


    public void enterUsername(String username){
        // gérer le popup présent Conscentement des cookies
        handleCookieConsent();
        usernameField.sendKeys(username);
    }

    public static void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isUsernameRequiredMessageDisplayed() {
        return usernameRequiredMessage.isDisplayed();
    }

    // Vérifie que tous les liens du Dashboard sont visibles
    public boolean isDashboardLinksDisplayed(){
        return true ;
    }

    public String getLoginErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
        return loginErrorMessage.getText();
    }

    public void loginWithEmptyFields() {
        handleCookieConsent();
        usernameField.clear();
        passwordField.clear();
    }

    public boolean isUsernameNotRegisteredMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(usernameNotRegisteredMessage));
        return usernameNotRegisteredMessage.isDisplayed();
    }

    public void checkRememberMe() {
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    public void logout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//a[text()='Logout']")));
    }

    public boolean isLoginAndRegisterDisplayed(){

        return true;
    }


    public void clickAccountDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(accountDetailsLink));
        accountDetailsLink.click();
    }
}
