package org.alma.services.cashbaby.bank;

public class Bank {
	
	public boolean pay(String from, String to, float amount, String label) {
		if(from.equals("A")) {
			return false;
		} else {
			return true;
		}
	}
}
