package function;

import function.account.pageobjects.LoginPage;
import function.account.pageobjects.SiteHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by tommy.wang on 4/14/2015.
 */
public class BaseFunctionTest {
    protected WebDriver webDriver;

    public BaseFunctionTest() {
        this.webDriver = Hooks.driver;
    }

    protected void loginAsUser(){
//        this.webDriver.get("http://localhost:9090/practice");
        this.webDriver.get("http://localhost:8080");

        PageFactory.initElements(this.webDriver, SiteHomePage.class);
        PageFactory.initElements(this.webDriver, LoginPage.class);

        SiteHomePage.authenticate_link.click();

        LoginPage.login.sendKeys("admin");
        LoginPage.password.sendKeys("admin");

        LoginPage.authenticate_button.click();
    }
}
