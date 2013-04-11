package com.example.boilerbanker;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client extends AbstractClient {

	//Will hold five latest Transactions, from oldest to newest (0-4)
	private Transaction[] fiveLastTransactions;
	private Transaction[] userTransactions;
	private double currentBalance;
	private String userName;
	private int numTransactions;

	public Client(String host, int port) throws IOException {
		super(host, port);
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
		
		userName = "";
=======
		user = "";
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
		currentBalance = 0;
		fiveLastTransactions = new Transaction[5];
		userTransactions = new Transaction[50];
		openConnection();
	}
	
	protected void handleMessageFromServer(Object msg) {
		if (msg == null){
			System.out.println("Client Error: msg from Server empty");
			return;
		}
		
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
		User user = (User) msg;
		userName = user.getUsername();
		currentBalance = user.getBalance();
		userTransactions = user.getTransactions();
		numTransactions = user.getNumTransactions();
=======
		//currentBalance = msg.currentBalance;
		//for(int i=0; msg.transactions[i] != null; i++){
		//	userTransactions = msg.transactions[i];
		//}
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
		
		setNewLastFiveTransactions();
	}
	
	//Latest transactions first. 
	//Set up file to store the latest five. 
	private void setNewLastFiveTransactions() {
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
		String filename = userName + "transactions";
		FileOutputStream out;
		try{
			String line;
			out = ApplicationContext.getMyApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
			line = String.valueOf(currentBalance) + "\n";
			out.write(line.getBytes());
			for(int i=0; i<5 && i<numTransactions; i++){
				line = userTransactions[i].getId() + " " + userTransactions[i].getDate() + " " + userTransactions[i].getLocation() + " " + userTransactions[i].getAmount() + "\n";
				out.write(line.getBytes());
			}
			
			out.close();
		}catch(FileNotFoundException e){
			System.out.println("Client Error: Couldn't open transaction file : " + e.toString());
			System.exit(-1);
		}catch(IOException e){
			System.out.println("Client Error: Couldn't write files : " + e.toString());
=======
		File f = new File("transactions.txt");
		PrintWriter out = null;
		try{
			out = new PrintWriter(f);
		}catch(Exception e){
			System.out.println("Error: Couldn't open transaction file :" + e.toString());
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
			System.exit(-1);
		}
		
		out.println(currentBalance);
		for(int i=0; i<5; i++){
			if(userTransactions[i] != null){
				out.println(userTransactions[i].getDate() + " " + userTransactions[i].getLocation() + " " + userTransactions[i].getAmount());
			}
		}
		
		out.close();
	}
	
	//Not finished. 
	public Transaction[] getLastFiveTransactions(){
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
		String filename = userName + "transactions";
		File f = new File(ApplicationContext.getMyApplicationContext().getFilesDir(), filename);
=======
		File f = new File("transactions.txt");
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
		Scanner in = null;
		try{
			in = new Scanner(f);
		}catch(Exception e){
			System.out.println("Error: Couldn't open transaction file :" + e.toString());
		}
		Transaction[] trans = new Transaction[5];
		String line;
		
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
		currentBalance = Integer.parseInt(in.nextLine());
		for(int i=0; i<5 && i<numTransactions; i++){
			line = in.nextLine();
			if(line != null){
				tokens = line.split(delims);
				trans[i] = new Transaction(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]));
			}
		}
		
		//In case the number of transactions is too small, use default constructor
		if (numTransactions < 5){
			for(int i=numTransactions; i<5; i++){
				trans[i] = new Transaction();
			}
=======
		currentBalance = in.nextInt();
		for(int i=0; i<5; i++){
			line = in.nextLine();
			
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
		}
		
		return trans;
	}
	
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
	public void sendUserCredentials(String user, String password) {
		String together = user + " " + password;
		handleMessageFromUI(together);
	}
	
	public void handleMessageFromUI(String message) {
		try {
		    sendToServer(message.toCharArray());
		}
		catch(IOException e)  {
			System.out.println("Client Error: Could not send message to server : " + e.toString());
		    System.exit(-1);
		}
	}

	
=======
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
	public void setCurrentBalance(int bal) {
		this.currentBalance = bal;
	}
	
	public void setUser(String user) {
		this.userName = user;
	}
	
	/**
	 * 	 * Temp method for manually setting transactions with hard coded data
	 * @param trans Transaction array to set as the transactions
	 */
	public void setTransactions(Transaction[] trans) {
		userTransactions = trans;
	}
	
	public double getCurrentBalance(){
		return currentBalance;
	}
	
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
	public String getUser(){
		return userName;
=======
	public String user(){
		return user;
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
	}
	
	public Transaction[] getTransactions() {
		return fiveLastTransactions;
	}
	
<<<<<<< HEAD:BoilerBankerFix/src/com/example/boilerbanker/Client.java
	//Provided for Testing purposes
	public void main(String args[]){
		/*
		Client client = new Client("data.cs.purdue.edu", 5555);
		Transaction trans[] = new Transaction[50];
		System.out.println("Old:");
		for(int i=0;i < 50; i++){
			trans[i] = new Transaction(i, "4/4/2013", "Walmart"+i, i*10);
			System.out.println(trans[i].getId() + " " + trans[i].getDate() + " " + trans[i].getLocation() + " " + trans[i].getAmount());
		}

		int current = 200;
		client.setUser("test");
		client.setCurrentBalance(current);
		
		client.handleMessageFromServer((Object)trans);
		
		Transaction newTrans[] = new Transaction[5];
		newTrans = client.getLastFiveTransactions();
		System.out.println("New:");
		for(int i=0;i < 5; i++){
			System.out.println(newTrans[i].getId() + " " + newTrans[i].getDate() + " " + newTrans[i].getLocation() + " " + newTrans[i].getAmount());
		}
		*/
=======
	public void printTransactions(){
		System.out.println("User: " + user);
		for(int i=0; i<5 || fiveLastTransactions[i] == null; i++){
			System.out.println(i + ": " + fiveLastTransactions[i]);
		}
		System.out.println("Balance: " + currentBalance);
>>>>>>> parent of 48decf3... Merge branch 'master' of https://github.com/scarmich/Boiler-Banker:BoilerBankerAndroid/src/com/example/boilerbanker/Client.java
	}
}

