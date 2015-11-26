package org.alma.services.cashbaby.bank.api.services;

import org.alma.services.cashbaby.bank.api.entities.*;

public interface IPaymentService {
	
	public void pay(ICreditCard from, String toAccountNumber, double amount)
		throws PaymentRefusedException,UnknownAccountException,UnknownCreditCardException;
}
