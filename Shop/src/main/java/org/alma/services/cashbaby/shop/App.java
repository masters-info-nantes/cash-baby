package org.alma.services.cashbaby.shop;

import java.util.List;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;

import org.alma.services.cashbaby.bank.client.*;

public class App {
	public static void main( String[] args ) {
		System.out.println("Hello World!");
		
		Shop shop = new Shop();
		List<Item> items = shop.getItems();
		for(Item item : items) {
			//~ System.out.println(item.toString());
			System.out.println("  "+shop.getItem(item.getId()));
		}
		
	}	
}
