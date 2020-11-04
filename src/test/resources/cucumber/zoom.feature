Feature: Zooming in and out of the graph
 	Scenario: Clicking zoom out makes the start date earlier and end date later
 		Given I click the zoom out button the graph units are in days the start date is not the furthest date in the past the end date is not todays date
 		Then the start date should decrease by one day and the end date should increase by a day
 		
 	Scenario: Clicking zoom out makes the start date earlier and end date the same
 		Given I click the zoom out button the graph units are in days the start date is not the furthest date in the past the end date is todays date
 		Then the start date should decrease by one day and the end date should stay the same
 		
 	Scenario: Clicking zoom out does not change the dates
 		Given I click the zoom out button the graph units are in days the start date is the furthest date in the past the end date is todays date
 		Then the start date and end date should remain the same
 	
 Scenario: Clicking zoom out makes the start date earlier and end date later in weeks
 		Given I click the zoom out button the graph units are in weeks the start date is not the furthest date in the past the end date is not todays date
 		Then the start date should decrease by one week and the end date should increase by one day
 		
 	Scenario: Clicking zoom out makes the start date earlier and end date the same in weeks
 		Given I click the zoom out button the graph units are in weeks the start date is not the furthest date in the past the end date is todays date
 		Then the start date should decrease by one week and the end date should stay the same
 		
 	Scenario: Clicking zoom out does not change the dates in weeks
 		Given I click the zoom out button the graph units are in weeks the start date is the furthest date in the past the end date is todays date
 		Then the start date and end date should be the same	
 		
 	Scenario: Clicking zoom in makes the start date later and end date earlier
 		Given I click the zoom in button the graph units are in days the start date is not the same as the end date
 		Then the start date should increase by one day and the end date should decrease by a day
 		
 	Scenario: Clicking zoom in does not change the dates
 		Given I click the zoom in button the graph units are in days the start date is equal to the end date
 		Then the start date and end date should be equal
 	
  Scenario: Clicking zoom in makes the start date later and end date earlier in weeks
 		Given I click the zoom in button the graph units are in weeks the start date is not the same as the end date
 		Then the start date should increase by one day and the end date should decrease by one day
 		
 	Scenario: Clicking zoom in does not change the dates in weeks
 		Given I click the zoom in button the graph units are in weeks the start date is equal to the end date
 		Then the start date and end date should stay the same
 	