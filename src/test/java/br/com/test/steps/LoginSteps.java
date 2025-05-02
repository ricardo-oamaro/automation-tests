package br.com.test.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        String headlessEnv = System.getenv("HEADLESS");
        boolean isHeadless = headlessEnv != null && headlessEnv.equalsIgnoreCase("true");

        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("o usuário está na página de login do OrangeHRM")
    public void usuario_na_pagina_login() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("ele informa usuário {string} e senha {string}")
    public void informa_credenciais(String usuario, String senha) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        userField.sendKeys(usuario);

        WebElement passField = driver.findElement(By.name("password"));
        passField.sendKeys(senha);
    }

    @When("clica no botão de login")
    public void clica_botao_login() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("ele deve ver o painel de controle")
    public void verifica_painel() throws InterruptedException {
        Thread.sleep(2000); // simples espera para o carregamento
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Then("ele deve ver uma mensagem de erro")
    public void mensagem_de_erro() throws InterruptedException {
        Thread.sleep(2000);
        String erro = driver.findElement(By.cssSelector(".oxd-alert-content-text")).getText();
        assertTrue(erro.contains("Invalid credentials"));
    }

    @When("ele clica no ícone do usuário e seleciona logout")
    public void clicar_logout() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.className("oxd-userdropdown-name")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    @Then("ele deve retornar para a página de login")
    public void retorna_para_login() throws InterruptedException {
        Thread.sleep(2000);
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("/auth/login"));
    }

    @Given("o usuário está logado no sistema com usuário {string} e senha {string}")
    public void login_com_sucesso(String usuario, String senha) {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(usuario);
        driver.findElement(By.name("password")).sendKeys(senha);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @When("ele acessa o menu {string}")
    public void acessa_menu(String menu) {
        WebElement menuElemento = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + menu + "']")
        ));
        menuElemento.click();
    }

    @Then("o sistema deve exibir a seção {string}")
    public void valida_secao(String secaoEsperada) {
        WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[text()='" + secaoEsperada + "']")
        ));
        assertEquals(secaoEsperada, titulo.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
