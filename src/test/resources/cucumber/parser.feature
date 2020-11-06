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
	Scenario: Uploading a malformed file
      		Given I am on index.jsp
 		When I login
 		And I click upload stocks
    		And I upload a malformed file
    		Then it should say report Invalid ticker symbol.
