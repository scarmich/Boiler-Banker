package com.example.boilerbanker;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PFCUDriver {
	
	private boolean credFlag = true;
	
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
        	credFlag = false;
        	driver.close();
        } else {
	        
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
	        
	        // Closes Driver
	        driver.close();
	        
	        // Save String to text file
	        FileWriter file = new FileWriter("transInfo.txt");
	        PrintWriter output = new PrintWriter(file);
	        output.println(htmlText);
	        output.close();
	        file.close();        
	        
	        parse(username);
        }
	}
	
	public boolean getFlag() {
		return credFlag;
	}

	public static void parse (String username) {
		String fileName = "transInfo.txt";
		File file = new File(fileName);
		String outFile = "UserTransactions/"+username+".txt";
		try {
		FileWriter fstream = new FileWriter(outFile);
		System.out.println("Opened");
		BufferedWriter out  = new BufferedWriter(fstream);
		Scanner read = new Scanner(file);
		while(read.hasNextLine()){
			String toAdd = read.nextLine();
			if(toAdd.contains("<table cellspacing=\"0\" border=\"1\" style=\"width:100%;border-collapse:collapse;\"")){
				toAdd = read.nextLine();
				toAdd = read.nextLine();
				toAdd = read.nextLine();
				toAdd = read.nextLine();
				while(!toAdd.contains("</tbody></table>")){
					if(!toAdd.contains("</tr>")){
						String parsedString = toAdd.substring(21);
						parsedString = parsedString.replaceAll("</td><td>", " ");
						parsedString = parsedString.replaceAll("</td><td align=\"right\">", " ");
						parsedString = parsedString.replaceAll("</td>", " ");
						parsedString = parsedString.replaceAll("N/A",  "");
						parsedString = parsedString.replaceAll("\\(", "");
						parsedString = parsedString.replaceAll("\\)", "");
						parsedString = parsedString.replaceAll("    ", "");
						//parsedString = parsedString.replaceAll("  [^ ]*", "");
						parsedString = parsedString.replaceAll("  ", " ");
						parsedString = parsedString.replaceAll("\\$", "");
						String []parsed = parsedString.split(" ");
						
						String date = parsed[0];
						String vendor = "";
						for (int i = 1; i < (parsed.length-3); i++) {
							vendor += parsed[i];
							if (i != (parsed.length-4)) {
								vendor += "_";
							}
						}
						String cost = parsed[(parsed.length-2)];
						String balance = parsed[(parsed.length-1)];
						parsedString = date + " " + vendor + " " + cost + " " + balance + "\n";
						out.write(parsedString);
						}
					toAdd = read.nextLine();
				}
				break;
			}
			
		}
		out.close();
		fstream.close();
		System.out.println("Finished parsing");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) throws IOException {
		new PFCUDriver("abravoset", "CS307project");
	}
}
