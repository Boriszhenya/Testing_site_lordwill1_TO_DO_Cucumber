Feature: а test of the TO-DO List App

  @positive
  Scenario: adding an entry to the To-Do List
    Given user is on the main page
    When a user adds the new task "Hello world!"
    Then this entry "Hello world!" will appear on the To-Do list

  Scenario: deleting a task from the To-Do List
    Given user is on the main page
    And  added "Hello World" to the to-do list
    When the user deletes the task "Hello world!"
    Then the to-do list will be empty

  Scenario: Adding 3 tasks at a time to the to-do list
    Given user is on the main page
    When a user adds a task list to the to-do list
      | read a chapter of a programming book |
      | do two tasks in Java                 |
      | walk the dog                         |
    Then are 3 tasks on the to-do list

  Scenario: deleting 1 out of 3 tasks in the to-do list
    Given user is on the main page
    When a user adds a task list to the to-do list
      | read a chapter of a programming book |
      | do two tasks in Java                 |
      | walk the dog                         |
    And user the user removes the 2nd task from the to-do list
    Then 2 tasks remain in the to-do list

  Scenario: to verify the operation of the color mode of the site
    Given user is on the main page
    When user selects light mode
    Then the new task input field has background-color #AEB1B4
    When user selects gray mode
    Then the new task input field has background-color #181a1a
    When the user selects dark mode
    Then the new task input field has background-color #01394c

  Scenario: to check if it is possible to navigate to the githab page
    Given user is on the main page
    When the user clicks the button to go to the developer's githab page
    Then the "https://github.com/lordwill1/todo-list" page opens
    When the user clicks the Back button
    Then the user goes back to the "https://lordwill1.github.io/todo-list/" page.
