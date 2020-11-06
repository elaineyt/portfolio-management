Feature: Test Portfolio Select All and Deselect All changes the graph
  Scenario: Select All Changes the total portfolio value
    Given portfolio select all is clicked
    Then the total portfolio value is greater than zero

  Scenario: Select All Changes the total portfolio line
    Given the portfolio select all is clicked
    Then the total portfolio value is greater than zero
        
  Scenario: Deselect All sets the total portfolio value to zero
    Given portfolio deselect all is clicked
    Then the total portfolio value is equal to zero
    
  Scenario: Deselect All removes positions from total portfolio line
    Given the portfolio deselect all is clicked
    Then all positions are removed from the total portfolio line
 