	import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;


	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class PFCUDrive {


	    public static void main(String[] args) throws Exception {
	    	
	        // The Firefox driver supports javascript 
	        WebDriver driver = new FirefoxDriver();
	        
	        // Go to the PFCU login page
	        driver.get("https://homebanking.purduefed.com/OnlineBanking/Login.aspx");
	        
	        // Enter username and select continue
	        WebElement userField = driver.findElement(By.name("M$content$PCDZ$MC3DFOE$ctl00$LoginName"));
	        userField.sendKeys("abravoset");
	        WebElement logButton = driver.findElement(By.name("M$content$PCDZ$MC3DFOE$ctl00$cmdContinue"));
	        logButton.click();
	        
	        //Enter the password and select login
	        WebElement passField = driver.findElement(By.name("M$content$PCDZ$MF3KFEF$ctl00$Password"));
	        passField.sendKeys("CS307project");
	        WebElement passButton = driver.findElement(By.name("M$content$PCDZ$MF3KFEF$ctl00$cmdContinue"));
	        passButton.click();
	        
	        //Handler for security questions
	        if (!(driver.getCurrentUrl().equals("https://homebanking.purduefed.com/OnlineBanking/AccountSummary.aspx"))) {
	        	System.out.println("Issue occured");
	        	System.exit(0);
	        }
	        
	        //Once logged in, navigate to the account views
	        driver.navigate().to("https://homebanking.purduefed.com/OnlineBanking/ViewAccounts/AccountActivity.aspx");
	        
	        //Select the proper account
	        Select select = new Select(driver.findElement(By.name("M$content$PCDZ$M2SN6LC$ctl00$selectedAccount")));
	        select.selectByIndex(2);
	        
	        //Select past 90 days
	        WebElement timeSelect = driver.findElement(By.id("M_content_PCDZ_M2SN6LC_ctl00_ClearedDateChoices_1"));
	        timeSelect.click();
	        
	        //Click search
	        WebElement search = driver.findElement(By.name("M$content$PCDZ$M2SN6LC$ctl00$search"));
	        search.click();
	        
	        // Gets the page's HTML
	        String htmlText = driver.getPageSource();
	        // NEEDED: PASS HTML TO PARSER
	        
	        // Closes driver
	        driver.close();
	        
	        FileWriter file = new FileWriter("htmlText.txt");
	        PrintWriter output = new PrintWriter(file);
	        output.println(htmlText);
	        
	        
	        
	        // Sleep until the div we want is visible or 5 seconds is over
	        /*
	        long end = System.currentTimeMillis() + 5000;
	        while (System.currentTimeMillis() < end) {
	            WebElement resultsDiv = driver.findElement(By.className("gssb_e"));

	            // If results have been returned, the results are displayed in a drop down.
	            if (resultsDiv.isDisplayed()) {
	              break;
	            }
	        }

	        // And now list the suggestions
	        List<WebElement> allSuggestions = driver.findElements(By.xpath("//td[@class='gssb_a gbqfsf']"));
	        
	        for (WebElement suggestion : allSuggestions) {
	            System.out.println(suggestion.getText());
	        }
	        */
	     }

}
