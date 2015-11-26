package org.alma.services.cashbaby.bank.domain.entities;

import java.util.HashMap;
import java.util.Map;
import org.alma.services.cashbaby.bank.api.entities.*;

public class Bank implements IBank {
	private Map<String,IAccount> accounts;
	private Map<String,ICreditCard> creditCards;
	private Map<String,IAccount> accountOfCreditCard;
	
	public Bank() {
		this.accounts = new HashMap<String,IAccount>();
		this.creditCards = new HashMap<String,ICreditCard>();
		this.accountOfCreditCard = new HashMap<String,IAccount>();
	}
	
	public IAccount getAccount(String accountNumber) {
		return this.accounts.get(accountNumber);
	}
	
	public IAccount getAccount(ICreditCard creditCards) {
		return this.accountOfCreditCard.get(creditCards.getNumber());
	}
	
	public void putAccount(IAccount account) {
		this.accounts.put(account.getAccountNumber(),account);
	}
	
	public void putCreditCard(ICreditCard creditCard, IAccount linkedAccount) throws UnknownAccountException {
		if(this.accounts.containsKey(linkedAccount.getAccountNumber())) {
			this.creditCards.put(creditCard.getNumber(),creditCard);
			this.accountOfCreditCard.put(creditCard.getNumber(),linkedAccount);
		} else {
			throw new UnknownAccountException();
		}
	}
}
