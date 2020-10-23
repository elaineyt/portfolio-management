Feature: 3 Login Attempt Limit for One Minute

	Scenario: Confirm after 3 failed login attempts in one minute that user is locked out from loggin in for one minute
		Given I am on index.jsp
		When I enter the username "test_user" and incorrect password "incorrect"
		And I click login
		When I enter the username "test_user" and incorrect password "incorrect"
		And I click login
		When I enter the username "test_user" and incorrect password "incorrect"
		And I click login
	    Then there should be the login error "Exceeded login attempts wait 1 minute."

