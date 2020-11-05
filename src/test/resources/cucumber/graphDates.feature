Feature: Test Graph Start Date and End Date
  Scenario: Default end date should be the current date
    Given i am logged into the application
    Then the end date should be the current date
    
  Scenario: Start date cannot be after the end date
    Given i am logged into the website
    Then i cannot set the start date to be after the end date
    
  Scenario: Start date cannot be further than a year into the past
		Given i am logged into the web app
    Then i cannot set the start date to be further than a year into the past

  Scenario: If nothing is checked, start date should be three months in the past
    Given i am logged into the site
    Then the start date should be three months in the past
    
  Scenario: If anything is checked, the start date should be the earliest date in the portfolio
    Given i am logged in and at least one position exists and is checked
    Then the start date should be the earliest date in the portfolio