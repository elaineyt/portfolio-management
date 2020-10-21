Feature: Checking the parser for CSV
  Scenario: Invalid date
    Given I load the test file
    When I enter an invalid date into the search box
    Then It should change it to the current date
    
  Scenario: Invalid amount
	Given I load the test file
	When I enter an invalid amount
	Then it should change it to one stock
	
  Scenario: Normal values
	Given I load the test file
	When I enter normal values seperated by commas
	Then it should parse all three values
