package br.com.test.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static br.com.test.utils.ApiConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserSteps {

    private Response response;
    private String payload;

    @Given("um payload com nome {string} e email {string}")
    public void criarPayloadUpdate(String nome, String email) {
        payload = String.format("""
            {
              "name": "%s",
              "email": "%s"
            }
        """, nome, email);
    }

    @When("o cliente faz uma requisição PUT para {string}")
    public void oClienteFazUmaRequisicaoPut(String endpoint) {
        response = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(payload)
                .put(endpoint);
    }

    @Then("o nome da resposta deve ser {string}")
    public void validarNomeResposta(String nomeEsperado) {
        response.then().body("name", equalTo(nomeEsperado));
    }

    @And("o email da resposta deve ser {string}")
    public void validarEmailResposta(String emailEsperado) {
        response.then().body("email", equalTo(emailEsperado));
    }

    @And("o status da resposta deve ser {int}")
    public void validarStatusCode(int status) {
        response.then().statusCode(status);
    }
}
