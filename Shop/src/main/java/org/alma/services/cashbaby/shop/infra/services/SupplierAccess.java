package org.alma.services.cashbaby.shop.infra.services;

import java.util.List;
import org.alma.services.cashbaby.application.OrderService;
import org.alma.services.cashbaby.api.entities.*;
import org.alma.services.cashbaby.domain.entities.*;
import org.alma.services.cashbaby.domain.services.*;

public class SupplierAccess {
	
	private OrderService orderService;
	
	public SupplierAccess() {
		this.orderService = new OrderService();
	}
	
	public List<Item> getItems() {
		return this.orderService.getItems();
	}
	
	public ItemDetailed getItem(String id) {
		return this.orderService.getItem(id);
	}
	
	public String reserve(String orderId, String itemId, int quantity) throws NotEnoughStockException {
		return this.orderService.reserve(orderId,itemId,quantity);
	}
	
	public boolean unreserve(String orderId, String itemId, int quantity) {
		return this.orderService.unreserve(orderId,itemId,quantity);
	}
	
	public String startOrder() {
		return this.orderService.startOrder();
	}
	
	public boolean order(String orderId, String shippingAddress) {
		return this.orderService.order(orderId,shippingAddress);
	}
}
