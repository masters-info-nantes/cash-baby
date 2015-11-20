package org.alma.services.cashbaby.bank;

public class Bank {
	
	/** Apply a payment from a credit card to an account
	 *
	 * @param from The credit card of the client
	 * @param to The account that will receive the amount
	 * @param amount The amount of the transaction
	 * 
	 * @return {@code true} if the payment is done, {@code false} otherwise
	 */
	//~ public boolean pay(CreditCard from, String to, float amount, String label) {
	public boolean pay(String fromNumber, String fromOwnerName, String fromExpirationDate, String fromVerificationCode, String to, float amount, String label) {
		CreditCard from = new CreditCard(
			fromNumber,
			fromOwnerName,
			fromExpirationDate,
			fromVerificationCode
		);
		if(from.getNumber().equals("A")) {
			return false;
		} else {
			return true;
		}
	}
}
