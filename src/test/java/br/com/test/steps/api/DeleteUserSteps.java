package br.com.test.steps.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.when;

public class DeleteUserSteps {

    private Response response;

    @When("o cliente faz uma requisição DELETE para {string}")
    public void oClienteFazUmaRequisicaoDelete(String endpoint) {
        response = when().delete(endpoint);
    }

    @Then("o status da resposta ao deletar usuario deve ser {int}")
    public void validarStatusCode(int status) {
        response.then().statusCode(status);
    }
}
