package br.com.test.api;

import br.com.test.base.BaseApiTest;
import br.com.test.utils.PayloadUtils;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureJunit5.class)
@Epic("Teste de exemplo")
@Feature("Validação básica")
public class ApiTest extends BaseApiTest {

    @Test
    @DisplayName("Buscar usuários")
    @Story("Execução simples")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetUsers() {
        given().
                when().
                get("/users").
                then().
                statusCode(200).
                body("size()", greaterThan(0));
    }

    @Test
    @DisplayName("criar um post")
    public void testCreatePost() {
        String payload = PayloadUtils.createPostPayload("foo", "bar", 1);

        given().
                header("Content-Type", "application/json").
                body(payload).
                when().
                post("/posts").
                then().
                statusCode(201).
                body("title", equalTo("foo")).
                body("userId", equalTo(1));
    }

    @Test
    @DisplayName("Atualizar usuário existente")
    public void testUpdateUser() {
        String updatedUser = """
            {
              "name": "Alice Atualizada",
              "email": "alice.new@example.com"
            }
        """;

        given().
                header("Content-Type", "application/json").
                body(updatedUser).
                when().
                put("/users/1").
                then().
                statusCode(200).
                body("name", equalTo("Alice Atualizada")).
                body("email", equalTo("alice.new@example.com"));
    }

    @Test
    @DisplayName("deletar usuário existente")
    public void testDeleteUser() {
        when().
                delete("/users/1").
                then().
                statusCode(200);
    }
}
