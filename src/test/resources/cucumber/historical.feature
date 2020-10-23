Feature: Historical
 	Scenario: Add stock Invalid stock
 		Given I have added an invalid stock
 		Then the historical ticker symbol error should say "Invalid ticker symbol."

 	Scenario: Add stock Duplicate stock
 		Given I have added a new historical stock
 		And I add a historical duplicate stock
 		Then the historical duplicate error should say "Portfolio already contains this stock."

 	Scenario: Add stock No specified number of shares
 		Given I have added a stock without share amount
 		Then the historical share error should say "Please enter number of shares."

 	Scenario: Add stock Number of shares is not an int
 		Given I have added a stock with a fractional share
 		Then the historical share error should say "Please enter a whole number of shares."

 	Scenario: Add stock Number of shares less than 1
 		Given I have added a stock with less than one share
 		Then the historical share error should say "Number of shares must be greater than zero." 

 	Scenario: Add stock Both buy and sell date empty
 		Given I have added a stock with empty dates
 		Then the historical buy error should say "Purchase date is required."

 	Scenario: Add stock Buy date empty
 		Given I have added a stock with empty buy date
 		Then the historical buy error should say "Sold date without purchase date."

 	Scenario: Add stock Sell date is before buy date
 		Given I have added a stock with incorrect dates
 		Then the historical sell error should say "Sold date is prior to purchase date."

 	Scenario: Add Valid Stock
 		Given I added a valid historical stock
 		Then the historical stock should be added to my portfolio

 	Scenario: Delete Stock
 		Given I have added the historical stock
 		And I delete the historical stock
 		Then the historical stock should be removed from my portfolio