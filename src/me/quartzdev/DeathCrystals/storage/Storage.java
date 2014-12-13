package me.quartzdev.DeathCrystals.storage;

public class Storage {
	
	// Structure for table: id, expiration_date, inv_contents
	StorageMethod method;
	
	public Storage(StorageMethod method) {
		this.method = method;
	}
	
	public int removeExpired(){
		return 0;
		
	}
	
	public void saveStorage() {
		
	}
	
	public void loadStorage() {
		
	}
	
	protected void addItem(Crystal crystal) {
		
	}
	
	protected void removeItem(Crystal crystal) {
		
	}
	
	protected void editItem(Crystal crystal){
		
	}
}
