package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public class Base {
	public static WebDriver driver;
	public static Logger logger;
	public File file;
	public File file1;
	public File file2;
	public static Properties env;
	public static Properties Objects;
	public static Properties TestData;
	public FileInputStream fileInput;
	public FileInputStream fileInput1;
	public FileInputStream fileInput2;
	public static WebDriverWait wait;
	public static WebDriverWait wait1;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeMethod
	@Parameters("browser")
	public void MainSetUp(String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, 15000);
			PropertyConfigurator.configure("Log4j.properties");
			logger = Logger.getLogger("VeblocWorkflowAutomation");
			logger.info("Chrome Launched Successfully");
			file = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Env.properties");
			file1 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Objects.properties");
			file2 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//TestData.properties");

			fileInput = null;
			fileInput1 = null;
			fileInput2 = null;
			try {
				fileInput = new FileInputStream(file);
				fileInput1 = new FileInputStream(file1);
				fileInput2 = new FileInputStream(file2);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			env = new Properties();
			Objects = new Properties();
			TestData = new Properties();

			try {
				env.load(fileInput);
				Objects.load(fileInput1);
				TestData.load(fileInput2);

			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			logger.info("Browser window maximized successfully");
			driver.get(env.getProperty("Url"));
			logger.info("URL Opened successfully:- " + env.getProperty("Url"));

		} else if (browser.equalsIgnoreCase("ChromeHeadless")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--headless", "--disable-gpu");
			driver = new ChromeDriver(options);
			wait = new WebDriverWait(driver, 15000);
			PropertyConfigurator.configure("Log4j.properties");
			logger = Logger.getLogger("VeblocWorkflowAutomation");
			logger.info("Chrome Launched Successfully");
			file = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Env.properties");
			file1 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Objects.properties");
			file2 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//TestData.properties");

			fileInput = null;
			fileInput1 = null;
			fileInput2 = null;
			try {
				fileInput = new FileInputStream(file);
				fileInput1 = new FileInputStream(file1);
				fileInput2 = new FileInputStream(file2);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			env = new Properties();
			Objects = new Properties();
			TestData = new Properties();

			try {
				env.load(fileInput);
				Objects.load(fileInput1);
				TestData.load(fileInput2);

			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			logger.info("Browser window maximized successfully");
			driver.get(env.getProperty("Url"));
			logger.info("URL Opened successfully:- " + env.getProperty("Url"));

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, 15000);
			PropertyConfigurator.configure("Log4j.properties");
			logger = Logger.getLogger("VeblocWorkflowAutomation");
			logger.info("Chrome Launched Successfully");
			file = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Env.properties");
			file1 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Objects.properties");
			file2 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//TestData.properties");

			fileInput = null;
			fileInput1 = null;
			fileInput2 = null;
			try {
				fileInput = new FileInputStream(file);
				fileInput1 = new FileInputStream(file1);
				fileInput2 = new FileInputStream(file2);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			env = new Properties();
			Objects = new Properties();
			TestData = new Properties();

			try {
				env.load(fileInput);
				Objects.load(fileInput1);
				TestData.load(fileInput2);

			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			logger.info("Browser window maximized successfully");
			driver.get(env.getProperty("Url"));
			logger.info("URL Opened successfully:- " + env.getProperty("Url"));

		}

		else if (browser.equalsIgnoreCase("firefoxHeadless")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("start-maximized");
			options.addArguments("--headless", "--disable-gpu");
			driver = new FirefoxDriver(options);
			wait = new WebDriverWait(driver, 15000);
			PropertyConfigurator.configure("Log4j.properties");
			logger = Logger.getLogger("VeblocWorkflowAutomation");
			logger.info("Chrome Launched Successfully");
			file = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Env.properties");
			file1 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Objects.properties");
			file2 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//TestData.properties");

			fileInput = null;
			fileInput1 = null;
			fileInput2 = null;
			try {
				fileInput = new FileInputStream(file);
				fileInput1 = new FileInputStream(file1);
				fileInput2 = new FileInputStream(file2);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			env = new Properties();
			Objects = new Properties();
			TestData = new Properties();

			try {
				env.load(fileInput);
				Objects.load(fileInput1);
				TestData.load(fileInput2);

			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			logger.info("Browser window maximized successfully");
			driver.get(env.getProperty("Url"));
			logger.info("URL Opened successfully:- " + env.getProperty("Url"));
		}

		else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "//Drivers//msedgedriver.exe");
			driver = new EdgeDriver();
			wait = new WebDriverWait(driver, 15000);
			PropertyConfigurator.configure("Log4j.properties");
			logger = Logger.getLogger("VeblocWorkflowAutomation");
			logger.info("Chrome Launched Successfully");
			file = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Env.properties");
			file1 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//Objects.properties");
			file2 = new File(System.getProperty("user.dir") + "//src//main//java//Properties//TestData.properties");

			fileInput = null;
			fileInput1 = null;
			fileInput2 = null;
			try {
				fileInput = new FileInputStream(file);
				fileInput1 = new FileInputStream(file1);
				fileInput2 = new FileInputStream(file2);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			env = new Properties();
			Objects = new Properties();
			TestData = new Properties();

			try {
				env.load(fileInput);
				Objects.load(fileInput1);
				TestData.load(fileInput2);

			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			logger.info("Browser window maximized successfully");
			driver.get(env.getProperty("Url"));
			logger.info("URL Opened successfully:- " + env.getProperty("Url"));

		} else {
			throw new Exception("You have not choosen the Browser");
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result);
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			captureScreenshot(result);
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		driver.quit();
	}

	@AfterTest
	public void masterTearDown() {
		extent.flush();
	}

	@BeforeTest
	public void LoadExtent() {

		ExtentSparkReporter spark = new ExtentSparkReporter("Report/VeblocWorkflow.html");
		extent = new ExtentReports();
		extent.setSystemInfo("HostName", "Automation Team");
		extent.setSystemInfo("Environment Name", "Production");
		extent.attachReporter(spark);
	}

	private void captureScreenshot(ITestResult result) {
		try {
			String screenshotPath = takeScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath, "Screenshot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String takeScreenshot(String testName) throws IOException {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Path destPath = Paths.get(System.getProperty("user.dir"), "screenshots", testName + "_" + timestamp + ".png");
		Files.createDirectories(destPath.getParent());
		Files.move(screenshotFile.toPath(), destPath);
		return destPath.toString();
	}

}
