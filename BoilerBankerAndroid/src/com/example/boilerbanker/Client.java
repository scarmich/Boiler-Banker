package com.example.boilerbanker;

import java.io.*;
import java.util.Scanner;
import android.content.Context;

public class Client extends AbstractClient {

	//Will hold all 50 transactions received from Server
	private Transaction[] userTransactions;
	private int currentBalance;
	private String user;

	public Client(String host, int port) {
		super(host, port);
		
		user = "";
		currentBalance = 0;
		userTransactions = new Transaction[50];
	}
	
	protected void handleMessageFromServer(Object msg) {
		if (msg == null){
			System.out.println("Client Error: msg from Server empty");
			return;
		}
		
		userTransactions = (Transaction[]) msg;
		
		
		//Need Seth to finish Server to really tell how to read what he sends
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
		String filename = user + "transactions";
		FileOutputStream out;
		try{
			out = ApplicationContext.getMyApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
			out.write(currentBalance);
			String line;
			for(int i=0; i<5; i++){
				if(userTransactions[i] != null){
					line = userTransactions[i].getId() + " " + userTransactions[i].getDate() + " " + userTransactions[i].getLocation() + " " + userTransactions[i].getAmount();
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
		
		currentBalance = Integer.parseInt(in.nextLine());
		for(int i=0; i<5; i++){
			line = in.nextLine();
			if(line != null){
				tokens = line.split(delims);
				trans[i] = new Transaction(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]));
			}
		}
		return trans;
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
	
	//Provided for Testing purposes
	public void main(String args[]){
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
	}
}

