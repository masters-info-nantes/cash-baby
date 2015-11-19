package org.alma.services.cashbaby.bank;

public class Bank {
	
	public boolean pay(CreditCard from, String to, float amount, String label) {
		if(from.getNumber().equals("A")) {
			return false;
		} else {
			return true;
		}
	}
}
