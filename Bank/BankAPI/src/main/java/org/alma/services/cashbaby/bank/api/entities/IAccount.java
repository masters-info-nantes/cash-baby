package org.alma.services.cashbaby.bank.api.entities;

public interface IAccount {
	public String getAccountNumber();
	public String getOwnerName();
	public double getAmount();
	public void setAccountNumber(String accountNumber);
	public void setOwnerName(String ownerName);
	public void setAmount(double amount);
}
