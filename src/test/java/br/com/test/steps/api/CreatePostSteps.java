package br.com.test.steps.api;

import br.com.test.utils.PayloadUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatePostSteps {

    private Response response;
    private String payload;

    @Given("um payload de post com título {string}, corpo {string} e userId {int}")
    public void criarPayloadDoPost(String titulo, String corpo, int userId) {
        payload = PayloadUtils.createPostPayload(titulo, corpo, userId);
    }

    @When("o cliente faz uma requisição POST para {string}")
    public void enviarRequisicaoPost(String endpoint) {
        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post(endpoint);
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

