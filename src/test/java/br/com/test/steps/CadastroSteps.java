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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CadastroSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        String tempUserDataDir = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + tempUserDataDir);

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Given("que o usuário acessa a página de login do OrangeHRM")
    public void acessarPaginaLogin() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("realiza o login com usuário {string} e senha {string}")
    public void realizarLogin(String usuario, String senha) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        userField.sendKeys(usuario);

        WebElement passField = driver.findElement(By.name("password"));
        passField.sendKeys(senha);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
    }

    @When("acessa o menu PIM e clica em {string}")
    public void acessarMenuPIM(String opcao) {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(opcao))).click();
    }

    @When("preenche o formulário com nome {string}, nome do meio {string} e sobrenome {string}")
    public void preencherFormulario(String nome, String meio, String sobrenome) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(nome);
        driver.findElement(By.name("middleName")).sendKeys(meio);
        driver.findElement(By.name("lastName")).sendKeys(sobrenome);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("o sistema deve redirecionar para a página de detalhes do empregado")
    public void validarPaginaDetalhes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
    }
}
