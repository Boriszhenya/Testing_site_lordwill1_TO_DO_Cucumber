package com.example.steps;

import com.example.context.Context;
import com.example.pages.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.example.context.Context.scenario;
import static com.example.context.Context.wait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MySteps {
    /*эта строка ломает тест :)))*/
       /*String longString = "Are you going to Scarborough Fair?\n" +
            "Parsley, sage, rosemary and thyme\n" +
            "Remember me to one who lives there\n" +
            "She once was a true love of mine\n" +
            "Tell her to make me a cambric shirt\n" +
            "Parsley, sage, rosemary and thyme\n" +
            "Without no seams nor needlework\n" +
            "Then she′ll be a true love of mine\n" +
            "Tell her to find me an acre of land\n" +
            "Parsley, sage, rosemary and thyme\n" +
            "Between salt water and the sea sand\n" +
            "Then she'll be a true love of mine\n" +
            "Tell her to reap it with a sickle of leather\n" +
            "Parsley, sage, rosemary and thyme\n" +
            "And gather it all in a bunch of heather\n" +
            "Then she′ll be a true love of mine\n" +
            "Are you going to Scarborough Fair?\n" +
            "Parsley, sage, rosemary and thyme";*/
    String longString = "Системное тестирование означает тестирование всей " +
            "системы в целом, оно выполняется после интеграционного тестирования, " +
            "чтобы проверить, работает ли вся система целиком должным образом. " +
            "В основном это тестирование типа «черный ящик», которое оценивает работу " +
            "системы с точки зрения пользователя с помощью документа спецификации и оно " +
            "не требует каких-либо внутренних знаний о системе, таких как дизайн или структура кода.";

    @Given("user is on the main page")
    public void user_is_home_page() {
        scenario.log("Main page");
    }

    @When("a user adds the new task {string}")
    public void aUserAddsTheNewTask(String task) {
        new MainPage().addOneTask(task);
    }

    @Then("this entry {string} will appear on the To-Do list")
    public void thisEntryWillAppearOnTheToDoList(String task) {
        assertTrue(new MainPage().isTextTask(task));
    }


    @And("added {string} to the to-do list")
    public void addedToTheToDoList(String task) {
        new MainPage().addOneTask(task);
    }

    @When("the user deletes the task {string}")
    public void theUserDeletesTheTask(String task) {
        new MainPage().deleteOneTaskFromList(task);
    }

    @Then("the to-do list will be empty")
    public void theToDoListWillBeEmpty() {
        MainPage m = new MainPage();
        wait.until(ExpectedConditions.invisibilityOf(m.listAllAddTasks.getFirst()));
        assertTrue(m.listAllAddTasks.isEmpty());
    }

    @When("a user adds a task list to the to-do list")
    public void aUserAddsATaskListToTheToDoList(List<String> tasks) {
        for (String task : tasks) {
            new MainPage().addOneTask(task);
        }
    }

    @Then("are {int} tasks on the to-do list")
    public void areTasksOnTheToDoList(int size) {
        assertEquals(size, new MainPage().listAllAddTasks.size());
    }

    @And("user removes the {int}nd task from the to-do list")
    public void userRemovesTheNdTaskFromTheToDoList(int recordNumber) {
        new MainPage().deletingTaskByNumberInTheToDoList(recordNumber);
     //wait.until(ExpectedConditions.invisibilityOfElementLocated(new MainPage().listAllAddTasks.get(recordNumber)));
     }

    @Then("{int} tasks remain in the to-do list")
    public void tasksRemainInTheToDoList(int numberOfRecords) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(numberOfRecords, new MainPage().listAllAddTasks.size());
    }


    @When("the user clicks the button to go to the developer's github page")
    public void theUserClicksTheButtonToGoToTheDeveloperSGithubPage() {
        new MainPage().buttonGitHub.click();

    }

    @Then("the {string} page opens")
    public void thePageOpens(String address) {
        assertEquals(address, Context.getDriver().getCurrentUrl());
    }

    @When("user does not enter text and adds a new task")
    public void userDoesNotEnterTextAndAddsANewTask() {
        new MainPage().buttonAddTask.click();
    }

    @Then("a pop-up window will appear that says {string}")
    public void aPopUpWindowWillAppearThatSays(String message) {
        Alert alert = Context.getDriver().switchTo().alert();
        assertEquals(message, alert.getText());
    }

    @When("the user closes the pop-up window")
    public void theUserClosesThePopUpWindow() {
        Alert alert = Context.getDriver().switchTo().alert();
        alert.accept();
    }


    @Then("the to-do list will appear empty")
    public void theToDoListWillAppearEmpty() {
        assertTrue(new MainPage().listAllAddTasks.isEmpty());
    }


    @When("the user enters a task longer task")
    public void theUserEntersATaskLongerTask() {
        new MainPage().addOneTask(longString);
    }

    @Then("this task is added to the to-do list")
    public void thisTaskIsAddedToTheToDoList() {
        assertEquals(longString, new MainPage().listAllAddTasks.getFirst().getText());
    }


    @When("user selects theme {string}")
    public void userSelectsTheme(String theme) throws InterruptedException {
        String locator = "//div[@id='header']/div/div[contains(@class,'" + theme + "')]";
        Context.getDriver().findElement(By.xpath(locator)).click();
        Thread.sleep(350);
    }

    @Then("the new task input field has background-color {string}")
    public void theNewTaskInputFieldHasBackgroundColor(String background_color) {
        MainPage mainPage = new MainPage();
        String colorValue = mainPage.fieldAddTask.getCssValue("background-color");
        String hexColor = mainPage.convertToHex(colorValue);
        assertEquals(background_color, hexColor);
    }

    @And("user marks the {int}nd task from the to-do list")
    public void userMarksTheNdTaskFromTheToDoList(int recordNumber) {
        new MainPage().markingTaskByNumberInTheToDoList(recordNumber);
    }

    @Then("there's {int} marked task on the to-do list")
    public void thereSMarkedTaskOnTheToDoList(int numberMarkedTasks) {
        assertEquals(numberMarkedTasks, new MainPage().listCheckTasks.size());
    }
}

