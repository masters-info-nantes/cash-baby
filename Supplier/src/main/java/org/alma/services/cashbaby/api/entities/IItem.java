package org.alma.services.cashbaby.api.entities;

public interface IItem {
	public String getId();
	public String getName();
	public String getPhotoUrl();
	public float getPrice();
	public void setId(String id);
	public void setName(String name);
	public void setPhotoUrl(String photoUrl);
	public void setPrice(float price);
}
