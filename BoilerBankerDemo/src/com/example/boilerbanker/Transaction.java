package com.example.boilerbanker;

public class Transaction {
	//Where the transaction was done
	private String location;
	private String date;
	//How much was spent or received.  If negative then it was spent
	//If positive it was received.
	private double amount;
	private int id;
	
	public Transaction(int id, String date, String loc, double amt){
		this.id = id;
		this.location = loc;
		this.amount = amt;
		this.date = date;
	}
	
	public String getLocation(){
		return location;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getId() {
		return id;
	}
}
