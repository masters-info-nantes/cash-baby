package org.alma.services.cashbaby.bank.api.entities;

public interface IBank {
	public IAccount getAccount(String accountNumber);
	public IAccount getAccount(ICreditCard creditCards);	
	public void putAccount(IAccount account);
	public void putCreditCard(ICreditCard creditCard, IAccount linkedAccount) throws UnknownAccountException;
}
