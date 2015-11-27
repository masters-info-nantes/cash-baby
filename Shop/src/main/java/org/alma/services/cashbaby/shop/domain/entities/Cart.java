package org.alma.services.cashbaby.shop.domain.entities;

import org.alma.services.cashbaby.shop.api.entities.IItemDetailed;

import java.util.ArrayList;

	// ItemId => quantity
public class Cart extends ArrayList<Cart.Entry> {

	public Cart() {
	}
	
	public Cart.Entry get(String entryId) {
		Cart.Entry res = null;
		for(Cart.Entry entry : this) {
			if(entry.getId().equals(entryId)) {
				res = entry;
				break;
			}
		}
		return res;
	}
	
	public void put(Cart.Entry entry) {
		this.add(entry);
	}
	
	public static class Entry extends ItemDetailed implements IItemDetailed {
		private int quantity;
		
		public Entry(IItemDetailed item, int quantity) {
			super(
				item.getId(),
				item.getName(),
				item.getPhotoUrl(),
				item.getPrice(),
				item.getDescription()
			);
			this.quantity = quantity;
		}
		
		public Entry() {
			super(null,null,null,0.0f,null);
			this.quantity = 0;
		}
		
		public int getQuantity() {
			return this.quantity;
		}
		
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}
}
