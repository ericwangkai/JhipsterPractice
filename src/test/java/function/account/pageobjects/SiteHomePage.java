package function.account.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by tommy.wang on 4/8/2015.
 */
public class SiteHomePage {

    public SiteHomePage() {

    }

    @FindBy(how = How.LINK_TEXT, using = "authenticate")
    public static WebElement authenticate_link;

    public static class NavigatorPage {

        @FindBy(how = How.LINK_TEXT, using = "Home")
        public static WebElement home_link;

        @FindBy(how = How.LINK_TEXT, using = "Entities")
        public static WebElement Entities_link;

        @FindBy(how = How.ID, using = "todo_link")
        public static WebElement todo_link;

        @FindBy(how = How.LINK_TEXT, using = "Account")
        public static WebElement account_link;

        @FindBy(how = How.LINK_TEXT, using = "Authenticate")
        public static WebElement authenticate_link;

        @FindBy(how = How.LINK_TEXT, using = "Register")
        public static WebElement register_link;


    }
}
