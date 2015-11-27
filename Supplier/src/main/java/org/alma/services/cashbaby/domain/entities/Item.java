package org.alma.services.cashbaby.domain.entities;

import org.alma.services.cashbaby.api.entities.*;
import org.alma.services.cashbaby.api.services.*;
import org.alma.services.cashbaby.domain.services.*;

public class Item implements IItem {
	private String id;
	private String name;
	private String photoUrl;
	private float price;

	public Item(String id, String name, String photoUrl, float price) {
		this.id = id;
		this.name = name;
		this.photoUrl = photoUrl;
		this.price = price;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhotoUrl() {
		return this.photoUrl;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String toString() {
		return "Item("+this.id+","+this.name+","+this.photoUrl+",$"+this.price+")";
	}
}
