package smoke;

import static bo.UserFactory.asUserWithSimplePermission;
import static org.testng.Assert.assertTrue;

import bo.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.InboxPage;
import pages.MailDialogPage;
import service.LoginService;

@Test(groups = {"smoke"})
public class YahooMailSmokeTest {
  private static final User user = asUserWithSimplePermission();

  @BeforeClass
  public void setup() {
    new HomePage()
        .clickSignInButton()
        .switchToLastWindow();
  }

  @Test(priority = 1)
  public void shouldLogin(){
    InboxPage inboxPage = new LoginService()
        .login(user);
    assertTrue(inboxPage.isPageLoaded());
  }

  @Test(priority = 2)
  public void shouldOpenComposeDialog() {
    MailDialogPage mailDialogPage = new MailDialogPage()
        .clickComposeButton();
    assertTrue(mailDialogPage.isMailDialogDisplayed());
  }

  @Test(priority = 3)
  public void shouldLogout() {
    new LoginService()
        .logout();
  }
}

