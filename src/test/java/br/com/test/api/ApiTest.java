package br.com.test.api;

import br.com.test.base.BaseApiTest;
import br.com.test.utils.PayloadUtils;
import io.cucumber.java.After;
import io.qameta.allure.*;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTest extends BaseApiTest {

    private static final String DB_PATH = "db.json";
    private static final String BACKUP_DB_PATH = "db_backup.json";

    @Before
    public void backupDb() {
        // Fazendo a cópia do db.json para o db-backup.json antes de cada teste
        try {
            Files.copy(Paths.get(DB_PATH), Paths.get(BACKUP_DB_PATH), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup do db.json realizado antes do teste.");
        } catch (IOException e) {
            System.err.println("Erro ao realizar backup do db.json: " + e.getMessage());
        }
    }

    @After
    public void restoreDb() {
        // Após cada teste, restaura o db.json com o db-backup.json
        try {
            Files.copy(Paths.get(BACKUP_DB_PATH), Paths.get(DB_PATH), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("db.json restaurado após o teste.");
        } catch (IOException e) {
            System.err.println("Erro ao restaurar o db.json: " + e.getMessage());
        }
    }

    @Epic("User Management")
    @Feature("Get Users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Teste para validar a recuperação de usuários")
    @Test
    public void testGetUsers() {
        given().
                when().
                get("/users").
                then().
                statusCode(200).
                body("size()", greaterThan(0));
    }

    @Epic("Post Management")
    @Feature("Create Posts")
    @Severity(SeverityLevel.NORMAL)
    @Description("Teste para validar a criação de posts")
    @Test
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

    @Epic("User Management")
    @Feature("Update Users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Teste para validar a atualização de usuários")
    @Test
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

    @Epic("User Management")
    @Feature("Delete Users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Teste para validar a exclusão de usuários")
    @Test
    public void testDeleteUser() {
        when().
                delete("/users/1").
                then().
                statusCode(200);
    }
}
