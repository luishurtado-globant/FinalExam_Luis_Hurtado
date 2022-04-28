package com.globant;

import Data.UserData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.EspnPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AppTest {

    private WebDriver driver;
    protected EspnPage espnPage;

    @BeforeClass
    @Parameters({"url"})
    public void setUp(String url){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test (priority = 1, dataProvider = "userInfo", dataProviderClass = UserData.class)
    public void loginEspnAccount(String userEmail, String userPassword){
        espnPage = new EspnPage(driver);
        espnPage.hoverProfile();
        assertTrue(espnPage.isSettingsBoxDisplay(), "The settings box is not displayed");
        assertEquals(espnPage.getAccountManagementText(), "Welcome!", "The text is not correct");
        assertTrue(espnPage.isLoginButtonVisible(), "The login button is not visible");
        espnPage.clickLoginButton();
        WebElement iframe = driver.findElement(By.id("disneyid-iframe"));
        driver.switchTo().frame(iframe);
        espnPage.enterUsername(userEmail);
        espnPage.enterPassword(userPassword);
        espnPage.clickSingIn();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main-container")));
        driver.switchTo().defaultContent();
    }

    @Test (priority = 2)
    public void logoutAccount(){
        espnPage = new EspnPage(driver);
        espnPage.hoverProfile();
        assertTrue(espnPage.verifyAccountLogged(), "The account is not logged in");
        espnPage.clickLogoutButton();
    }

    @Test (priority = 3)
    public void deleteAccount(){
        espnPage = new EspnPage(driver);
        espnPage.hoverProfile();
        espnPage.clickProfileSettings();
        WebElement iframeD = driver.findElement(By.id("disneyid-iframe"));
        driver.switchTo().frame(iframeD);
        espnPage.clickDeleteAccount();
        espnPage.clickConfirmDeleteAccount();
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void closeSetUp(){
        driver.quit();
    }
}
