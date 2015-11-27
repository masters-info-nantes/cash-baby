package org.alma.services.cashbaby.bank.domain.entities;

import java.util.HashMap;
import java.util.Map;
import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.domain.services.*;

public class Bank implements IBank {
	private static Map<String,IAccount> accounts;
	private static Map<String,ICreditCard> creditCards;
	private static Map<String,IAccount> accountOfCreditCard;
	
	static {
		Bank.accounts = new HashMap<String,IAccount>();
		Bank.creditCards = new HashMap<String,ICreditCard>();
		Bank.accountOfCreditCard = new HashMap<String,IAccount>();
		
		IAccount accountCashBaby = new Account(
			"1234123412341234",
			"Ca$hBaby",
			1000.0
		);
		IAccount accountJohnDoe = new Account(
			"6789678967896789",
			"John Doe",
			50.0
		);
		
		ICreditCard creditCardJohnDoe = new CreditCard(
			"1234-1234-1234-1234",
			"John Doe",
			"11/12",
			"123"
		);
		
		Bank.accounts.put(accountCashBaby.getAccountNumber(),accountCashBaby);
		Bank.accounts.put(accountJohnDoe.getAccountNumber(),accountJohnDoe);
		Bank.creditCards.put(creditCardJohnDoe.getNumber(),creditCardJohnDoe);
		Bank.accountOfCreditCard.put(creditCardJohnDoe.getNumber(),accountJohnDoe);
	}
	
	public Bank() {
	}
	
	public IAccount getAccount(String accountNumber) {
		return Bank.accounts.get(accountNumber);
	}
	
	public IAccount getAccount(ICreditCard creditCards) {
		return Bank.accountOfCreditCard.get(creditCards.getNumber());
	}
	
	public void putAccount(IAccount account) {
		Bank.accounts.put(account.getAccountNumber(),account);
	}
	
	public void putCreditCard(ICreditCard creditCard, IAccount linkedAccount) throws UnknownAccountException {
		if(Bank.accounts.containsKey(linkedAccount.getAccountNumber())) {
			Bank.creditCards.put(creditCard.getNumber(),creditCard);
			Bank.accountOfCreditCard.put(creditCard.getNumber(),linkedAccount);
		} else {
			throw new UnknownAccountException();
		}
	}
}
