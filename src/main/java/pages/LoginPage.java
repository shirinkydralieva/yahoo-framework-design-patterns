package pages;

import framework.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginPage extends AbstractPage {

  private static final By EMAIL_INPUT_LOCATOR
      = By.name("username");
  private static final By NEXT_BUTTON_LOCATOR =
      By.cssSelector("button[type='submit']");
  private static final By PASSWORD_INPUT_LOCATOR
      = By.name("password");

  public LoginPage typeEmail(String email){
    element(EMAIL_INPUT_LOCATOR).click();
    element(EMAIL_INPUT_LOCATOR).type(email);
    return this;
  }

  public LoginPage typePassword(String password){
    element(PASSWORD_INPUT_LOCATOR).click();
    element(PASSWORD_INPUT_LOCATOR).type(password);
    return this;
  }

  public LoginPage clickNextButton(){
    element(NEXT_BUTTON_LOCATOR).click();
    return this;
  }

}
