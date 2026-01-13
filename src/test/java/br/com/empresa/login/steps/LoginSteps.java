package br.com.empresa.login.steps;

import br.com.empresa.login.config.TestConfig;
import br.com.empresa.login.core.DriverFactory;
import br.com.empresa.login.pages.HomePage;
import br.com.empresa.login.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("que acesso a página de login")
    public void acessoPaginaLogin() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.open();
    }

    @When("realizo login com usuário USER válido")
    public void loginUsuarioValido() {
        loginPage.login(
                TestConfig.get("user.username"),
                TestConfig.get("user.password")
        );
    }

    @When("realizo login com usuário VISITOR válido")
    public void loginVisitorValido() {
        loginPage.login(
                TestConfig.get("visitor.username"),
                TestConfig.get("visitor.password")
        );
    }

    @When("realizo 3 tentativas de login com senha inválida")
    public void tresTentativasInvalidas() {
        for (int i = 0; i < 3; i++) {
            loginPage.login(
                    TestConfig.get("user.username"),
                    TestConfig.get("invalid.password")
            );
        }
    }

    @Then("devo visualizar o dashboard do usuário")
    public void validarDashboard() {
        assertTrue(homePage.isUserDashboardVisible());
    }

    @Then("devo visualizar mensagem de acesso negado")
    public void validarAcessoNegado() {
        assertTrue(homePage.isAccessDeniedVisible());
    }

    @Then("devo visualizar mensagem de usuário bloqueado")
    public void validarUsuarioBloqueado() {
        assertEquals(
                "Your username is invalid!",
                loginPage.getBlockedMessage()
        );
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
