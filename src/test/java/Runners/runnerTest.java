package Runners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/resources/login.feature",
    glue = {"StepDefination", "Hooks"}
)
public class runnerTest extends AbstractTestNGCucumberTests {
}
