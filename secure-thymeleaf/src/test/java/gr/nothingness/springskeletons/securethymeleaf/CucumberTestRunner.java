package gr.nothingness.springskeletons.securethymeleaf;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = "gr.nothingness.springskeletons.securethymeleaf",
    features = "src/test/resources",
    plugin = {"pretty", "html:target/site/cucumber-pretty.html", "json:target/cucumber.json"}
)
public class CucumberTestRunner {

}
