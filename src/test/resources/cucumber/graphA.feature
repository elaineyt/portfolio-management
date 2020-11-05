Feature: GraphA
	Scenario: Total Portfolio Value
		Given I have logged in with no portfolio
		Then total portfolio value is zero
		
	Scenario: Percent Change 
		Given I have clicked cmg
		Then percent change value is greater than zero
		
	Scenario: Arrow
		Given the percent change value is above zero
		Then green arrow displays