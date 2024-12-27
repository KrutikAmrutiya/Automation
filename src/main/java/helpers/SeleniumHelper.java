package helpers;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;



import com.aventstack.extentreports.Status;

public class SeleniumHelper extends Capture {

    private String relativeFilePath;

	// Generic Click Method
    public void click(String locatorType, String locatorValue, String elementName) {
        WebElement element = findElement(locatorType, locatorValue);
        element.click();
        test.info("Clicked on " + elementName);
        logger.info("Clicked on " + elementName);
    }

    // Generic Enter Text Method
    public void enterText(String locatorType, String locatorValue, String elementName, String text) {
        WebElement element = findElement(locatorType, locatorValue);
        element.clear();
        element.sendKeys(text);
        test.info("Entered text '" + text + "' into " + elementName);
        logger.info("Entered text '" + text + "' into " + elementName);
    }

    // Wait and Click with Logging
    public void waitAndClick(String locatorType, String locatorValue, String elementName) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findBy(locatorType, locatorValue)));
        element.click();
        test.info("Waited and clicked on " + elementName);
        logger.info("Waited and clicked on " + elementName);
    }

    // Wait and Enter Text with Logging
    public void waitAndEnterText(String locatorType, String locatorValue, String elementName, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findBy(locatorType, locatorValue)));
        element.clear();
        element.sendKeys(text);
        test.info("Waited and entered text '" + text + "' into " + elementName);
        logger.info("Waited and entered text '" + text + "' into " + elementName);
    }

    // Image Upload
    public void uploadImage(String locatorType, String locatorValue, String filePath, String elementName) {
        WebElement element = findElement(locatorType, locatorValue);
        element.sendKeys(filePath);
        test.info("Uploaded file to " + elementName);
        logger.info("Uploaded file to " + elementName);
    }

    // File Upload Using Robot
    public void fileUploadWithRobot(String filePath) throws Exception {
        Robot rb = new Robot();
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        test.info("File uploaded using Robot");
        logger.info("File uploaded using Robot");
    }

    // Verify Element is Present
    public boolean isElementPresent(String locatorType, String locatorValue, String elementName) {
        try {
            findElement(locatorType, locatorValue);
            test.info("Element is present: " + elementName);
            logger.info("Element is present: " + elementName);
            return true;
        } catch (NoSuchElementException e) {
            test.warning("Element not present: " + elementName);
            logger.warn("Element not present: " + elementName);
            return false;
        }
    }

    // Verify Element is Visible
    public boolean isElementVisible(String locatorType, String locatorValue, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(findElement(locatorType, locatorValue)));
            boolean isVisible = element.isDisplayed();
            test.info("Element is visible: " + elementName);
            logger.info("Element is visible: " + elementName);
            return isVisible;
        } catch (Exception e) {
            test.warning("Element not visible: " + elementName);
            logger.warn("Element not visible: " + elementName);
            throw new AssertionError("Element not found: " + elementName, e);
        }
    }

    // Assert Element is Present
    public void assertElementPresent(String locatorType, String locatorValue, String elementName) {
        Assert.assertTrue(isElementPresent(locatorType, locatorValue, elementName), elementName + " is not present.");
        test.info("Verified presence of element: " + elementName);
        logger.info("Verified presence of element: " + elementName);
    }

    // Assert Element is Visible
    public void assertElementVisible(String locatorType, String locatorValue, String elementName) {
        Assert.assertTrue(isElementVisible(locatorType, locatorValue, elementName), elementName + " is not visible.");
        test.info("Verified visibility of element: " + elementName);
        logger.info("Verified visibility of element: " + elementName);
    }
    
    protected void selectTodayDate() throws Exception {
        // Fetch today's date in the format expected by the date picker
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        test.log(Status.INFO, "Today's date: " + todayDate);
        
        // Construct the XPath for today's date in the date picker
        String todayXpath = "//td[contains(@class, 'day') and text()='" + todayDate + "']"; // Modify 'day' to match your date picker structure
        
        // Locate and click on today's date
        WebElement todayElement = driver.findElement(By.xpath(todayXpath));
        if (todayElement != null && todayElement.isDisplayed()) {
            todayElement.click();
            test.log(Status.INFO, "Successfully selected today's date: " + todayDate);
        } else {
            throw new Exception("Unable to locate or select today's date in the date picker.");
        }
    }

    protected void changeProfilePicture(String string) throws Exception {
        // Get the absolute path of the file
        String absolutePath = new File(relativeFilePath).getAbsolutePath();

        // Log the file path
        test.log(Status.INFO, "Attempting to upload a new profile picture from: " + absolutePath);

        // Find the <input type="file"> element (adjust locator to match your system)
        WebElement fileInput = driver.findElement(By.xpath(Objects.getProperty("Profile_Picture_Input")));

        // Send the absolute file path to the <input> element
        fileInput.sendKeys(absolutePath);

        // Add a wait to simulate file upload processing
        Thread.sleep(5000);
    }
    
    // Helper Methods
    private WebElement findElement(String locatorType, String locatorValue) {
        return driver.findElement(findBy(locatorType, locatorValue));
    }

    private By findBy(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }
}