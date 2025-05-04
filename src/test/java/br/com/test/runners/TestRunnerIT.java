package br.com.test.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br.com.test.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html"
        },
        monochrome = true

)
public class TestRunnerIT extends AbstractTestNGCucumberTests {
}
