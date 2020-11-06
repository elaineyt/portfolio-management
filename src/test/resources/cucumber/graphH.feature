Feature: Graph H

	 	
 	Scenario: S&P index
 		Given I am on mainpage.jsp
 		Then S&P data is present
 		
 	Scenario: Uncheck S&P index	
 		Given I am on mainpage.jsp
 		Then I toggle S&P data off number of points decreases
 	
 		