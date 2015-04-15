package function.todo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import function.BaseFunctionTest;
import function.account.pageobjects.SiteHomePage;
import function.todo.pageobjects.TodoPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/**
 * Created by tommy.wang on 4/14/2015.
 */
public class CreateToDoItem extends BaseFunctionTest{

    public CreateToDoItem(){
        super();
    }

    @Given("^I have logged in as a user$")
    public void I_have_logged_in_as_a_user() throws Throwable {
        loginAsUser();
    }

    @When("^I navigate to the ToDo management page$")
    public void I_navigate_to_the_ToDo_management_page() throws Throwable {
        PageFactory.initElements(this.webDriver, SiteHomePage.NavigatorPage.class);
        SiteHomePage.NavigatorPage.Entities_link.click();
        SiteHomePage.NavigatorPage.todo_link.click();
//        WebElement todo_link = this.webDriver.findElement(By.id("todo_link"));
//        Assert.assertNotNull(todo_link);
//        todo_link.click();
    }

    @Then("^I saw ToDo item input box on the page$")
    public void I_saw_ToDo_item_input_box_on_the_page() throws Throwable {
        PageFactory.initElements(this.webDriver, TodoPage.class);
        Assert.assertTrue(TodoPage.todo_inputbox.isDisplayed());
    }

    @When("^I type in a ToDo item and hit Enter key$")
    public void I_type_in_a_ToDo_item() throws Throwable {
        TodoPage.todo_inputbox.sendKeys("This is a test ToDo item.");
        TodoPage.todo_inputbox.sendKeys(Keys.RETURN);
    }

    @Then("^I see an new ToDo item added into the ToDo List$")
    public void I_see_an_new_ToDo_item_added_into_the_ToDo_List() throws Throwable {
//        WebElement TodoRow = this.webDriver.findElement(By.xpath("//table/tbody/tr/td[text() = 'This is a test ToDo item.']"));
        WebElement TodoRow = TodoPage.todo_item;
        Assert.assertNotNull(TodoRow);
        Assert.assertTrue(TodoRow.isDisplayed());
        Assert.assertTrue("".equals(TodoPage.todo_inputbox.getText()));
    }
}
