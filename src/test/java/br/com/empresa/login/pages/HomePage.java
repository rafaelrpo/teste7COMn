package br.com.empresa.login.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userDashboard = By.id("user-dashboard");
    private final By accessDenied = By.id("access-denied");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isUserDashboardVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userDashboard)).isDisplayed();
    }

    public boolean isAccessDeniedVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accessDenied)).isDisplayed();
    }
}
