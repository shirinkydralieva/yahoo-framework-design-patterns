package framework.listener;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import framework.ui.driver.WebDriverSingleton;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import util.ScreenshotUtil;

@Slf4j
public class ScreenshotOnFailureListener extends ReportPortalTestNGListener {

  @Override
  public void onTestFailure(ITestResult result) {
    String testName = result.getMethod().getMethodName();
    log.error("Test failed: {}", testName, result.getThrowable());
    String screenshotPath =
        ScreenshotUtil.takeScreenshot
            (WebDriverSingleton.getInstance().getDriver(), testName);
    File screenshotFile = new File(screenshotPath);
    log.error("RP_MESSAGE#FILE#{}#{}", screenshotFile.getAbsolutePath(), "Screenshot for " + testName);
  }

}
