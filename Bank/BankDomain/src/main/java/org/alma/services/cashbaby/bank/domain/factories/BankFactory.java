package org.alma.services.cashbaby.bank.domain.factories;

import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.domain.entities.Bank;

public class BankFactory {
	
	private static BankFactory bankFactory;
	
	private IBank bank;
	
	private BankFactory() {
	}
	
	public static BankFactory getInstance() {
		if(BankFactory.bankFactory == null) {
			BankFactory.bankFactory = new BankFactory();
		}
		return BankFactory.bankFactory;
	}
	
	public IBank makeBank() {
		if(this.bank == null) {
			this.bank = new Bank();
		}
		return this.bank;
	}
}
