Feature: Checking the parser for CSV
  Scenario: Invalid date
    Given I load the test1 file
    Then It should change it to the current date
    
  Scenario: Invalid amount
	Given I load the test2 file
	Then it should change it to one stock
	
  Scenario: Normal values
	Given I load the test3 file
	Then it should parse all three values
