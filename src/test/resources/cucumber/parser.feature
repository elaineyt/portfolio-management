Feature: Parser
	Scenario: Upload button
		Given I am on index.jsp
 		When I login
 		And I click upload stocks
 		Then there should be a upload button
	Scenario: Close button
		Given I am on index.jsp
 		When I login
 		And I click upload stocks
 		Then there should be a close button
