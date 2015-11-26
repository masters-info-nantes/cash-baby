package org.alma.services.cashbaby.bank.application;

import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.api.services.*;
import org.alma.services.cashbaby.bank.domain.entities.*;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args ) throws Exception {
		AccountService as = new AccountService();
		IAccount accountCashBaby = new Account(
			"1234123412341234",
			"Ca$hBaby",
			1000.0
		);
		IAccount accountJohnDoe = new Account(
			"6789678967896789",
			"John Doe",
			50.0
		);
		
		ICreditCard creditCardJohnDoe = new CreditCard(
			"1234-1234-1234-1234",
			"John Doe",
			"11/12",
			"123"
		);
		
		as.registerAccount(accountCashBaby);
		as.registerAccount(accountJohnDoe);
		System.out.println("AccountService.registerAccount => ok");
		try {
			as.registerCreditCard(creditCardJohnDoe,accountJohnDoe);
			System.out.println("AccountService.registerCreditCard => ok");
		} catch(UnknownAccountException ex) {
			System.out.println("AccountService.registerCreditCard => UnknownAccountException");
		}
		try {
			as.getAccount("6789678967896789");
			System.out.println("AccountService.getAccount => ok");
		} catch(UnknownAccountException ex) {
			System.out.println("AccountService.getAccount => UnknownAccountException");
		}
		
		PaymentService ps = new PaymentService();
		try {
			ps.pay(
				creditCardJohnDoe,
				accountCashBaby.getAccountNumber(),
				10.5
			);
			System.out.println("PaymentService.pay => ok");
		} catch(PaymentRefusedException ex) {
			System.out.println("PaymentService.pay => PaymentRefusedException");
		} catch(UnknownAccountException ex) {
			System.out.println("PaymentService.pay => UnknownAccountException");
		} catch(UnknownCreditCardException ex) {
			System.out.println("PaymentService.pay => UnknownCreditCardException");
		}
		
		System.out.println( "Hello World!" );
	}
}
