Feature: а negative tests of the TO-DO List App

  @negative


  Scenario: check whether it is possible to add a task without text to the to-do list
    Given user is on the main page
    When user does not enter text and adds a new task
    Then a pop-up window will appear that says "You must write something!"
    When the user closes the pop-up window
    Then the to-do list will appear empty

  Scenario: very long task input
    Given user is on the main page
    When the user enters a task longer task
    Then this task is added to the to-do list
