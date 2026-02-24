package org.sogeti.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sogeti.ecommerce.configuration.Hooks;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;


public class ShopPage extends BasePage {

    private WebDriverWait wait;

    public ShopPage(WebDriver driver) {
        super(driver);
        this.driver = Hooks.driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Éléments (Correction des points-virgules) ---

    @FindBy(xpath = "//a[text()='Shop']")
    private WebElement shopLink;

    @FindBy(xpath = "//a[contains(@class, 'add_to_cart_button')]")
    private List<WebElement> listAddToCartButton;

    @FindBy(xpath = "//a[contains(@class, 'wc-forward')]")
    private WebElement viewBasketLink;

    @FindBy(xpath = "//li[contains(@class, 'cat-item')]/a")
    private List<WebElement> listTheme;

    @FindBy(xpath = "//button[text()='Filter']")
    private WebElement filterByPrice;

    @FindBy(xpath = "//a[@class='woocommerce-LoopProduct-link']/img")
    private List<WebElement> listProductImage;

    @FindBy(xpath = "//a[contains(@class, 'woocommerce-LoopProduct-link')]/h3")
    private WebElement productTitle;

    @FindBy(xpath = "//a[@class='woocommerce-LoopProduct-link']/span[@class='price']")
    private WebElement productPrice;

    @FindBy(xpath = "//a[contains(@class, 'button') and contains(@href, '/product/')]")
    private List<WebElement> productDetailsLink;

    @FindBy(xpath = "(id = \"wpmenucartli\")")
    private WebElement BasketLink;

    String theme;
    String image;


    public void jeSuisSurLaPageShop() {
        wait.until(ExpectedConditions.elementToBeClickable(shopLink)).click();
    }

    public void jaiAjouteUnArticleAuPanier() {
        Random random = new Random();
        int index = random.nextInt(listAddToCartButton.size());
        wait.until(ExpectedConditions.elementToBeClickable(listAddToCartButton.get(index))).click();
    }

    public void jeCliqueSurLeLienViewBasketQuiApparaîtSousLarticle() {
        wait.until(ExpectedConditions.visibilityOf(viewBasketLink)).click();
    }

    public void jeSuisRedirigéVersLaPageBasket() {
        wait.until(ExpectedConditions.urlContains("basket"));
        assertTrue("L'URL contient 'basket'",driver.getCurrentUrl().contains("basket"));
    }

    public void jeFiltreLesArticlesParTheme() {
        Random random = new Random();
        int index = random.nextInt(listTheme.size());
        theme = listTheme.get(index).getText();
        wait.until(ExpectedConditions.elementToBeClickable(listTheme.get(index))).click();
    }

    public void laVueAfficheLesArticlesAuThèmeChoisi(){
        wait.until(ExpectedConditions.urlContains(theme.toLowerCase()));
        assertTrue("L'URL contient" + theme,driver.getCurrentUrl().contains(theme.toLowerCase()));
    }


    public void jeFiltreLesArticlesParPrix() {
        filterByPrice.click();
    }

    public void laVueAfficheLesArticlesDansLordreCorrespondant() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.urlContains("min_price"),
                ExpectedConditions.urlContains("max_price")
        ));
    }

    public void unePhotoDescriptiveUnLibelléEtLePrixAffichéAuDessus() {
        Random random = new Random();
        int index = random.nextInt(listProductImage.size());
        image = listProductImage.get(index).getText();
        wait.until(ExpectedConditions.elementToBeClickable(listProductImage.get(index))).click();
        assertTrue("Titre présent", productTitle.isDisplayed());
        assertTrue("Prix présent", productPrice.isDisplayed());
    }

    public void unLienActifSurUnDeCesElementRedirigeVersLeDétailDeLarticle() {
        wait.until(ExpectedConditions.urlContains(image.toLowerCase()));
        assertTrue("L'URL contient" + image,driver.getCurrentUrl().contains(image.toLowerCase()));
    }

    public void jeCliqueSurLicôneDuPanierSituéeEnEntête(){
        BasketLink.click();
    }
}
