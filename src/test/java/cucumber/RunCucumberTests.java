package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Run all the cucumber tests in the current package.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = {
		"src/test/resources/cucumber/graphHistorical.feature",
		"src/test/resources/cucumber/graphPortfolio.feature",
		"src/test/resources/cucumber/graphA.feature",
		"src/test/resources/cucumber/mobile.feature",
		"src/test/resources/cucumber/SSL.feature",
		"src/test/resources/cucumber/zoom.feature",
		"src/test/resources/cucumber/graphDates.feature",
		"src/test/resources/cucumber/graphH.feature"
		//"src/test/resources/cucumber/LimitedLoginAttempts.feature", 
		// "src/test/resources/cucumber/graphH.feature",
		//"src/test/resources/cucumber/historical.feature",
		//"src/test/resources/cucumber/parser.feature",
		//"src/test/resources/cucumber/mainpage.feature",
		})
public class RunCucumberTests {

	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
	}

}
