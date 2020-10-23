Feature: 3 Login Attempt Limit for One Minute

	Scenario: Confirm after 3 failed login attempts in one minute that user is locked out from logging in
		Given I am on index.jsp
		When I enter the username "test_user3" and incorrect password "incorrect" 3 times
		And I click login
	    Then there should be the login error "Exceeded login attempts wait 1 minute."
	
	Scenario: After being locked out a user can't login again until waiting a minute
		Given I am on index.jsp
		When I enter the username "test_user3" and incorrect password "incorrect" 3 times
		And I wait a minute
		And I enter the username "test_user3" and password "test_user3"
		Then I am on mainpage.jsp
		