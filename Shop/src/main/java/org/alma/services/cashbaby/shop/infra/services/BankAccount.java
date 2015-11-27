package org.alma.services.cashbaby.shop.infra.services;

import org.alma.services.cashbaby.bank.application.AccountService;
import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;
import org.alma.services.cashbaby.bank.domain.entities.*;


public class BankAccount {
	
	private AccountService accountService;
	
	public BankAccount() {
		this.accountService = new AccountService();
	}
	
	public Account getAccount(String accountNumber) throws UnknownAccountException {
		return this.accountService.getAccount(accountNumber);
	}
	
	public void registerAccount(IAccount account) {
		this.accountService.registerAccount(account);
	}
	
	public void registerCreditCard(ICreditCard creditCard,IAccount account) throws UnknownAccountException {
		this.accountService.registerCreditCard(creditCard,account);
	}
}
