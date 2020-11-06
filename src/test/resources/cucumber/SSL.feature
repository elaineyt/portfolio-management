Feature: SSL
	
	Scenario: HTTPS Address
		Given I am on index.jsp
		Then the url has https
	
	Scenario: HTTP doesn't work
		Given I go to http://localhost:8443
		Then site can't be reached