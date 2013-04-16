package com.example.boilerbanker;

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
		transactions = new Transaction[100];
		String fileName = "UserTransactions/" + userName + ".txt";
		File f = new File(fileName);
		
		double amount;
		String location;
		String date;
		int id;
		double garbage;
		boolean flag = true;
		int i;
		
		try {
			Scanner in = new Scanner(f);

			i = 0;
			
			while(in.hasNext()){
				transactions[i] = new Transaction();
				
				date = in.next();
				location = in.next();
				amount = in.nextDouble();
				
				if(flag){
					balance = in.nextDouble();
					flag = false;
					System.out.println("Balance: " + balance);
				}
				else{
					garbage = in.nextDouble(); //won't use this
				}
				
				transactions[i].setId(i);
				transactions[i].setAmount(amount);
				transactions[i].setLocation(location);
				transactions[i].setDate(date);
				
				//doesn't print this line
				System.out.println("Id: " + transactions[i].getId() + " date: " + transactions[i].getDate() + " location: " + transactions[i].getLocation() + " amount: " + transactions[i].getAmount());
				
				i++;
			}
			numTransactions = i;
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
