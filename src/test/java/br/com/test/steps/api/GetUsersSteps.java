package br.com.test.steps.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.greaterThan;

public class GetUsersSteps {

    private Response response;

    @When("o cliente faz uma requisição GET para {string}")
    public void oClienteFazUmaRequisicaoGETPara(String endpoint) {
        response = RestAssured.get(endpoint);
    }

    @Then("o status da resposta ao buscar usuarios deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("o corpo da resposta deve conter mais de {int} usuários")
    public void oCorpoDaRespostaDeveConterMaisDeXUsuarios(int quantidade) {
        response.then().body("size()", greaterThan(quantidade));
    }
}
