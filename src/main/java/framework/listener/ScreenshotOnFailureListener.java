package framework.listener;

import framework.ui.driver.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.ScreenshotUtil;

@Slf4j
public class ScreenshotOnFailureListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    String testName = result.getMethod().getMethodName();
    log.error("Test failed: {}", testName, result.getThrowable());
    String screenshotPath =
        ScreenshotUtil.takeScreenshot
            (WebDriverSingleton.getInstance().getDriver(), testName);
    log.info("Screenshot saved for failed test {}: {}", testName, screenshotPath);
  }



}
