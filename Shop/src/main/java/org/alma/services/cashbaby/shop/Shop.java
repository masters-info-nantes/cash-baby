package org.alma.services.cashbaby.shop;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;

import org.alma.services.cashbaby.bank.client.*;
import org.alma.services.cashbaby.supplier.client.*;

public class Shop {
	
	private static Map<String,Basket> orders;// orderId => order
	
	static {
		orders = new HashMap<String,Basket>();
	}
	
	/*public List<Item> getItems() {
		ArrayList<Item> res = new ArrayList<Item>();
		try {
			SupplierStub stub = new SupplierStub("http://localhost:9763/services/Supplier/");
			
			SupplierStub.GetItems getItems = new SupplierStub.GetItems();
			SupplierStub.GetItemsResponse supplierRes = stub.getItems(getItems);
			SupplierStub.Item[] supplierResItems = supplierRes.get_return();
			
			for(SupplierStub.Item si : supplierResItems) {
				res.add(
					new Item(
						si.getId(),
						si.getName(),
						si.getPhotoUrl(),
						si.getPrice()
					)
				);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} finally {
			return res;
		}
	}*/
	
	/*public ItemDetailed getItem(String id) {
		ItemDetailed res = null;
		try {
			SupplierStub stub = new SupplierStub("http://localhost:9763/services/Supplier/");
			
			SupplierStub.GetItem getItem = new SupplierStub.GetItem();
			getItem.setId(id);
			SupplierStub.GetItemResponse supplierRes = stub.getItem(getItem);
			SupplierStub.ItemDetailed supplierResItem = supplierRes.get_return();
			
			res = new ItemDetailed(
				supplierResItem.getId(),
				supplierResItem.getName(),
				supplierResItem.getPhotoUrl(),
				supplierResItem.getPrice(),
				supplierResItem.getDescription()
			);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} finally {
			return res;
		}
	}*/
	
	/** Gets alls items with basic informations
	 * 
	 * @return All items without details
	 */
	public List<Item> getItems() {
		ArrayList<Item> res = new ArrayList<Item>();
		res.add(
			new Item(
				"001",
				"Dollar 1 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Onedolar2009series.jpg/250px-Onedolar2009series.jpg",
				1.0f
			)
		);
		res.add(
			new Item(
				"002",
				"Dollar 2 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/US_%242_obverse.jpg/250px-US_%242_obverse.jpg",
				1.5f
			)
		);
		return res;
	}
	
	/** Gets one item by its id
	 * 
	 * @param id The id of the item
	 * 
	 * @return The item with all details wanted
	 */
	public ItemDetailed getItem(String id) {
		ItemDetailed res = null;
		if(id.equals("001")) {
			res = new ItemDetailed(
				"001",
				"Dollar 1 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Onedolar2009series.jpg/250px-Onedolar2009series.jpg",
				1.0f,
				"Pack 5x$1 bill"
			);
		} else {
			res = new ItemDetailed(
				"002",
				"Dollar 2 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/US_%242_obverse.jpg/250px-US_%242_obverse.jpg",
				1.5f,
				"Pack 5x$2 bill"
			);
		}
		return res;
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
	public String reserve(String orderId, String itemId, int quantity) throws NotEnoughStockException {
		
		// TODO
		
		return orderId;
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
		boolean supplierUnreserve = true;
		// TODO
		
		if(supplierUnreserve) {
			Basket basket = Shop.orders.get(orderId);
			basket.put(
				this.getItem(itemId),
				basket.get(itemId)+quantity
			);
		}
		return true;
	}
	
	/** Create a new order id the order
	 * 
	 * @return The id of the new order
	 */
	public String startOrder() {
		String orderId = null;
		orderId = UUID.randomUUID().toString();
		// TODO
		
		Shop.orders.put(
			orderId,
			new Basket()
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
	//~ public boolean order(String orderId, String shippingAddress, CreditCard card) {
	public boolean order(String orderId, String shippingAddress, String cardNumber, String cardOwnerName, String cardExpirationDate, String cardVerificationCode) {
		
		// TODO
		
		return true;
	}
	
	/** Gets basket from orderId
	 * 
	 * @param orderId The id of the wanted order
	 * 
	 * @return The basket corresponding to the orderId
	 */
	public Basket getBasket(String orderId) {
		return Shop.orders.get(orderId);
	}
}
