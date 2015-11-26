package org.alma.services.cashbaby.bank.domain.entities;

public class CreditCard {
	private String number;
	private String ownerName;
	private String expirationDate;
	private String verificationCode;
	
	public CreditCard(String number, String ownerName, String expirationDate, String verificationCode) {
		this.number = number;
		this.ownerName = ownerName;
		this.expirationDate = expirationDate;
		this.verificationCode = verificationCode;
	}
	
	public CreditCard() {
		this(null,null,null,null);
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String getOwnerName() {
		return this.ownerName;
	}
	
	public String getExpirationDate() {
		return this.expirationDate;
	}
	
	public String getVerificationCode() {
		return this.verificationCode;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public String toString() {
		return "CreditCard("+this.number+","+this.ownerName+","+this.expirationDate+","+this.verificationCode+")";
	}
}
