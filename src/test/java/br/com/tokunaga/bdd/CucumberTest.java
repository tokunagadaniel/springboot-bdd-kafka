package br.com.tokunaga.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "json:target/cucumber-report/cucumber.json"},
        features = "src/test/resources/cucumber.feature",
        glue = "br.com.tokunaga.bdd"
)
public class CucumberTest {
}