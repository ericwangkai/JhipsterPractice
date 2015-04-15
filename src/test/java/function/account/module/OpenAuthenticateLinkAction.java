package function.account.module;

import function.account.pageobjects.LoginPage;
import function.account.pageobjects.SiteHomePage;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created by tommy.wang on 4/8/2015.
 */
public class OpenAuthenticateLinkAction {

    public static void Execute(WebDriver driver, HashMap<String, String> map){
        SiteHomePage.authenticate_link.click();

        LoginPage.login.sendKeys(map.get("login"));
        LoginPage.password.sendKeys(map.get("password"));

        LoginPage.authenticate_button.click();
    }
}
