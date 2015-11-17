package org.alma.services.cashbaby.bank;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
		CreditCard cc = new CreditCard("1234-1234-1234-1234","John Doe","11/15","123");
        System.out.println( "Hello World!" );
        System.out.println(cc);
    }
}
