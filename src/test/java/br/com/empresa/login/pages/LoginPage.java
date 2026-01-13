package br.com.empresa.login.pages;

import br.com.empresa.login.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("loginBtn");
    private final By errorMessage = By.id("error-message");
    private final By blockedMessage = By.id("blocked-message");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.timeout()));
    }

    public void open() {
        driver.get(TestConfig.get("base.url") + "/login");
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).clear();
        driver.findElement(usernameInput).sendKeys(user);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(pass);
        driver.findElement(loginButton).click();
    }

    public String getBlockedMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockedMessage)).getText();
    }
}
