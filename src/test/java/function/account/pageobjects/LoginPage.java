package function.account.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by tommy.wang on 4/8/2015.
 */
public class LoginPage {

    @FindBy(how = How.ID, using = "username")
    public static WebElement login;

    @FindBy(how = How.ID, using = "password")
    public static WebElement password;

    @FindBy(how = How.CLASS_NAME, using = "btn-primary")
    public static WebElement authenticate_button;

    @FindBy(how = How.LINK_TEXT, using = "Register a new account")
    public static WebElement register_link;



}
