package br.com.test.steps.api;

import br.com.test.utils.PayloadUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static br.com.test.utils.ApiConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatePostSteps {

    private Response response;
    private String payload;

    @Given("um payload de post com título {string}, corpo {string} e userId {int}")
    public void criarPayloadDoPost(String titulo, String corpo, int userId) {
        payload = PayloadUtils.createPostPayload(titulo, corpo, userId);
    }

    @Given("um payload malformado")
    public void criarPayloadMalformado() {
        payload = "{ \"title\": \"foo\", \"body\": ";
    }

    @When("o cliente faz uma requisição POST para {string}")
    public void enviarRequisicaoPost(String endpoint) {
        response = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(endpoint);
    }

    @Then("o status code deve ser {int}")
    public void validarStatusCodeCriacaoPost(int status) {
        response.then().statusCode(status);
    }

    @Then("o título da resposta deve ser {string}")
    public void validarTituloResposta(String tituloEsperado) {
        response.then().body("title", equalTo(tituloEsperado));
    }

    @Then("o userId da resposta deve ser {int}")
    public void validarUserIdResposta(int userId) {
        response.then().body("userId", equalTo(userId));
    }
}

