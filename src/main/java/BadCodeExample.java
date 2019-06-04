import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe");
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
        String searchTerm = "Selenium";
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='r']/a/h3"));
        System.out.println("searchResults count: " + searchResults.size());

        for (WebElement searchResult : searchResults) {
            String strLinkText = searchResult.getText();
            if (strLinkText.contains(searchTerm)) {
                System.out.println("Title contains '" + searchTerm + "': " + strLinkText);
            } else
                System.out.println("searchTerm not found '" + searchTerm + "': " + strLinkText);

        }

    }

}
