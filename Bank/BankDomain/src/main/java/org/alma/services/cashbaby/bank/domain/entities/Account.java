package org.alma.services.cashbaby.bank.domain.entities;

public class Account {
	private String accountNumber;
	private String ownerName;
	private double amount;
	
	public Account(String accountNumber, String ownerName, double amount) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.amount = amount;
	}
	
	public Account() {
		this(null,null,0.0d);
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public String getOwnerName() {
		return this.ownerName;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString() {
		return "Account("+this.accountNumber+","+this.ownerName+","+this.amount+")";
	}
}
