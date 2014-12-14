package me.quartzdev.DeathCrystals.storage;

import org.bukkit.inventory.Inventory;

public class Crystal {
	
	Storage storage;
	
	int id;
	long expirationDate;
	Inventory contents;
	
	public Crystal(Storage storage, int id, long expirationDate, Inventory contents) {
		this.storage = storage;
		this.id = id;
		this.expirationDate = expirationDate;
		this.contents = contents;
		
		storage.addItem(this);
	}
	
	public int getId() {
		return id;
	}
	
	public long getExpirationDate() {
		return expirationDate;
	}
	
	public Inventory getContents() {
		return contents;
	}
	
	protected void setId(int id) {
		this.id = id;
		storage.editItem(this);
	}
	
	protected void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
		storage.editItem(this);
	}
	
	protected void setContents(Inventory contents) {
		this.contents = contents;
		storage.editItem(this);
	}
	
	public void delete() {
		storage.removeItem(this);
		id = -1;
		expirationDate = 0;
		contents = null;
	}
	
}
