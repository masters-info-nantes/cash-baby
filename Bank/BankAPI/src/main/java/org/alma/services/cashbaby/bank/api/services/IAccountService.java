package org.alma.services.cashbaby.bank.domain.services;

import org.alma.services.cashbaby.bank.api.entities.*;

public interface IAccountService {
	
	public IAccount getAccount(String accountNumber) throws UnknownAccountException;
	public void registerAccount(IAccount account);
	public void registerCreditCard(ICreditCard creditCard,IAccount account) throws UnknownAccountException;
}
