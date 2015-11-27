package org.alma.services.cashbaby.shop.api.entities;

import java.util.Map;
import java.util.HashMap;

public class Order extends HashMap<String,Integer> {
	
	public Order() {
		super();
	}
	
	public Order(Order order) {
		super(order);
	}
	
	public static class ClosedOrder extends Order {
		
		private String shippingAddress;
		
		public ClosedOrder(Order order,String shippingAddress) {
			super(order);
			this.shippingAddress = shippingAddress;
		}
		
		public String getShippingAddress() {
			return this.shippingAddress;
		}
		
		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}
		
		
		public Integer put(String key, Integer value) {
			throw new UnsupportedOperationException();
		}
		
		public void putAll(Map<? extends String,? extends Integer> m) {
			throw new UnsupportedOperationException();
		}
		
		public Integer remove(Object key) {
			throw new UnsupportedOperationException();
		}
	}
}
