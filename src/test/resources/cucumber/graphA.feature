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
		
	Scenario: Checking an unchecked historical position should increase number of points on graph
		Given I have an unchecked position
		Then when I check the position the number of graph points increase 
	
	Scenario: Unchecking a checked historical position should decrease number of points on graph
		Given I have a checked position
		Then when I uncheck the position the number of graph points decrease


	