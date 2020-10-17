Feature: Main Page
  Scenario: Color of banner
	Given I am on temps main page
	When I check the banner color
	Then the banner color should be red

  Scenario: Banner title
	Given A am on temps main page
	When I check the banner title
	Then the String should be “USC CS310 Stock Portfolio Management”

  Scenario: Log out of account
	Given E am on temps main page
	When I click log out button in the top right
	Then the page should have a log in button

  Scenario: Check add stock options
	Given B am on temps main page
	When I click on the add stock popup
	Then I should see all cases in the addStock function

  Scenario: add specific stock ticker
	Given D am on temps main page
	When I click the add stock popup
	And I search RYN
	And I click add RYN
	Then RYN stock should be in my portfolio

  Scenario: Delete stock
	Given C am on temps main page
	When I click on stock RYN to remove
	And I click delete RYN stock
	Then “RYN” should no longer be in my portfolio
