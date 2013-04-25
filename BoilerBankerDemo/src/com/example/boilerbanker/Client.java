package com.example.boilerbanker;

import java.io.*;
import java.util.Scanner;
import android.content.Context;

public class Client extends AbstractClient {

	//Will hold all 50 transactions received from Server
	private Transaction[] userTransactions;
	private double currentBalance;
	private String userName;
	private int numTransactions;
	
	private User user;

	public Client(String host, int port) throws IOException {
		super(host, port);
		
		userName = "";
		currentBalance = 0;
		//userTransactions = new Transaction[50];
		openConnection();
	}
	
	protected void handleMessageFromServer(Object msg) {
		//System.out.println("message received");
		if (msg == null){
			System.out.println("Client Error: msg from Server empty");
			return;
		}
		
		if (msg instanceof String) {
			System.out.println("FAILURE");
			MainActivity.changeCreds();
			MainActivity.changeWaiting();
		} else {
			
			user = (User) msg;
			userName = user.getUsername();
			currentBalance = user.getBalance();
			userTransactions = user.getTransactions();
			numTransactions = user.getNumTransactions();
			
			//System.out.println(userName + " " + currentBalance);
			for (int i = 0; i < 5; i++) {
				System.out.println(userTransactions[i].getDate() + " " + userTransactions[i].getLocation() + " " + userTransactions[i].getAmount());
			}
			MainActivity.changeWaiting();
			/*
			try {
				closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//setNewLastFiveTransactions();
		}
	}
	
	//Latest transactions first. 
	//Set up file to store the latest five. 
	private void setNewLastFiveTransactions() {
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
			System.exit(-1);
		}
	}
	
/*
	public Transaction[] getLastFiveTransactions(){
	
		String filename = userName + "transactions";
		File f = new File(ApplicationContext.getMyApplicationContext().getFilesDir(), filename);
		Scanner in = null;
		try{
			in = new Scanner(f);
		}catch(Exception e){
			System.out.println("Client Error: Couldn't open transaction file or Scanner for reading :" + e.toString());
		}
		Transaction[] trans = new Transaction[5];
		
		currentBalance = Integer.parseInt(in.nextLine());
		for(int i=0; i<5 && i<numTransactions && in.hasNextLine(); i++){
			trans[i] = new Transaction(Integer.parseInt(in.next()), in.next(), in.next(), Double.parseDouble(in.next()), Double.parseDouble(in.next()));
		}
		
		//In case the number of transactions is too small, use default constructor
		if (numTransactions < 5){
			for(int i=numTransactions; i<5; i++){
				trans[i] = new Transaction();
			}
		}
		return trans;
	} */
	
	public void sendUserCredentials(String user, String password) {
		String together = user + " " + password;
		handleMessageFromUI(together);
	}
	
	public void handleMessageFromUI(String message) {
		try {
		    sendToServer((Object)message);
		}
		catch(IOException e)  {
			System.out.println("Client Error: Could not send message to server : " + e.toString());
		    System.exit(-1);
		}
	}

	
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
	
	public User getUserData() {
		return user;
	}
	
	/*
	public String getUser(){
		return userName;
	}
	*/
	
	public Transaction[] getTransactions() {
		return userTransactions;
	}
	
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
	}
}

