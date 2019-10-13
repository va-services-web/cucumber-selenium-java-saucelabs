package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C://Learning//Portfolio//cucumber-selenium-java-saucelabs//Features//animana.feature",
        glue = "StepDefinitions"
)
public class TestRunner{
}
