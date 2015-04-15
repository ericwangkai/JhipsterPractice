package function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by tommy.wang on 4/9/2015.
 */
public class TestUtils {

    private final static Logger log = LoggerFactory.getLogger(TestUtils.class);

    public static void waitUntilPageLoaded(WebDriver driver){
        String previousUrl = driver.getCurrentUrl();
        System.out.println("Previous URL -> " + previousUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                System.out.println("Current URL -> " + d.getCurrentUrl());
                System.out.println("Current & Previous URL -> " + (d.getCurrentUrl() == previousUrl));
                return !previousUrl.equals(d.getCurrentUrl());
            }
        });
    }

    public static void waitUtilElementLoaded(){}
}
