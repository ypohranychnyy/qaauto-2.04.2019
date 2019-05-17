package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;
import utils.GmailLoginPage;
import utils.GmailPage;

public class PasswordResetTest extends BaseTest {

    @Test
    public void resetPasswordTest() {
        String userEmail = "lnkdn.tst@gmail.com",
                userPassword = "testLink!";

        ResetPasswordPage resetPasswordPage = loginPage.resetPassword();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(), "Reset Password page is not loaded.");

        ResetPasswordLinkSentPage resetPasswordLinkSentPage = resetPasswordPage.submitUsername(userEmail);
        Assert.assertTrue(resetPasswordLinkSentPage.isPageLoaded(), "Reset Password Link Sent page is not loaded.");

        GmailLoginPage gmailLoginPage = resetPasswordLinkSentPage.redirectToGmailPage();
        Assert.assertTrue(gmailLoginPage.isPageLoaded(), "Gmail Login Page is not loaded.");

        gmailLoginPage.submitEmail(userEmail);
        GmailPage gmailPage = gmailLoginPage.submitPassword(userPassword);
        Assert.assertTrue(gmailPage.isPageLoaded(), "Gmail Inbox Page is not loaded.");

        EnterNewPasswordPage enterNewPasswordPage = gmailPage.goToResetLink();
        Assert.assertTrue(enterNewPasswordPage.isPageLoaded(), "LinkedIn New Password Page is not loaded.");

        ResetPasswordSuccessPage resetPasswordSuccessPage = enterNewPasswordPage.submitNewPassword(userPassword);
        Assert.assertTrue(resetPasswordSuccessPage.isPageLoaded(), "Reset Success Password Page is not loaded.");

        HomePage homePage = resetPasswordSuccessPage.goToHomepage();
        Assert.assertTrue(homePage.isPageLoaded(), "HomePage is not loaded.");
    }
}
