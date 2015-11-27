package org.alma.services.cashbaby.domain.services;

import java.util.List;

import org.alma.services.cashbaby.api.entities.*;
import org.alma.services.cashbaby.api.services.*;
import org.alma.services.cashbaby.domain.entities.*;
import org.alma.services.cashbaby.domain.services.*;

public class OrderService implements IOrderService {
	
	private ISupplier supplier;
	
	public OrderService() {
		this.supplier = new Supplier();
	}
	
	public List<IItem> getItems() {
		return this.supplier.getItems();
	}
	
	public IItemDetailed getItem(String id) {
		return this.supplier.getItem(id);
	}
	
	public String reserve(String orderId, String itemId, int quantity) throws NotEnoughStockException {
		return this.supplier.reserve(orderId,itemId,quantity);
	}
	
	public boolean unreserve(String orderId, String itemId, int quantity) {
		return this.supplier.unreserve(orderId,itemId,quantity);
	}
	
	public String startOrder() {
		return this.supplier.startOrder();
	}
	
	public boolean order(String orderId, String shippingAddress) {
		return this.supplier.order(orderId,shippingAddress);
	}
}
