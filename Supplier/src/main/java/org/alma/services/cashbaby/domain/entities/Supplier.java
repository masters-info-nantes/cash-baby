package org.alma.services.cashbaby.domain.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import org.alma.services.cashbaby.api.entities.*;
import org.alma.services.cashbaby.api.services.*;
import org.alma.services.cashbaby.domain.services.*;

public class Supplier implements ISupplier {
	private static Map<String,Integer> stock;// itemId => quantity
	private static Map<String,IItemDetailed> items;// itemId => item
	private static Map<String,Order> orders;// orderId => order
	
	static {
		Supplier.stock = new HashMap<String,Integer>();
		Supplier.items = new HashMap<String,IItemDetailed>();
		Supplier.orders = new HashMap<String,Order>();
		
		ItemDetailed[] tmp = {
			new ItemDetailed(
				"001",
				"Dollar 1 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Onedolar2009series.jpg/250px-Onedolar2009series.jpg",
				1.0f,
				"Pack 5x$1 bill"
			),
			new ItemDetailed(
				"002",
				"Dollar 2 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/US_%242_obverse.jpg/250px-US_%242_obverse.jpg",
				1.5f,
				"Pack 5x$2 bill"
			),
			new ItemDetailed(
				"003",
				"Dollar 5 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/US_%245_Series_2006_obverse.jpg/250px-US_%245_Series_2006_obverse.jpg",
				2.0f,
				"Pack 5x$5 bill"
			),
			new ItemDetailed(
				"004",
				"Dollar 10 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/US10dollarbill-Series_2004A.jpg/250px-US10dollarbill-Series_2004A.jpg",
				3.0f,
				"Pack 5x$10 bill"
			),
			new ItemDetailed(
				"005",
				"Dollar 20 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/US20-front.jpg/250px-US20-front.jpg",
				5.0f,
				"Pack 5x$20 bill"
			),
			new ItemDetailed(
				"006",
				"Dollar 50 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/50_USD_Series_2004_Note_Front.jpg/250px-50_USD_Series_2004_Note_Front.jpg",
				10.0f,
				"Pack 5x$50 bill"
			),
			new ItemDetailed(
				"007",
				"Dollar 100 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/New100front.jpg/250px-New100front.jpg",
				20.0f,
				"Pack 5x$100 bill"
			),
			new ItemDetailed(
				"008",
				"Euro 5 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/EUR_5_obverse_%282013_issue%29.png/100px-EUR_5_obverse_%282013_issue%29.png",
				1.0f,
				"Pack 5x5€ bill"
			),
			new ItemDetailed(
				"009",
				"Euro 10 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/EUR_10_obverse_%282014_issue%29.png/100px-EUR_10_obverse_%282014_issue%29.png",
				1.5f,
				"Pack 5x10€ bill"
			),
			new ItemDetailed(
				"010",
				"Euro 20 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/The_Europa_series_20_%E2%82%AC_obverse_side.jpg/100px-The_Europa_series_20_%E2%82%AC_obverse_side.jpg",
				2.0f,
				"Pack 5x20€ bill"
			),
			new ItemDetailed(
				"011",
				"Euro 50 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/EUR_50_obverse_%282002_issue%29.jpg/100px-EUR_50_obverse_%282002_issue%29.jpg",
				3.0f,
				"Pack 5x50€ bill"
			),
			new ItemDetailed(
				"012",
				"Euro 100 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/EUR_100_obverse_%282002_issue%29.jpg/100px-EUR_100_obverse_%282002_issue%29.jpg",
				5.0f,
				"Pack 5x100€ bill"
			),
			new ItemDetailed(
				"013",
				"Euro 200 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/EUR_200_obverse_%282002_issue%29.jpg/100px-EUR_200_obverse_%282002_issue%29.jpg",
				10.0f,
				"Pack 5x200€ bill"
			),
			new ItemDetailed(
				"014",
				"Euro 500 x5",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/EUR_500_obverse_%282002_issue%29.jpg/100px-EUR_500_obverse_%282002_issue%29.jpg",
				20.0f,
				"Pack 5x500€ bill"
			)
		};
		
		for(ItemDetailed i : tmp) {
			Supplier.stock.put(i.getId(),100);
			Supplier.items.put(i.getId(),i);
		}
	}
	
	public List<IItem> getItems() {
		List<IItem> res = new ArrayList<IItem>();
		for(IItemDetailed item : this.items.values()) {
			res.add(
				new Item(
					item.getId(),
					item.getName(),
					item.getPhotoUrl(),
					item.getPrice()
				)
			);
		}
		return res;
	}
	
	public IItemDetailed getItem(String id) {
		return this.items.get(id);
	}
	
	public String reserve(String orderId, String itemId, int quantity) throws NotEnoughStockException {
		String currentOrderId;
		if(orderId == null) {
			currentOrderId = UUID.randomUUID().toString();
			Supplier.orders.put(currentOrderId,new Order());
		} else {
			currentOrderId = orderId;
		}
		
		int stockQuantity = Supplier.stock.get(itemId);
		if(stockQuantity >= quantity) {
			Supplier.stock.put(itemId,stockQuantity-quantity);
		} else {
			throw new NotEnoughStockException();
		}
		
		Supplier.orders.get(currentOrderId).put(itemId,quantity);
		
		return currentOrderId;
	}
	
	public boolean unreserve(String orderId, String itemId, int quantity) {
		Order order = Supplier.orders.get(orderId);
		if(order == null) {
			return false;
		}
		
		Integer orderedQuantity = order.get(itemId);
		if(orderedQuantity < quantity) {
			return false;
		}
		
		order.put(itemId,orderedQuantity-quantity);
		Supplier.stock.put(
			itemId,
			Supplier.stock.get(itemId)+quantity
		);
		
		return true;
	}
	
	public String startOrder() {
		String orderId = null;
		orderId = UUID.randomUUID().toString();
		Supplier.orders.put(orderId,new Order());
		return orderId;
	}
	
	public boolean order(String orderId, String shippingAddress) {
		Order order = Supplier.orders.get(orderId);
		if(order == null) {
			return false;
		}
		Supplier.orders.put(
			orderId,
			new Order.ClosedOrder(
				order,
				shippingAddress
			)
		);
		return true;
	}
	
}
