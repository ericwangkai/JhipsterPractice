Feature: Create an ToDo Item
    Scenario: Create an ToDo Item with non-null title
        Given I have logged in as a user
        When I navigate to the ToDo management page
        Then I saw ToDo item input box on the page
        When I type in a ToDo item and hit Enter key
        Then I see an new ToDo item added into the ToDo List

