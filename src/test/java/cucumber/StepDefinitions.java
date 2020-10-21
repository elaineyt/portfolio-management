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
import org.openqa.selenium.support.ui.WebDriverWait;

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


	


	@After()
	public void after() {
		driver.quit();
	}
}
