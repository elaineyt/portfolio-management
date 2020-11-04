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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "https://localhost:8443/";

	private final WebDriver driver = new ChromeDriver();
	
	//SSL.feature
	//
	//
	//
	//SSL.feature
	@Then("the url has https")
	public void the_url_has_https() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = ROOT_URL;
		assertTrue(url.contains("https"));	
	}
	
	//LimitedLoginAttemps.feature
	//
	//
	//
	//LimitedLoginAttemps.feature
	
	@When("I click login")
	public void i_click_login() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("login-submit"));
	}
	
	@When("I enter the username {string} and incorrect password {string} 3 times")
	public void i_enter_the_username_and_incorrect_password(String string, String string2) {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys(string);
		driver.findElement(By.id("login-password")).sendKeys(string2);
		
		//enter incorrectly 3 times
		for( int i = 0; i<3; i++) {	
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement button = driver.findElement(By.id("login-submit"));
			button.click();
		}
	
		
	}
	
	@When("I enter the username {string} and password {string}")
	public void i_enter_the_username_and_password(String string, String string2) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.id("login-username")).sendKeys(string);
			driver.findElement(By.id("login-password")).sendKeys(string2);
			WebElement button = driver.findElement(By.id("login-submit"));
			button.click();
	}
	
	@When("I wait a minute")
	public void i_wait_a_minute() {
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("there should be the login error {string}")
	public void there_should_be_the_login_error(String string) {
		String error = driver.findElement(By.id("loginError")).getText();
	    assertTrue(string.equals(error));
	}
	
	
	//mainpage.feature 
	//
	//
	//
	//mainpage.feature
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
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-submit")));
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
	
	@When("I add an invalid stock")
	public void i_add_an_invalid_stock() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("AAPLL");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();

	}

	@Then("the ticker symbol error should say {string}")
	public void the_ticker_symbol_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addStockErrorTS")).getText();
	    assertTrue(string.equals(error));
	}
	
	@Given("I have added a new stock")
	public void i_have_added_a_new_stock() {
		
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}
	
	@When("I add a duplicate stock")
	public void i_add_a_duplicate_stock() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("addStockTicker")).clear();
		driver.findElement(By.id("addStockShares")).clear();
		driver.findElement(By.id("addStockBuyDate")).clear();
		driver.findElement(By.id("addStockSellDate")).clear();
		
		driver.findElement(By.id("addStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}
	
	@Then("the duplicate error should say {string}")
	public void the_duplicate_error_should_say(String string) {
		
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String error = driver.findElement(By.id("addStockErrorTS")).getText();
		    assertTrue(string.equals(error));
			
		}
		finally {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement button = driver.findElement(By.xpath("//*[@id=\"addStockModal\"]/div/div/div[3]/button[1]"));
			button.click();
			
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rAAPL']/div[3]/button")));
			WebElement button1 = driver.findElement(By.xpath("//*[@id='rAAPL']/div[3]/button"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			button1.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement button2 = driver.findElement(By.id("deleteStock"));
			button2.click();
		}
  
	    
	}

	@When("I add a stock without share amount")
	public void i_add_a_stock_without_share_amount() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}

	@Then("the share error should say {string}")
	public void the_share_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addStockErrorShares")).getText();
	    assertTrue(string.equals(error));
	    
	}

	@When("I add a stock with a fractional share")
	public void i_add_a_stock_with_a_fractional_share() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("0.5");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	    
	}


	@When("I add a stock with less than one share")
	public void i_add_a_stock_with_less_than_one_share() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("0");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}

	@When("I add a stock with empty dates")
	public void i_add_a_stock_with_empty_dates() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("");
		driver.findElement(By.id("addStockSellDate")).sendKeys("");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}

	@Then("the buy error should say {string}")
	public void the_buy_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addStockErrorBuy")).getText();
	    assertTrue(string.equals(error));
	    
	}

	@When("I add a stock with empty buy date")
	public void i_add_a_stock_with_empty_buy_date() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}

	@When("I add a stock with incorrect dates")
	public void i_add_a_stock_with_incorrect_dates() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/14/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}

	@Then("the sell error should say {string}")
	public void the_sell_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addStockErrorSell")).getText();
	    assertTrue(string.equals(error));
	}
	
	@When("I add a valid stock")
	public void i_add_a_valid_stock() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}

	@Then("the stock should be added to my portfolio")
	public void the_stock_should_be_added_to_my_portfolio() {
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement stock = driver.findElement(By.id("rGOOG"));
			assertTrue(stock != null);
		}
		finally {
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rGOOG']/div[3]/button")));
			WebElement button1 = driver.findElement(By.xpath("//*[@id='rGOOG']/div[3]/button"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			button1.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement button2 = driver.findElement(By.id("deleteStock"));
			button2.click();
			
		}
	}
	
	@Given("I have added the stock")
	public void i_have_added_the_stock() {
		
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addStockTicker")).sendKeys("TSLA");
		driver.findElement(By.id("addStockShares")).sendKeys("1");
		driver.findElement(By.id("addStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addStock"));
		button1.click();
	}
	
	@When("I delete a stock")
	public void i_delete_a_stock() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rTSLA']/div[3]/button")));
		WebElement button = driver.findElement(By.xpath("//*[@id='rTSLA']/div[3]/button"));

		button.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button1 = driver.findElement(By.id("deleteStock"));
		button1.click();
		
	}

	@Then("the stock should be removed from my portfolio")
	public void the_stock_should_be_removed_from_my_porfolio() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean exist = driver.findElements(By.id("rTSLA")).size() != 0;
		
		assertTrue(exist == false);
	}

	
	
	
	
	//historical.feature 
	//
	//
	//
	//historical.feature

	@Given("I have added an invalid stock")
	public void i_have_added_an_invalid_stock() {
		//login first
		driver.get(ROOT_URL); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPLL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Then("the historical ticker symbol error should say {string}")
	public void the_historical_ticker_symbol_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addHistoricalStockErrorTS")).getText();
	    assertTrue(string.equals(error));
	}

	@Given("I have added a new historical stock")
	public void i_have_added_a_new_historical_stock() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Given("I add a historical duplicate stock")
	public void i_add_a_historical_duplicate_stock() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("addHistoricalStockTicker")).clear();
		driver.findElement(By.id("addHistoricalStockShares")).clear();
		driver.findElement(By.id("addHistoricalStockBuyDate")).clear();
		driver.findElement(By.id("addHistoricalStockSellDate")).clear();
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/31/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}
	
	@Then("the historical duplicate error should say {string}")
	public void the_historical_duplicate_error_should_say(String string) {
		
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String error = driver.findElement(By.id("addHistoricalStockErrorTS")).getText();
		    assertTrue(string.equals(error));
			
		}
		finally {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement button = driver.findElement(By.xpath("//*[@id=\"addHistoricalStockModal\"]/div/div/div[3]/button[1]"));
			button.click();
		
			
			
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"r-historical-AAPL\"]/div[3]/button")));
			WebElement button1 = driver.findElement(By.xpath("//*[@id=\"r-historical-AAPL\"]/div[3]/button"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			button1.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement button2 = driver.findElement(By.id("deleteHistoricalStock"));
			button2.click();
		}
  
	    
	}

	@Given("I have added a stock without share amount")
	public void i_have_added_a_stock_without_share_amount() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Then("the historical share error should say {string}")
	public void the_historical_share_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addHistoricalStockErrorShares")).getText();
	    assertTrue(string.equals(error));
	}

	@Given("I have added a stock with a fractional share")
	public void i_have_added_a_stock_with_a_fractional_share() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("0.6");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}


	@Given("I have added a stock with less than one share")
	public void i_have_added_a_stock_with_less_than_one_share() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("0");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Given("I have added a stock with empty dates")
	public void i_have_added_a_stock_with_empty_dates() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}
	
	@Then("the historical buy error should say {string}")
	public void the_historical_buy_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addHistoricalStockErrorBuy")).getText();
	    assertTrue(string.equals(error));
	}
	
	

	@Given("I have added a stock with empty buy date")
	public void i_have_added_a_stock_with_empty_buy_date() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Given("I have added a stock with incorrect dates")
	public void i_have_added_a_stock_with_incorrect_dates() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("AAPL");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/14/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Then("the historical sell error should say {string}")
	public void the_historical_sell_error_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("addHistoricalStockErrorSell")).getText();
	    assertTrue(string.equals(error));
	}

	@Given("I added a valid historical stock")
	public void i_added_a_valid_historical_stock() {
		driver.get(ROOT_URL); 
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("GOOG");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Then("the historical stock should be added to my portfolio")
	public void the_historical_stock_should_be_added_to_my_portfolio() {
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement stock = driver.findElement(By.id("r-historical-GOOG"));
			assertTrue(stock != null);
		}
		finally {
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='r-historical-GOOG']/div[3]/button")));
			WebElement button1 = driver.findElement(By.xpath("//*[@id='r-historical-GOOG']/div[3]/button"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			button1.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement button2 = driver.findElement(By.id("deleteHistoricalStock"));
			button2.click();
			
		}
	}

	@Given("I have added the historical stock")
	public void i_have_added_the_historical_stock() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement button = driver.findElement(By.id("addHistoricalStockModalButton"));
		button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("addHistoricalStockTicker")).sendKeys("TSLA");
		driver.findElement(By.id("addHistoricalStockShares")).sendKeys("1");
		driver.findElement(By.id("addHistoricalStockBuyDate")).sendKeys("10/15/2020");
		driver.findElement(By.id("addHistoricalStockSellDate")).sendKeys("10/30/2020");
		
		WebElement button1 = driver.findElement(By.id("addHistoricalStock"));
		button1.click();
	}

	@Given("I delete the historical stock")
	public void i_delete_the_historical_stock() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='r-historical-TSLA']/div[3]/button")));
		WebElement button = driver.findElement(By.xpath("//*[@id='r-historical-TSLA']/div[3]/button"));

		button.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement button1 = driver.findElement(By.id("deleteHistoricalStock"));
		button1.click();
	}

	@Then("the historical stock should be removed from my portfolio")
	public void the_historical_stock_should_be_removed_from_my_portfolio() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean exist = driver.findElements(By.id("r-historical-TSLA")).size() != 0;
		
		assertTrue(exist == false);
	}
	
	//graph.feature 
	//
	//
	//
	//graph.feature
	@Given("I enter a start date that is before my end date")
	public void i_enter_a_start_date_that_is_before_my_end_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("graphStartDate")).clear();
		driver.findElement(By.id("graphStartDate")).sendKeys("10/31/2020");
		
		//click the select all button just to toggle off of the start date text box. this will allow the test case to pass
		WebElement button1 = driver.findElement(By.xpath("//*[@id=\"select-all-portfolio\"]/div[1]/input"));
		button1.click();
		
	
	}

	@Then("the date error symbol should say {string}")
	public void the_date_error_symbol_should_say(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String error = driver.findElement(By.id("graphDateError")).getText();
	    assertTrue(string.equals(error));
	}
	
	@Given("I am idle for one hundred and twenty seconds")
	public void i_am_idle_for_one_hundred_and_twenty_seconds() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}
	
	//zoom.feature 
	//
	//
	//
	//zoom.feature
	@Given("I click the zoom out button the graph units are in days the start date is not the furthest date in the past the end date is not todays date")
	public void i_click_the_zoom_out_button_the_graph_units_are_in_days_the_start_date_is_not_the_furthest_date_in_the_past_the_end_date_is_not_todays_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// * First zoom in to make sure the end date isn't max
		WebElement zoomIn = driver.findElement(By.id("zoomIn"));
		zoomIn.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("the start date should decrease by one day and the end date should increase by a day")
	public void the_start_date_should_decrease_by_one_day_and_the_end_date_should_increase_by_a_day() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomOut = driver.findElement(By.id("zoomOut"));
			zoomOut.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait = new WebDriverWait(driver, 5);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			System.out.println("Updated graph start date: " + new_start_date.toString());
			System.out.println("Original graph start date: " + original_start_date.toString());
			
			assertTrue(original_start_date.compareTo(new_start_date) == 1);
			assertTrue(new_end_date.compareTo(original_end_date) == 1);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		}  
		
		driver.close();
		
	}

	@Given("I click the zoom out button the graph units are in days the start date is not the furthest date in the past the end date is todays date")
	public void i_click_the_zoom_out_button_the_graph_units_are_in_days_the_start_date_is_not_the_furthest_date_in_the_past_the_end_date_is_todays_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
	}

	@Then("the start date should decrease by one day and the end date should stay the same")
	public void the_start_date_should_decrease_by_one_day_and_the_end_date_should_stay_the_same() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomOut = driver.findElement(By.id("zoomOut"));
			zoomOut.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(original_start_date.compareTo(new_start_date) == 1);
			assertTrue(new_end_date.compareTo(original_end_date) == 0);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		}  
		
		driver.close();
	}

	@Given("I click the zoom out button the graph units are in days the start date is the furthest date in the past the end date is todays date")
	public void i_click_the_zoom_out_button_the_graph_units_are_in_days_the_start_date_is_the_furthest_date_in_the_past_the_end_date_is_todays_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Set the earliest start date
		driver.findElement(By.id("graphStartDate")).clear();
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.YEAR, -1);
		driver.findElement(By.id("graphStartDate")).sendKeys(new SimpleDateFormat("MM/dd/yyyy").format(instance.getTime()));
		
	}

	@Then("the start date and end date should remain the same")
	public void the_start_date_and_end_date_should_remain_the_same() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomOut = driver.findElement(By.id("zoomOut"));
			zoomOut.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(original_start_date.compareTo(new_start_date) == 0);
			assertTrue(new_end_date.compareTo(original_end_date) == 0);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		}  
		
		driver.close();
	}

	// Zoom Out Weeks
	@Given("I click the zoom out button the graph units are in weeks the start date is not the furthest date in the past the end date is not todays date")
	public void i_click_the_zoom_out_button_the_graph_units_are_in_weeks_the_start_date_is_not_the_furthest_date_in_the_past_the_end_date_is_not_todays_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * First zoom in to make sure the end date isn't max
		WebElement zoomIn = driver.findElement(By.id("zoomIn"));
		zoomIn.click();
		
		// * Then Select Weeks
		Select graphUnit = new Select(driver.findElement(By.id("graphUnits")));
		graphUnit.selectByValue("weeks");
	}

	@Then("the start date should decrease by one week and the end date should increase by one day")
	public void the_start_date_should_decrease_by_one_week_and_the_end_date_should_increase_by_one_day() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomOut = driver.findElement(By.id("zoomOut"));
			zoomOut.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(original_start_date.compareTo(new_start_date) == 1);
			assertTrue(new_end_date.compareTo(original_end_date) == 1);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		}  
		
		driver.close();
	}

	@Given("I click the zoom out button the graph units are in weeks the start date is not the furthest date in the past the end date is todays date")
	public void i_click_the_zoom_out_button_the_graph_units_are_in_weeks_the_start_date_is_not_the_furthest_date_in_the_past_the_end_date_is_todays_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Then Select Weeks
		Select graphUnit = new Select(driver.findElement(By.id("graphUnits")));
		graphUnit.selectByValue("weeks");
	}

	@Then("the start date should decrease by one week and the end date should stay the same")
	public void the_start_date_should_decrease_by_one_week_and_the_end_date_should_stay_the_same() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomOut = driver.findElement(By.id("zoomOut"));
			zoomOut.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(original_start_date.compareTo(new_start_date) == 1);
			assertTrue(new_end_date.compareTo(original_end_date) == 0);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		}  
		
		driver.close();
	}

	@Given("I click the zoom out button the graph units are in weeks the start date is the furthest date in the past the end date is todays date")
	public void i_click_the_zoom_out_button_the_graph_units_are_in_weeks_the_start_date_is_the_furthest_date_in_the_past_the_end_date_is_todays_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Set the earliest start date
		driver.findElement(By.id("graphStartDate")).clear();
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.YEAR, -1);
		driver.findElement(By.id("graphStartDate")).sendKeys(new SimpleDateFormat("MM/dd/yyyy").format(instance.getTime()));
		
		// * Then Select Weeks
		Select graphUnit = new Select(driver.findElement(By.id("graphUnits")));
		graphUnit.selectByValue("weeks");
	}

	@Then("the start date and end date should be the same")
	public void the_start_date_and_end_date_should_be_the_same() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomOut = driver.findElement(By.id("zoomOut"));
			zoomOut.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(original_start_date.compareTo(new_start_date) == 0);
			assertTrue(new_end_date.compareTo(original_end_date) == 0);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		} 
		
		driver.close();
	}

	// Zoom In Days
	@Given("I click the zoom in button the graph units are in days the start date is not the same as the end date")
	public void i_click_the_zoom_in_button_the_graph_units_are_in_days_the_start_date_is_not_the_same_as_the_end_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
	}

	@Then("the start date should increase by one day and the end date should decrease by a day")
	public void the_start_date_should_increase_by_one_day_and_the_end_date_should_decrease_by_a_day() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomIn = driver.findElement(By.id("zoomIn"));
			zoomIn.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(new_start_date.compareTo(original_start_date) == 1);
			assertTrue(original_end_date.compareTo(new_end_date) == 1);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		} 
		
		driver.close();
	}

	@Given("I click the zoom in button the graph units are in days the start date is equal to the end date")
	public void i_click_the_zoom_in_button_the_graph_units_are_in_days_the_start_date_is_equal_to_the_end_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Set the earliest start date
		driver.findElement(By.id("graphStartDate")).clear();
		driver.findElement(By.id("graphStartDate")).sendKeys(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				
	}

	@Then("the start date and end date should be equal")
	public void the_start_date_and_end_date_should_be_equal() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomIn = driver.findElement(By.id("zoomIn"));
			zoomIn.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(new_start_date.compareTo(original_start_date) == 1);
			assertTrue(original_end_date.compareTo(new_end_date) == 1);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		} 
		
		driver.close();
	}

	// Zoom In Weeks
	@Given("I click the zoom in button the graph units are in weeks the start date is not the same as the end date")
	public void i_click_the_zoom_in_button_the_graph_units_are_in_weeks_the_start_date_is_not_the_same_as_the_end_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Then Select Weeks
		Select graphUnit = new Select(driver.findElement(By.id("graphUnits")));
		graphUnit.selectByValue("weeks");
	}

	@Then("the start date should increase by one day and the end date should decrease by one day")
	public void the_start_date_should_increase_by_one_day_and_the_end_date_should_decrease_by_one_day() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomIn = driver.findElement(By.id("zoomIn"));
			zoomIn.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(new_start_date.compareTo(original_start_date) == 1);
			assertTrue(original_end_date.compareTo(new_end_date) == 1);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		} 
		
		driver.close();
	}

	@Given("I click the zoom in button the graph units are in weeks the start date is equal to the end date")
	public void i_click_the_zoom_in_button_the_graph_units_are_in_weeks_the_start_date_is_equal_to_the_end_date() {
		driver.get(ROOT_URL);  
		//login first
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("login-username")).sendKeys("test_user");
		driver.findElement(By.id("login-password")).sendKeys("test_password");
		WebElement loginButton = driver.findElement(By.id("login-submit"));
		loginButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Set the earliest start date
		driver.findElement(By.id("graphStartDate")).clear();
		driver.findElement(By.id("graphStartDate")).sendKeys(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
			
		// * Then Select Weeks
		Select graphUnit = new Select(driver.findElement(By.id("graphUnits")));
		graphUnit.selectByValue("weeks");
	}

	@Then("the start date and end date should stay the same")
	public void the_start_date_and_end_date_should_stay_the_same() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
		
		// * Get updated dates
		WebElement graphStartDate = driver.findElement(By.id("graphStartDate"));
		WebElement graphEndDate = driver.findElement(By.id("graphEndDate"));
		try {
			Date original_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date original_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			WebElement zoomIn = driver.findElement(By.id("zoomIn"));
			zoomIn.click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("graphStartDate")));
			
			System.out.println("Finding new dates");
			graphStartDate = driver.findElement(By.id("graphStartDate"));
			graphEndDate = driver.findElement(By.id("graphEndDate"));
			
			System.out.println("Updated graph start date: " + graphStartDate.getAttribute("value"));
			System.out.println("Updated graph end date: " + graphEndDate.getAttribute("value"));
			
			// * Check that the new dates are updated
			Date new_start_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphStartDate.getAttribute("value"));
			Date new_end_date = new SimpleDateFormat("MM/dd/yyyy").parse(graphEndDate.getAttribute("value"));
			
			assertTrue(new_start_date.compareTo(original_start_date) == 1);
			assertTrue(original_end_date.compareTo(new_end_date) == 1);
			
		} catch (ParseException e) {
			System.out.println("Failed to parse dates... Try again");
		} 
		
		driver.close();
	}
	
	@After()
	public void after() {
		driver.quit();
	}
}
