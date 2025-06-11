package br.com.test.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        features = "src/test/resources/features/api",
        glue = {
                "br.com.test.hooks",
                "br.com.test.steps.api"
        }
)
public class ApiRunnerTest {

    static {
        System.setProperty("allure.results.directory", "target/allure-results");
    }
}
