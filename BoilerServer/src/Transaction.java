package com.example.boilerbanker;

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
	private double balance;
	
	public Transaction(int id, String date, String loc, double amt, double balance){
		this.id = id;
		this.location = loc;
		this.amount = amt;
		this.date = date;
		this.balance = balance;
	}
	
	public Transaction() {
		id = 0;
		location = "";
		amount = 0.0;
		balance = 0.0;
		date = "";
	}
	
	public String getLocation(){
		return location;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getBalance() {
		return balance;
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
	
	public void setBalance(double bal){
		this.balance = bal;
	}
}
