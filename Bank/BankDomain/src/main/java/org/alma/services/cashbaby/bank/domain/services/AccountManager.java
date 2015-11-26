package org.alma.services.cashbaby.bank.domain.services;

import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;
import org.alma.services.cashbaby.bank.domain.entities.*;
import org.alma.services.cashbaby.bank.domain.factories.*;

public class AccountManager implements IAccountService {
	
	private IBank bank;
	
	public AccountManager() {
		this.bank = BankFactory.getInstance().makeBank();
	}
	
	
	public IAccount getAccount(String accountNumber) throws UnknownAccountException {
		IAccount account = this.bank.getAccount(accountNumber);
		if(account == null) {
			throw new UnknownAccountException();
		}
		return account;
	}
	
	public void registerAccount(IAccount account) {
		this.bank.putAccount(account);
	}
	
	public void registerCreditCard(ICreditCard creditCard,IAccount account) throws UnknownAccountException {
		this.bank.putCreditCard(creditCard,account);
	}
}
