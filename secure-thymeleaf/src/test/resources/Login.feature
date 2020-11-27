Feature: Login Functionality

  In order to do use the web application
  As a valid user
  I want to login successfully

  Scenario: Login

    Given I am in the login page of the web application
    When I enter valid credentials
      |user|password|
    Then I should be taken to the hello page

  Scenario Outline: Login Successful

    Given I am in the login page of the web application
    When I enter a valid <username> and <password>
    Then I should be taken to the hello page

    Examples:
      |username|password  |
      |"user"  |"password"|