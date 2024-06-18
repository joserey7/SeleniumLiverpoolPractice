package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "stepDefinitions"},
        plugin = {"pretty", "summary", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class LiverpoolRunner {
}
