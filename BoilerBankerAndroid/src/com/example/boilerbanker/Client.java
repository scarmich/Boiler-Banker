package com.example.boilerbanker;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client extends AbstractClient {

	//Will hold five latest Transactions, from oldest to newest (0-4)
	private Transaction[] fiveLastTransactions;
	private Transaction[] userTransactions;
	private int currentBalance;
	private String user;

	public Client(String host, int port) {
		super(host, port);
		user = "";
		currentBalance = 0;
		fiveLastTransactions = new Transaction[5];
		userTransactions = new Transaction[50];
	}
	
	protected void handleMessageFromServer(Object msg) {
		if (msg == null){
			System.out.println("Client Error: msg from Server empty");
			return;
		}
		
		//currentBalance = msg.currentBalance;
		//for(int i=0; msg.transactions[i] != null; i++){
		//	userTransactions = msg.transactions[i];
		//}
		
		setNewLastFiveTransactions();
	}
	
	//Dependent upon how Seth develops the data structure sent. 
	//Latest trans first or last. 
	//Set up file to store the last five. 
	private void setNewLastFiveTransactions() {
		File f = new File("transactions.txt");
		PrintWriter out = null;
		try{
			out = new PrintWriter(f);
		}catch(Exception e){
			System.out.println("Error: Couldn't open transaction file :" + e.toString());
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
		File f = new File("transactions.txt");
		Scanner in = null;
		try{
			in = new Scanner(f);
		}catch(Exception e){
			System.out.println("Error: Couldn't open transaction file :" + e.toString());
		}
		Transaction[] trans = new Transaction[5];
		String line;
		
		currentBalance = in.nextInt();
		for(int i=0; i<5; i++){
			line = in.nextLine();
			
		}
		return trans;
	}
	
	public void setCurrentBalance(int bal) {
		this.currentBalance = bal;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public int getCurrentBalance(){
		return currentBalance;
	}
	
	public String user(){
		return user;
	}
	
	public Transaction[] getTransactions() {
		return fiveLastTransactions;
	}
	
	public void printTransactions(){
		System.out.println("User: " + user);
		for(int i=0; i<5 || fiveLastTransactions[i] == null; i++){
			System.out.println(i + ": " + fiveLastTransactions[i]);
		}
		System.out.println("Balance: " + currentBalance);
	}
}

