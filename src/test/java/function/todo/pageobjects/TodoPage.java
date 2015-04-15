package function.todo.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by tommy.wang on 4/14/2015.
 */
public class TodoPage {

    @FindBy(how = How.ID, using = "ToDoItem")
    public static WebElement todo_inputbox;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[text() = 'This is a test ToDo item.']")
    public static WebElement todo_item;
}
