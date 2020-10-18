package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "http://localhost:8080/";

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on mainpage.jsp")
	public void i_am_on_mainpage_jsp() {
	    driver.get(ROOT_URL + "MainPage.jsp"); 
	}
	
	@Then("the banner color should be grey")
	public void the_banner_color_should_be_grey() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement element = driver.findElement(By.xpath("/html/body/div[1]"));
		String hex = Color.fromString(element.getCssValue("background-color")).asHex();
		System.out.println(element.getCssValue("hex"));
		assertEquals(hex, "#787878");
	}
	
	@Then("the banner should say {string}")
	public void the_banner_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String element = driver.findElement(By.xpath("/html/body/div[1]/h1")).getText();
		assertTrue(element.equals(string));  
		
	}
	
	@Given("I am on index.jsp")
	public void i_am_on_index_jsp() {
		driver.get(ROOT_URL);  
	}
	
	@When("I login")
	public void i_login() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement button = driver.findElement(By.id("login-submit"));
		button.click();
	}
	
	@And("I click the logout button")
	public void i_click_the_logout_button() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("logoutButton"));
		button.click();
	}
	@Then("I should be on index.jsp")
	public void i_should_be_on_index_jsp() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement tab = driver.findElement(By.className("active"));
		String buttonName = tab.getText();
		assertTrue(buttonName.equals("Login")); 
	}


	@After()
	public void after() {
		driver.quit();
	}
}
