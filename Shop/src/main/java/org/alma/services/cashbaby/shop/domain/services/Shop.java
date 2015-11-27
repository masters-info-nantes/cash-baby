package org.alma.services.cashbaby.shop.api.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import org.alma.services.cashbaby.shop.infra.services.*;
import org.alma.services.cashbaby.shop.api.entities.*;
import org.alma.services.cashbaby.shop.api.services.*;
import org.alma.services.cashbaby.shop.domain.entities.*;
import org.alma.services.cashbaby.shop.domain.services.*;
import org.alma.services.cashbaby.bank.api.entities.*;
import org.alma.services.cashbaby.bank.domain.entities.*;

public class Shop {
	
	private static Map<String,Cart> orders;// orderId => order
	
	static {
		orders = new HashMap<String,Cart>();
	}
	
	private BankPayment bankPayment;
	private BankAccount bankAccount;
	private SupplierAccess supplierAccess;
	
	public Shop() {
		this.bankPayment = new BankPayment();
		this.bankAccount = new BankAccount();
		this.supplierAccess = new SupplierAccess();
	}
	
	/** Gets alls items with basic informations
	 * 
	 * @return All items without details
	 */
	public List<Item> getItems() {
		List<org.alma.services.cashbaby.domain.entities.Item> resService = this.supplierAccess.getItems();
		List<Item> res = new ArrayList<Item>();
		for(org.alma.services.cashbaby.domain.entities.Item i : resService) {
			res.add(
				new Item(
					i.getId(),
					i.getName(),
					i.getPhotoUrl(),
					i.getPrice()
				)
			);
		}
		return res;
	}
	
	/** Gets one item by its id
	 * 
	 * @param id The id of the item
	 * 
	 * @return The item with all details wanted
	 */
	public ItemDetailed getItem(String id) {
		org.alma.services.cashbaby.domain.entities.ItemDetailed resService = this.supplierAccess.getItem(id);
		return new ItemDetailed(
			resService.getId(),
			resService.getName(),
			resService.getPhotoUrl(),
			resService.getPrice(),
			resService.getDescription()
		);
	}

	/** Reserves a quantity of an item and associate it to an order
	 * 
	 * @param orderId The id of the order.
	 * @param itemId The id of the wanted item
	 * @param quantity The quantity wanted of the item
	 * 
	 * @return The order id
	 * 
	 * @exception NotEnoughStockException If wanted quantity is higher than quantity in stock
	 */
	public String reserve(String orderId, String itemId, int quantity) throws org.alma.services.cashbaby.api.entities.NotEnoughStockException {
		String resOrderId = this.supplierAccess.reserve(orderId,itemId,quantity);
		Cart cart = Shop.orders.get(resOrderId);
		Cart.Entry back = cart.get(itemId);
		if(back == null) {
			cart.put(
				new Cart.Entry(
					this.getItem(itemId),
					0
				)
			);
			back = cart.get(itemId);
		}
		back.setQuantity(back.getQuantity()+quantity);
		return resOrderId;
	}
	
	/** Unreserves a quantity of an item
	 * 
	 * @param orderId The id of the order. If {@code null}, generate a new order id;
	 * @param itemId The id of the wanted item
	 * @param quantity The quantity wanted to restock of the item
	 * 
	 * @return The order id
	 */
	public boolean unreserve(String orderId, String itemId, int quantity) {
		boolean resOrderId = this.supplierAccess.unreserve(orderId,itemId,quantity);
		if(resOrderId) {
			Cart cart = Shop.orders.get(resOrderId);
			Cart.Entry back = cart.get(itemId);
			if(back != null) {
				back.setQuantity(back.getQuantity()-quantity);
			}
		}
		return resOrderId;
	}
	
	/** Create a new order id the order
	 * 
	 * @return The id of the new order
	 */
	public String startOrder() {
		String orderId = this.supplierAccess.startOrder();
		Shop.orders.put(
			orderId,
			new Cart()
		);
		return orderId;
	}
	
	/** Valides the order
	 * 
	 * @param orderId The order to validate
	 * @param shippingAddress The address where ship the order
	 * @param card The card that will be use for pay
	 * 
	 * @return {@code true} if payment is validate, {@code false} otherwise 
	 */
	public boolean order(String orderId, String shippingAddress, CreditCard card) {
		this.supplierAccess.order(orderId,shippingAddress);
		Cart cart = this.getCart(orderId);
		double amount = 0.0d;
		for(Cart.Entry item : cart) {
			amount += new Double(item.getQuantity()).doubleValue()*item.getPrice();
		}
		try {
			this.bankPayment.pay(card,"1234123412341234",amount);
		} catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	/** Gets Cart from orderId
	 * 
	 * @param orderId The id of the wanted order
	 * 
	 * @return The Cart corresponding to the orderId
	 */
	public Cart getCart(String orderId) {
		return Shop.orders.get(orderId);
	}
}
