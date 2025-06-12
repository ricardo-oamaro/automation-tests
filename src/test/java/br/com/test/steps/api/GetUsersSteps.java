package br.com.test.steps.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static br.com.test.utils.ApiConstants.BASE_URI;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class GetUsersSteps {

    private Response response;

    @When("o cliente faz uma requisição GET para {string}")
    public void oClienteFazUmaRequisicaoGETPara(String endpoint) {
        response = RestAssured.given()
                .baseUri(BASE_URI)
                .when()
                .get(endpoint);
    }

    @Then("o status da resposta ao buscar usuarios deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("o corpo da resposta deve conter mais de {int} usuários")
    public void oCorpoDaRespostaDeveConterMaisDeXUsuarios(int quantidade) {
        response.then().body("size()", greaterThan(quantidade));
    }

    @Then("o corpo da resposta deve conter 0 usuários")
    public void validarCorpoRespostaVazia() {
        response.then()
                .body("", hasSize(0));
    }
}
