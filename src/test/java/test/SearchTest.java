package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultsPage;
import utils.GMailService;
import utils.Regexp;

import java.util.List;

public class SearchTest extends BaseTest {

    @BeforeMethod
    public void beforeMethodTwo() {
        System.out.println("2nd before method");
    }

    @Test
    public void basicSearchTest() {
        String userEmail = "xodylj@ukr.net",
                userPassword = "Kosmetista1990",
                searchTerm = "HR";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");
        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "SearchResults page is not loaded.");
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 10,
                "Search results count is wrong.");
        List<String> searchResults = searchResultsPage.getSearchResultsText();
        for (String searchResult : searchResults) {
            Assert.assertTrue(searchResult.contains(searchTerm),
                    "Search term: " + searchTerm + " not found in: \n" + searchResult);
        }
    }


}