package org.alma.services.cashbaby.shop.api.services;

import java.util.List;
import org.alma.services.cashbaby.shop.api.entities.*;

public interface IOrderService {
	/** Gets alls items with basic informations
	 * 
	 * @return All items without details
	 */
	public List<IItem> getItems();
	
	/** Gets one item by its id
	 * 
	 * @param id The id of the item
	 * 
	 * @return The item with all details wanted
	 */
	public IItemDetailed getItem(String id);
	
	/** Reserves a quantity of an item and associate it to an order
	 * 
	 * @param orderId The id of the order. If {@code null}, generate a new order id;
	 * @param itemId The id of the wanted item
	 * @param quantity The quantity wanted of the item
	 * 
	 * @return The order id
	 * 
	 * @exception NotEnoughStockException If wanted quantity is higher than quantity in stock
	 */
	public String reserve(String orderId, String itemId, int quantity) throws NotEnoughStockException;
	
	/** Unreserves a quantity of an item
	 * 
	 * @param orderId The id of the order. If {@code null}, generate a new order id;
	 * @param itemId The id of the wanted item
	 * @param quantity The quantity wanted to restock of the item
	 * 
	 * @return {@code true} if action is correctly apply, {@code false} otherwise
	 */
	public boolean unreserve(String orderId, String itemId, int quantity);
	
	
	/** Create a new order id the order
	 * 
	 * @return The id of the new order
	 */
	public String startOrder();
	
	/** Valides the order
	 * 
	 * @param orderId The order to validate
	 * @param shippingAddress The address where ship the order
	 * 
	 * @return {@code true} if payment is validate, {@code false} otherwise 
	 */
	public boolean order(String orderId, String shippingAddress);
}
