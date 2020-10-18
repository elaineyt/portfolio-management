Feature: Authorization
	Scenario: Title of banner
		Given I am on index.jsp
		Then the banner should say "USC CS310 Stock Portfolio Management"

	Scenario: Navigating from Login to Register tab
		Given I am on index.jsp
		When I click the register tab
		Then the register tab should be active
	
	Scenario: Error message if username doesn't exist
		Given I am on index.jsp
		When I enter the username "lol" and password "test_password"
		And I click login
 		Then there should be the login error "Username doesn't exist."
 		
	Scenario: Error message if password is incorrect
		Given I am on index.jsp
		When I enter the username "test_user" and incorrect password "incorrect"
		Then there should be the login error "Incorrect password."
		
	Scenario: Error message if username already exists
		Given I am on index.jsp
		When I click the register tab
		When I enter the username "test_user" and password "password" twice
 		Then there should be the register error "This user already exists."
 		
 	Scenario: Error message if passwords don’t match
		Given I am on index.jsp
		When I click the register tab
		And I enter the username "new" and password "first" and password "second"
		Then there should be the register error "Your passwords did not match."
		
	Scenario: Error message if username is empty
		Given I am on index.jsp
		When I click the register tab
		And I enter the username "" and password "password" twice
		Then there should be the register error "Please provide a username."

	Scenario: Error message if password is empty
		Given I am on index.jsp
		When I click the register tab
		And I enter the username "new" and password "" and password ""
		Then there should be the register error "Please provide a password."

	Scenario: Error message if confirm password is empty
		Given I am on index.jsp
		When I click the register tab
		And I enter the username "new" and password "password" and password ""
		Then there should be the register error "Please confirm your password."
	
 	Scenario: User successful registration
		Given I am on index.jsp
		When I click the register tab
		And I enter the username "newuserrrrr" and password "brandnew" twice
		Then the login tab should be active
		
	Scenario: User clicks cancel button
		Given I am on index.jsp
		When I click the register tab
		And I enter the username "brand" and password "brandnew" without submitting
		And I click the cancel button
		Then the login tab should be active