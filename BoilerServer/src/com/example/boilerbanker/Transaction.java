package com.example.boilerbanker;
//package com.example.boilerbanker;

import java.io.Serializable;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
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
	
	public Transaction() {
		id = 0;
		location = "";
		amount = 0.0;
		date = "";
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
	
	public int getId(){
		return id;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
}
