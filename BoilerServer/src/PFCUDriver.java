import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.python.util.PythonInterpreter;


public class PFCUDriver {
	
	public PFCUDriver(String username, String password) throws IOException {
		// The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        
        // Go to the PFCU login page
        driver.get("https://homebanking.purduefed.com/OnlineBanking/Login.aspx");
        
        // Enter username and select continue
        WebElement userField = driver.findElement(By.name("M$content$PCDZ$MC3DFOE$ctl00$LoginName"));
        userField.sendKeys(username);
        WebElement logButton = driver.findElement(By.name("M$content$PCDZ$MC3DFOE$ctl00$cmdContinue"));
        logButton.click();
        
        //Enter the password and select login
        WebElement passField = driver.findElement(By.name("M$content$PCDZ$MF3KFEF$ctl00$Password"));
        passField.sendKeys(password);
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
        
        // Save String to text file
        FileWriter file = new FileWriter("transInfo.txt");
        PrintWriter output = new PrintWriter(file);
        output.println(htmlText);
        output.close();
        file.close();
        
        // NEEDED: PASS HTML TO PARSER
        PythonInterpreter pi = new PythonInterpreter();
        
        // Closes driver
        driver.close();

	}

	
	public static void main(String [] args) throws IOException {
		
	}
}
