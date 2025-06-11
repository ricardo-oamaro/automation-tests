package br.com.test.base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public static void setup() {
        // Configuração base do RestAssured
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

        // Adiciona o filtro do Allure para capturar requests/responses
        System.setProperty("allure.results.directory", "target/allure-results");

        // Habilita logs detalhados do RestAssured
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
