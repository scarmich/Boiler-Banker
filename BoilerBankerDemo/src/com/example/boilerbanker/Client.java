package com.example.boilerbanker;

import java.io.*;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;

public class Client extends AbstractClient {

	//Will hold five latest Transactions, from oldest to newest (0-4)
	private Transaction[] userTransactions;
	private int currentBalance;
	private String user;

	public Client(String host, int port, String user) {
		super(host, port);
		this.user = user;
		currentBalance = 0;
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
		//File f = new File(context.getFilesDir(), "transactions.txt");
		String filename = user + "transactions";
		FileOutputStream out;
		try{
			out = ApplicationContext.getMyApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
			out.write(currentBalance);
			String line;
			for(int i=0; i<5; i++){
				if(userTransactions[i] != null){
					line = userTransactions[i].getDate() + " " + userTransactions[i].getLocation() + " " + userTransactions[i].getAmount();
					out.write(line.getBytes());
				}
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
	
	//Not finished. 
	public Transaction[] getLastFiveTransactions(){
		String filename = user + "transactions";
		File f = new File(ApplicationContext.getMyApplicationContext().getFilesDir(), filename);
		Scanner in = null;
		try{
			in = new Scanner(f);
		}catch(Exception e){
			System.out.println("Client Error: Couldn't open transaction file or Scanner for reading :" + e.toString());
		}
		Transaction[] trans = new Transaction[5];
		String line;
		String delims = "[ ]";
		String[] tokens;
		
		currentBalance = in.nextInt();
		for(int i=0; i<5; i++){
			line = in.nextLine();
			tokens = line.split(delims);
			trans[i] = new Transaction(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
		}
		return trans;
	}
	
	public void handleMessageFromClientUI(String message)
			{
			  try
			  {
			    sendToServer(message);
			  }
			  catch(IOException e)
			  {
			    System.out.println("Client Error: Could not send message to server : " + e.toString());
			    System.exit(-1);
			  }
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
	
	public String getUser(){
		return user;
	}
	
	public Transaction[] getTransactions() {
		return userTransactions;
	}
}

