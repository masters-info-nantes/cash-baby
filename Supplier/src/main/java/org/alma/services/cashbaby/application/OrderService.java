package org.alma.services.cashbaby.application;

import java.util.List;
import java.util.ArrayList;

import org.alma.services.cashbaby.api.entities.*;
import org.alma.services.cashbaby.api.services.*;
import org.alma.services.cashbaby.domain.entities.*;
import org.alma.services.cashbaby.domain.services.*;

public class OrderService {//implements IOrderService {
	
	private org.alma.services.cashbaby.domain.services.OrderService orderService;
	
	public OrderService() {
		this.orderService = new org.alma.services.cashbaby.domain.services.OrderService();
	}
	
	public List<Item> getItems() {
		List<Item> res = new ArrayList<Item>();
		for(IItem i : this.orderService.getItems()) {
			res.add((Item)i);
		}
		return res;
	}
	
	public ItemDetailed getItem(String id) {
		return (ItemDetailed)this.orderService.getItem(id);
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
