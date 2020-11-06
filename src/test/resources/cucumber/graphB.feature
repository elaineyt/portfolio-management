Feature: Graph (Part B)	
 	Scenario: Unit switches
 		Given the unit is in weeks
 		Then the number of graph points should increase if I switch to days
 		
 	Scenario: Checking portfolio list position
 		Given a position is unchecked
 		Then the total portfolio line graph should increase if I check a position
 		
 	Scenario: Unchecking portfolio list position
 		Given a position is checked
 		Then the total portfolio line graph should decrease if I uncheck a position
 		
 	Scenario: Deleting checked portfolio list position should change graph
 		Given a position is checked
 		Then the total portfolio line graph should decrease if I delete a position
