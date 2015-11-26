package org.alma.services.cashbaby.bank.application;

import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;
import org.alma.services.cashbaby.bank.domain.entities.*;
import org.alma.services.cashbaby.bank.domain.services.*;

public class AccountService {//implements IAccountService {
	
	private IAccountService accountService;
	
	public AccountService() {
		this.accountService = new AccountManager();
	}
	
	public Account getAccount(String accountNumber) throws UnknownAccountException {
		return (Account)this.accountService.getAccount(accountNumber);
	}
	
	public void registerAccount(IAccount account) {
		this.accountService.registerAccount(account);
	}
	
	public void registerCreditCard(ICreditCard creditCard,IAccount account) throws UnknownAccountException {
		this.accountService.registerCreditCard(creditCard,account);
	}
}
