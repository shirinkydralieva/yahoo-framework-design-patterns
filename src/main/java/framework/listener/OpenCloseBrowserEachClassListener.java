package framework.listener;

import static framework.properties.EnvironmentProperty.getEnv;

import framework.ui.driver.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.testng.IClassListener;
import org.testng.ITestClass;

@Slf4j
public class OpenCloseBrowserEachClassListener implements IClassListener {

  @Override
  public void onBeforeClass(ITestClass testClass) {
    log.info("Open browser");
    WebDriverSingleton.getInstance().getDriver().get(getEnv());
  }

  @Override
  public void onAfterClass(ITestClass testClass) {
    log.info("Close browser");
    WebDriverSingleton.getInstance().getDriver().quit();
    WebDriverSingleton.getInstance().cleanup();
  }

}
