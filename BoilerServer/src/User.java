package com.example.boilerbanker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private Transaction[] transactions;
	private double balance;
	private int numTransactions;
	
	
	public User(String userName){
		this.userName = userName;
		transactions = new Transaction[5];
		String fileName = "UserTransactions/" + userName + ".txt";
		File f = new File(fileName);
		
		double amount;
		String location;
		String date;
		double bal;
		
		try {
			Scanner in = new Scanner(f);
			//throw away first two lines of file (aka descriptor lines)
			in.nextLine();
			in.nextLine();
			//balance = Double.parseDouble(in.nextLine());
			//numTransactions = Integer.parseInt(in.nextLine());
			int i;
			for(i = 0; in.hasNextLine(); i++){
				transactions[i] = new Transaction();
				date = in.next();
				location = in.next();
				amount = Double.parseDouble(in.next());
				bal = Double.parseDouble(in.next());
				
				transactions[i].setId(i);
				transactions[i].setAmount(amount);
				transactions[i].setLocation(location);
				transactions[i].setDate(date);
				transactions[i].setBalance(bal);
			}
			numTransactions = i;
			
			for(;i < 50; i++) {
				transactions[i] = new Transaction();
			}
			balance = transactions[0].getBalance();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
	
	public double getBalance(){
		return balance;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public Transaction[] getTransactions() {
		return transactions;
	}
	
	public int getNumTransactions() {
		return numTransactions;
	}
	
}
