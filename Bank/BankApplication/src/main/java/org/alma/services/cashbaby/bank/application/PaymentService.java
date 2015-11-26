package org.alma.services.cashbaby.bank.application;

import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;
import org.alma.services.cashbaby.bank.domain.services.*;

public class PaymentService implements IPaymentService {
	
	private IPaymentService payment;
	
	public PaymentService() {
		this.payment = new Payment();
	}
	
	public void pay(ICreditCard from, String toAccountNumber, double amount) 
			throws PaymentRefusedException,UnknownAccountException,UnknownCreditCardException {
		this.payment.pay(from,toAccountNumber,amount);
	}
}
