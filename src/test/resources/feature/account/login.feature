Feature: Login
    Scenario: Login as admin successfully
        Given I open site and click on <authenticate> link
        Then login page displayed
        When I enter login and password
        And I click on login button
        Then I will directed to Welcome page
