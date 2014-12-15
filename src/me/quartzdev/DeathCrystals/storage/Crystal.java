package me.quartzdev.DeathCrystals.storage;

import java.text.SimpleDateFormat;

import org.bukkit.inventory.Inventory;

public class Crystal {
	
	Storage storage;
	
	int id;
	long expirationDate;
	Inventory contents;
	
	protected Crystal(Storage storage, int id, long expirationDate, Inventory contents) {
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
	
	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
		storage.editItem(this);
	}
	
	public void setContents(Inventory contents) {
		this.contents = contents;
		storage.editItem(this);
	}
	
	public void delete() {
		storage.removeItem(this);
		id = -1;
		expirationDate = 0;
		contents = null;
	}
	
	public String getReadableDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
		return sdf.format(this.getExpirationDate());
	}
	
	public static Crystal createItem(Storage storage, long expirationDate, Inventory contents) {
		int id = storage.getLowestUnusedCrystal();
		
		Crystal c = new Crystal(storage, id, expirationDate, contents);
		
		storage.addItem(c);
		return c;
	}
	
}
