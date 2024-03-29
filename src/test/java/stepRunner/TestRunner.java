package stepRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/"
        ,glue={"stepDefinition"},
        tags={"@practical"},
        plugin = {"pretty", "html:target/cucumber-htmlreport","json:target/cucumber-report.json"}
        )
public class TestRunner {
    }
