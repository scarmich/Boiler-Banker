//package com.example.boilerbanker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private Transaction[] transactions;
	private int numTransactions;
	private double balance;
	
	
	public User(String userName){
		this.userName = userName;
		transactions = new Transaction[5];
		String fileName = userName + ".txt";
		File f = new File(fileName);
		
		double amount;
		String location;
		String date;
		
		try {
			Scanner in = new Scanner(f);
			balance = Double.parseDouble(in.nextLine());
			numTransactions = Integer.parseInt(in.nextLine());
			for(int i = 0; i < numTransactions; i++){
				transactions[i] = new Transaction();
				amount = in.nextDouble();
				date = in.next();
				location = in.next();
				
				transactions[i].setId(i);
				transactions[i].setAmount(amount);
				transactions[i].setLocation(location);
				transactions[i].setDate(date);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
	
	public int getNumTransactions(){
		return numTransactions;
	}
	
}
