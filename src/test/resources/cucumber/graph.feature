Feature: Graph
 	Scenario: Start date before end date
 		Given I enter a start date that is before my end date
 		Then the date error symbol should say "End date before start date"
 		
 	Scenario: Inactivity leads to logout
 		Given I am idle for one hundred and twenty seconds
 		Then I should be on index.jsp