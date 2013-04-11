package com.example.boilerbanker;

import java.io.Serializable;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
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
	
	public Transaction() {
		id = 0;
		location = null;
		amount = 0.0;
		date = null;
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
	
	public int getId(){
		return id;
	}
}
