Feature: Fibonacci

  Scenario: Get fibonacci sequence from web service
    Given a fibonacci service has been provided
    When I hit the fibonacci service
    Then I get back the expected sequence