package org.alma.services.cashbaby.shop.infra.services;

import org.alma.services.cashbaby.bank.application.PaymentService;
import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;

public class BankPayment {
	private PaymentService paymentService;
	
	public BankPayment() {
		this.paymentService = new PaymentService();
	}
	
	public void pay(ICreditCard from, String toAccountNumber, double amount) 
			throws PaymentRefusedException,UnknownAccountException,UnknownCreditCardException {
		this.paymentService.pay(from,toAccountNumber,amount);
	}
}
