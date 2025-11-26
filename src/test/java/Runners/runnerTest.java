package Runners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/resources/login.feature",
    glue = {"StepDefinition", "Hooks"},
    plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber.json"}
)
public class runnerTest extends AbstractTestNGCucumberTests {
}
