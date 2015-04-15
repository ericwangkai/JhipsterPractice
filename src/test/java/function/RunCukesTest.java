package function;
/**
 * Created by tommy.wang on 3/27/2015.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/feature/"},
    format = {"pretty", "html:build/cucumber-html-report", "json:build/cucumber/cucumber-report.json"})
public class RunCukesTest {

}
