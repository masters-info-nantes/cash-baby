package org.alma.services.cashbaby.bank.domain.services;

import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;
import org.alma.services.cashbaby.bank.domain.entities.*;
import org.alma.services.cashbaby.bank.domain.factories.*;

public class Payment implements IPaymentService {
	
	private IBank bank;
	
	public Payment() {
		this.bank = BankFactory.getInstance().makeBank();
	}
	
	public void pay(ICreditCard from, String toAccountNumber, double amount)
			throws PaymentRefusedException,UnknownAccountException,UnknownCreditCardException {
		IAccount fromAccount = this.bank.getAccount(from);
		IAccount toAccount = this.bank.getAccount(toAccountNumber);
		if(fromAccount == null) {
			throw new UnknownCreditCardException();
		}
		if(toAccount == null) {
			throw new UnknownAccountException();
		}
		if(toAccount.getAmount() < amount) {
			throw new PaymentRefusedException();
		}
		fromAccount.setAmount(fromAccount.getAmount()-amount);
		toAccount.setAmount(toAccount.getAmount()+amount);
	}
}
