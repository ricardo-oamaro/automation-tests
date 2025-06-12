package br.com.test.steps.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static br.com.test.utils.ApiConstants.BASE_URI;
import static io.restassured.RestAssured.when;

public class DeleteUserSteps {

    private Response response;

    @When("o cliente faz uma requisição DELETE para {string}")
    public void oClienteFazUmaRequisicaoDelete(String endpoint) {
        response = RestAssured.given()
                .baseUri(BASE_URI)
                .when()
                .delete(endpoint);
    }

    @Then("o status da resposta ao deletar usuario deve ser {int}")
    public void validarStatusCode(int status) {
        response.then().statusCode(status);
    }
}
