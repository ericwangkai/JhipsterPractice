package function.account;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import function.BaseFunctionTest;
import function.TestUtils;
import function.account.pageobjects.LoginPage;
import function.account.pageobjects.SiteHomePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import function.Hooks;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by tommy.wang on 4/8/2015.
 */
public class LoginAsAdminSuccessfully extends BaseFunctionTest{

    public LoginAsAdminSuccessfully() {
        super();
    }

    @Given("^I open site and click on <authenticate> link$")
    public void I_open_site_and_click_on_authenticate_link() throws Throwable {
        this.webDriver.get("http://localhost:9090/practice");

        PageFactory.initElements(this.webDriver, SiteHomePage.class);
        PageFactory.initElements(this.webDriver, LoginPage.class);

        SiteHomePage.authenticate_link.click();
    }

    @Then("^login page displayed$")
    public void login_page_displayed() throws Throwable {

        //TestUtils.waitUntilPageLoaded(this.webDriver);

        WebElement rememberMe = this.webDriver.findElement(By.id("rememberMe"));

        Assert.assertNotNull(rememberMe);
    }

    @When("^I enter login and password$")
    public void I_enter_login_and_password() throws Throwable {

        LoginPage.login.sendKeys("admin");
        LoginPage.password.sendKeys("admin");

    }

    @And("^I click on login button$")
    public void I_click_on_login_button() throws Throwable {

        LoginPage.authenticate_button.click();
    }

    @And("^I will directed to Welcome page$")
    public void I_will_directed_to_Welcome_page() throws Throwable {
        TestUtils.waitUntilPageLoaded(this.webDriver);
        Assert.assertTrue(this.webDriver.getPageSource().contains("You are logged in as user \"admin\""));
    }
}
