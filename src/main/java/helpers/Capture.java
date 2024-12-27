package helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.Status;

public class Capture extends Base {
	
	public void TakeScreenshot(String screenshotName) throws Exception {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());
		String fileName = System.getProperty("user.dir") + "//ScreenShots//" + screenshotName + "_" + timestamp
				+ ".png";
		FileUtils.copyFile(screenshotFile, new File(fileName));
		logger.info("Screenshot captured successfully: " + fileName);
		test.log(Status.INFO, "Screenshot captured successfully: " + fileName);
	}
}
