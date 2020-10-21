Feature: Main Page
	Scenario: Color of banner
		Given I am on mainpage.jsp
		Then the banner color should be grey

	Scenario: Banner title
		Given I am on mainpage.jsp
		Then the banner should say "USC CS310 Stock Portfolio Management"
		
	Scenario: Log out of account
		Given I am on index.jsp
		When I login
		And I click the logout button
		Then I should be on index.jsp
		
	Scenario: Add stock Invalid stock
		Given I am on index.jsp
		When I login
		And I add an invalid stock
		Then the ticker symbol error should say "Invalid ticker symbol."
		
	Scenario: Add stock Duplicate stock
		Given I have added a new stock
		And I add a duplicate stock
		Then the duplicate error should say "Portfolio already contains this stock."

	Scenario: Add stock No specified number of shares
		Given I am on index.jsp
		When I login
		And I add a stock without share amount
		Then the share error should say "Please enter number of shares."

	Scenario: Add stock Number of shares is not an int
		Given I am on index.jsp
		When I login
		And I add a stock with a fractional share
		Then the share error should say "Please enter a whole number of shares."

	Scenario: Add stock Number of shares less than 1
		Given I am on index.jsp
		When I login
		And I add a stock with less than one share
		Then the share error should say "Number of shares must be greater than zero." 

	Scenario: Add stock Both buy and sell date empty
		Given I am on index.jsp
		And I login
		When I add a stock with empty dates
		Then the buy error should say "Purchase date is required."

	Scenario: Add stock Buy date empty
		Given I am on index.jsp
		And I login
		When I add a stock with empty buy date
		Then the buy error should say "Sold date without purchase date."

	Scenario: Add stock Sell date is before buy date
		Given I am on index.jsp
		And I login
		When I add a stock with incorrect dates
		Then the sell error should say "Sold date is prior to purchase date."
		
	Scenario: Add Valid Stock
		Given I am on index.jsp
		When I login
		And I add a valid stock
		Then the stock should be added to my portfolio
		
	Scenario: Delete Stock
		Given I have added the stock
		And I delete a stock
		Then the stock should be removed from my portfolio
		
	

		
	