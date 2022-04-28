package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EspnPage {

    private WebDriver driver;
    private By settingBox = By.cssSelector(".user .global-user");
    private By accountManagement = By.cssSelector(".user .global-user-container .account-management .display-user");
    private By loginButton = By.cssSelector(".user .global-user a[data-affiliatename]");
    private By logoutButton = By.cssSelector(".user .global-user .small");
    private By username = By.cssSelector(".input-wrapper input[type=\"email\"]");
    private By password = By.cssSelector(".input-wrapper input[type=\"password\"]");
    private By signInButton = By.cssSelector(".btn-group .btn-submit");
    private By profileSettings = By.cssSelector(".user .global-user-container .account-management a[tref]");
    private By deleteAccountButton = By.id("cancel-account");
    private By confirmDeleteButton = By.cssSelector(".btn.btn-primary.ng-isolate-scope");

    public EspnPage(WebDriver driver){
        this.driver = driver;
    }

    //Hover
    public void hoverProfile(){
        By profile = By.id("global-user-trigger");
        WebElement profileIcon = driver.findElement(profile);
        Actions actions = new Actions(driver);
        actions.moveToElement(profileIcon).perform();
    }

    public String getAccountManagementText(){
        hoverProfile();
        return driver.findElement(accountManagement).getText();
    }

    public boolean isSettingsBoxDisplay(){
        hoverProfile();
        return driver.findElement(settingBox).isDisplayed();
    }

    //Login
    public boolean isLoginButtonVisible(){
        return driver.findElement(loginButton).isDisplayed();
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }


    public void enterUsername(String userEmail){
        driver.findElement(username).sendKeys(userEmail);
    }

    public void enterPassword(String userPassword){
        driver.findElement(password).sendKeys(userPassword);
    }

    public void clickSingIn(){
        driver.findElement(signInButton).click();
    }

    //Logout
    public boolean verifyAccountLogged(){
        return driver.findElement(logoutButton).isDisplayed();
    }

    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    //Delete account
    public void clickProfileSettings(){
        driver.findElement(profileSettings).click();
    }

    public void waitButtonVisibility(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton));
    }

    public void clickDeleteAccount(){
        waitButtonVisibility();
        try {
            driver.findElement(deleteAccountButton).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            WebElement deleteButton = driver.findElement(By.id("cancel-account"));
            deleteButton.click();
        }
    }

    public void clickConfirmDeleteAccount(){
        driver.findElement(confirmDeleteButton).click();
    }

}
