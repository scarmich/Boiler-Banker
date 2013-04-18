package com.example.boilerbanker;

import java.util.ArrayList;

public class Category {
	
	private ArrayList<Transaction> transactions;
	private String name;
	
	public Category(String name) {
		this.name = name;
		transactions = new ArrayList<Transaction>();
	}
	
	public void addTransaction(Transaction trans) {
		transactions.add(trans);
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return transactions.size();
	}
	
	public ArrayList<Transaction> getTrans() {
		return transactions;
	}
	
	public double getAmount() {
		double amount = 0;
		for(int i = 0; i < transactions.size(); i++) {
			amount += transactions.get(i).getAmount();
		}
		return amount;
	}

}
