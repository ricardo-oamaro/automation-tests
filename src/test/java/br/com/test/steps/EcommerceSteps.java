package br.com.test.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcommerceSteps {

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

    @Given("que o usuário acessa o site SauceDemo")
    public void acessarSite() {
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
    }

    @When("ele faz login com usuário {string} e senha {string}")
    public void fazerLogin(String username, String password) throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @When("adiciona o produto {string} ao carrinho")
    public void adicionarProdutoAoCarrinho(String nomeProduto) throws InterruptedException {
        WebElement botaoAdicionar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + nomeProduto + "']/ancestor::div[@class='inventory_item']//button")));
        botaoAdicionar.click();
    }

    @Then("o carrinho deve conter o produto {string}")
    public void validarProdutoNoCarrinho(String nomeProduto) {
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement itemNoCarrinho = driver.findElement(By.className("inventory_item_name"));
        Assertions.assertEquals(nomeProduto, itemNoCarrinho.getText());
    }

    @When("ele remove o produto {string} do carrinho")
    public void removerProdutoDoCarrinho(String nomeProduto) {
        if (!driver.getCurrentUrl().contains("cart")) {
            driver.findElement(By.className("shopping_cart_link")).click();
        }
        WebElement botaoRemover = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[text()='" + nomeProduto + "']/ancestor::div[@class='cart_item']//button"))));
        botaoRemover.click();
    }

    @Then("o carrinho não deve conter o produto {string}")
    public void validarProdutoRemovido(String nomeProduto) {
        boolean produtoRemovido = driver.findElements(By.xpath("//div[text()='" + nomeProduto + "']")).isEmpty();
        Assertions.assertTrue(produtoRemovido);
    }

    @When("vai para o carrinho")
    public void acessarCarrinho() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @When("inicia o checkout")
    public void iniciarCheckout() {
        WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
        checkout.click();
    }

    @When("preenche os dados de pagamento com nome {string}, sobrenome {string} e CEP {string}")
    public void preencherDadosPagamento(String nome, String sobrenome, String cep) {
        driver.findElement(By.id("first-name")).sendKeys(nome);
        driver.findElement(By.id("last-name")).sendKeys(sobrenome);
        driver.findElement(By.id("postal-code")).sendKeys(cep);
        driver.findElement(By.id("continue")).click();
    }

    @When("finaliza a compra")
    public void finalizarCompra() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("a compra deve ser concluída com sucesso")
    public void validarCompraConcluida() {
        WebElement mensagem = driver.findElement(By.className("complete-header"));
        Assertions.assertEquals("Thank you for your order!", mensagem.getText());
    }

}
