package All_Task;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import helpers.TearDown;


public class TC_032_VerifyThatTheUserCanClickOnTaskInTheSideNavBar extends TearDown{
	
	    @Test
	    public void TC_032_VerifyThatTheUserCanClickOnTaskInTheSideNavBar() throws Exception {
	    	
	        test = extent.createTest("VerifyThatTheUserCanClickOnTaskInTheSideNavBar");
	        test.log(Status.INFO, "Browser launched successfully");
	        test.log(Status.INFO, "URL Opened successfully:- " + env.getProperty("ProdUrl"));
	        MediumFixedWait();
	        
	        click("xpath", Objects.getProperty("Use_Legacy_Login"), "Use Legacy Login");
	        NormalFixedWait();
	        enterText("name", Objects.getProperty("EmailAddress_Textbox"), "Email Address Textbox", env.getProperty("EmailAddress_TextboxData"));
	        NormalFixedWait();
	        enterText("xpath", Objects.getProperty("ChoosePassword_Textbox"), "Choose Password Textbox", env.getProperty("ChoosePassword_TextboxData"));
	        NormalFixedWait();
	        click("xpath", Objects.getProperty("Remember_Chekbox"), "Remember Checkbox");
	        NormalFixedWait();
	        click("xpath", Objects.getProperty("Sign_In"), "SignIn Button");
	        HighFixedWait();
	        
	        click("xpath", Objects.getProperty("Select_Workspace"), "Select Workspace");
	        NormalFixedWait();
	        click("xpath", Objects.getProperty("Open_Workspace"), "Open Workspace Button");
	        MediumFixedWait(); 
	        
	        
	        click("xpath", Objects.getProperty("Tasks_Click"), "Click The Tasks");
	        MediumFixedWait();
	        click("xpath", Objects.getProperty("Complete_Incomplete"), "Click Completed Or Incompleted");
	        MediumFixedWait();
	        click("xpath", Objects.getProperty("Close_Task"), "Close icon click");
	        MediumFixedWait();
	   
	        WebElement taskTitleElement = driver.findElement(By.xpath("(//div[text()='Task Title']/parent::div/following-sibling::div//div//div/span)[1]"));
	        String taskTitleText = taskTitleElement.getText();
	        MediumFixedWait();

	        
	        click("xpath", Objects.getProperty("Click_DateIcon"), "Click Date Icon");
	        NormalFixedWait();
	        click("xpath", Objects.getProperty("Select_Date"), "Select Date In DatePicker");
	        MediumFixedWait();
	        click("xpath", Objects.getProperty("Close_Task"), "Close icon click");
	        MediumFixedWait();
	        
	        click("xpath", Objects.getProperty("Assignee_Select"), "Click Assignee Icon");
	        NormalFixedWait();
	        click("xpath", Objects.getProperty("First_Assignee"), "Select First Assignee");
	        MediumFixedWait();
	        click("xpath", Objects.getProperty("Close_Task"), "Close icon click");
	        MediumFixedWait();
	        
	        click("xpath", Objects.getProperty("Priority_Click"), "Select Priority");
	        MediumFixedWait();
	        click("xpath", Objects.getProperty("First_Priority"), "Change First Priority");
	        MediumFixedWait();
	        
	        click("xpath", Objects.getProperty("All_Task"), "Click On All Task");
	        MediumFixedWait();
	        
	        driver.navigate().refresh();
	        MediumFixedWait();
	        
	        
	        isElementVisible("xpath","(//span[text()='"+taskTitleText+"']/parent::div)[2]" , "Task Name: "+taskTitleText+" is Visible");
	        
	  }



}
