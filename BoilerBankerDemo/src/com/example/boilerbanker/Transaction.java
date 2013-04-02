package com.example.boilerbanker;

public class Transaction {
	//Where the transaction was done
	private String location;
	private String date;
	//How much was spent or received.  If negative then it was spent
	//If positive it was received.
	private int amount;
	
	public Transaction(String date, String loc, int amt){
		this.location = loc;
		this.amount = amt;
		this.date = date;
	}
	
	public String getLocation(){
		return location;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getDate() {
		return date;
	}
}
