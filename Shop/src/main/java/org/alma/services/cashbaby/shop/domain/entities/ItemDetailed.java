package org.alma.services.cashbaby.shop.domain.entities;

import org.alma.services.cashbaby.shop.api.entities.*;

public class ItemDetailed extends Item implements IItemDetailed {
	private String description;
	
	public ItemDetailed(String id, String name, String photoUrl, float price, String description) {
		super(id,name,photoUrl,price);
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "ItemDetailed("+this.getId()+","+this.getName()+","+this.getPhotoUrl()+",$"+this.getPrice()+","+this.description+")";
	}
}
