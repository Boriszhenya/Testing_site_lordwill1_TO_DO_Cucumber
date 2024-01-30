package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {


    @FindBy(xpath = "//div[@id='form']/form/input")
    public WebElement fieldAddTask;

    @FindBy(xpath = "//div[@id='form']/form/button")
    public WebElement buttonAddTask;

    @FindBy(tagName = "svg")
    public WebElement buttonGitHub;

    @FindBy(xpath = "//div[@id='myUnOrdList']/ul/div/li")
    public List<WebElement> listAllAddTasks;

    @FindBy(xpath = "//button[contains(@class,'check-btn')]")
    public List<WebElement> listButtonsToMarkTaskCompletion;

    @FindBy(xpath = "//button[contains(@class,'delete-btn')]")
    public List<WebElement> listButtonsDeleteTasks;

    @FindBy(xpath = "//div[contains(@class,'completed')]/li")
    public List<WebElement> listCheckTasks;


    public void addOneTask(String newTask) {
        fieldAddTask.sendKeys(newTask);
        buttonAddTask.click();
    }

    public boolean isTextTask(String task) {
        for (WebElement oneTask : listAllAddTasks) {
            if (oneTask.getText().equals(task)) {
                return true;
            }
        }
        return false;
    }

    public void deleteOneTaskFromList(String task) {

        for (int i = 0; i < listAllAddTasks.size(); i++) {

            if (listAllAddTasks.get(i).getText().equals(task)) {
                listButtonsDeleteTasks.get(i).click();
            }
        }
        System.out.println(listAllAddTasks.size());
    }

    public void deletingTaskByNumberInTheToDoList(int recordNumber) {

        for (int i = 0; i < listAllAddTasks.size(); i++) {
            if (i == (recordNumber - 1)) {
                listButtonsDeleteTasks.get(i).click();
            }
        }
    }

    public void markingTaskByNumberInTheToDoList(int recordNumber) {

        for (int i = 0; i < listAllAddTasks.size(); i++) {
            if (i == (recordNumber - 1)) {
                listButtonsToMarkTaskCompletion.get(i).click();
            }
        }
    }


    public String convertToHex(String colorValue) {
        String[] rgbaValues = colorValue.replace("rgba(", "").replace(")", "").split(",");
        int red = Integer.parseInt(rgbaValues[0].trim());
        int green = Integer.parseInt(rgbaValues[1].trim());
        int blue = Integer.parseInt(rgbaValues[2].trim());

        return String.format("#%02x%02x%02x", red, green, blue);
    }

}