	import java.util.List;

	import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class PFCUDrive {


	    public static void main(String[] args) throws Exception {
	    	
	        // The Firefox driver supports javascript 
	        WebDriver driver = new FirefoxDriver();
	        
	        // Go to the Google Suggest home page
	        driver.get("https://homebanking.purduefed.com/OnlineBanking/Login.aspx");
	        
	        // Enter the query string "Cheese"
	        WebElement userField = driver.findElement(By.name("M$content$PCDZ$MC3DFOE$ctl00$LoginName"));
	        userField.sendKeys("abravoset");
	        
	        WebElement logButton = driver.findElement(By.name("M$content$PCDZ$MC3DFOE$ctl00$cmdContinue"));
	        logButton.click();
	        
	        WebElement passField = driver.findElement(By.name("M$content$PCDZ$MF3KFEF$ctl00$Password"));
	        passField.sendKeys("CS307project");
	        
	        WebElement passButton = driver.findElement(By.name("M$content$PCDZ$MF3KFEF$ctl00$cmdContinue"));
	        passButton.click();
	        
	        driver.navigate().to("https://homebanking.purduefed.com/OnlineBanking/ViewAccounts/AccountActivity.aspx");
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
