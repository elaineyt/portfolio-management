Feature: Graph

 	
 	Scenario: S&P index
 		Given I am on mainpage.jsp
 		Then S&P data is present
 		
 	Scenario: Uncheck S&P index	
 		Given I am on mainpage.jsp
 		When I toggle S&P data off
 		Then number of points decreases
 	
 		