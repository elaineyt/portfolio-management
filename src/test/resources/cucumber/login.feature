#Feature: Login
#			
#	Scenario: Username is empty
#		Given I am on index.jsp
#		When I click login
#		Then there should be the login error "Please provide a username."
#
#	Scenario: Password is empty
#		Given I am on index.jsp
#		When I click login
#		When I enter the username "yer" and incorrect password ""
#		Then there should be the login error "Please provide a password."
#
#	Scenario: Redirect to MainPage upon successful login
#		Given I am on index.jsp
#		When I enter the username "test_user" and password "test_password"
#		And I click login
#		Then I am on MainPage.jsp
#	
#	Scenario: Color of banner
#		Given I am on index.jsp
#		When I check the banner color
#		
#	Scenario: Password hidden as it’s typed login tab
#		Given I am on index.jsp
#		When I enter a password "123"
#		Then the text should be hidden
#	
#	Scenario: Password hidden as it’s typed register tab
#		Given I am on index.jsp
#		When I click the register tab
#		And I enter a register password "123"
#		Then the text should be hidden
#	
#	Scenario: Confirm password is hidden as it’s typed register tab
#		Given I am on index.jsp
#		When I click the register tab
#		And I enter the confirm password "123"
#		Then the text should be hidden