package org.alma.services.cashbaby.bank.api.entities;

public interface ICreditCard {
	public String getNumber();
	public String getOwnerName();
	public String getExpirationDate();
	public String getVerificationCode();
	public void setNumber(String number);
	public void setOwnerName(String ownerName);
	public void setExpirationDate(String expirationDate);
	public void setVerificationCode(String verificationCode);
}
