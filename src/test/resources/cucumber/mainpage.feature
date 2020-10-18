Feature: Main Page
	Scenario: Color of banner
		Given I am on mainpage.jsp
		Then the banner color should be grey

	Scenario: Banner title
		Given I am on mainpage.jsp
		Then the banner should say "USC CS310 Stock Portfolio Management"
		
	Scenario: Log out of account
		Given I am on index.jsp
		When I login
		And I click the logout button
		Then I should be on index.jsp
	

