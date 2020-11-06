Feature: Test Portfolio Select All and Deselect All changes the graph
  Scenario: Select All Changes increases the correct number of graph points
    Given historical select all is clicked
    Then the number of graph points increases by the correct number
    
  Scenario: Deselect All Changes decreses the correct number of graph points
    Given historical deselect all is clicked
    Then the number of graph points decreases by the correct number