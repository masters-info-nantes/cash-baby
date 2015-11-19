package org.alma.services.cashbaby.supplier;

public class ItemDetailed extends Item {
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
