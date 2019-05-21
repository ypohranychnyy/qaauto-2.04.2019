package page;

import org.openqa.selenium.WebDriver;
import utils.GMailService;

public abstract class BasePage {

    protected WebDriver driver;
    protected static GMailService gMailService = new GMailService();

    public abstract boolean isPageLoaded();


}
