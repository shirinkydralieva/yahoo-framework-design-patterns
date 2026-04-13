package regression;

import static bo.UserFactory.asUserWithSimplePermission;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import bo.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.InboxPage;
import pages.MailDialogPage;
import pages.MailFolderPage;
import service.LoginService;

@Test(groups = {"regression"})
public class YahooMailTest {

  private static final User user = asUserWithSimplePermission();
  private static final String EMAIL = user.getUsername();
  private static final String MAIL_SUBJECT = "Autotest check subject";
  private static final String MAIL_BODY = "Autotest check mail body";

  private InboxPage inboxPage;
  private MailDialogPage mailDialogPage;

  @BeforeClass
  public void setup(){
    new HomePage()
        .clickSignInButton()
        .switchToLastWindow();
  }

  @Test(priority = 1)
  public void shouldLogin(){
    inboxPage = new LoginService()
        .login(user);
    assertTrue(inboxPage.isPageLoaded());
  }

  @Test(priority = 2)
  public void shouldSaveNewMailInDraft(){
    mailDialogPage = new MailDialogPage()
        .clickComposeButton()
        .typeRecipient(EMAIL)
        .typeSubject(MAIL_SUBJECT)
        .typeBody(MAIL_BODY)
        .clickCloseButton();
    mailDialogPage.waitMailDialogToBeClosed();

    boolean isMailInDraftFolder = inboxPage
        .openDrafts()
        .isMailDisplayed(MAIL_SUBJECT);

    mailDialogPage = new MailFolderPage()
        .clickMailInFolderBySubject(MAIL_SUBJECT);

    mailDialogPage.waitMailDialogToBeOpened();

    assertTrue(isMailInDraftFolder);
    assertEquals(mailDialogPage.getRecipientText(EMAIL), EMAIL);
    assertEquals(mailDialogPage.getSubjectTitleValue(), MAIL_SUBJECT);
    assertEquals(mailDialogPage.getBodyText(), MAIL_BODY);
  }

  @Test(priority = 3)
  public void shouldSendMailAndBeInSentFolder(){
    mailDialogPage
        .clickSendButton();

    boolean isMailDisappearedFromDrafts =
        new MailFolderPage().isMailDisappeared(MAIL_SUBJECT);

    boolean isMailInSentFolder = inboxPage
        .openSent()
        .isMailDisplayed(MAIL_SUBJECT);

    assertTrue(isMailDisappearedFromDrafts);
    assertTrue(isMailInSentFolder);
  }

  @Test(priority = 4)
  public void shouldLogout() {
    new LoginService()
        .logout();
  }

}
