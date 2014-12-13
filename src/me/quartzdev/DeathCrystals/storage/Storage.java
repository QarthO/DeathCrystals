package me.quartzdev.DeathCrystals.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Storage {
	
	// Structure for table: id, expiration_date, inv_contents
	File storageFile;
	
	public Storage(File storageFile) {
		this.storageFile = storageFile;
		if(!storageFile.exists()){
			try {
				storageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeExpired(){
		try {
			FileInputStream stream = new FileInputStream(storageFile);
			InputStreamReader streamReader = new InputStreamReader(stream);
			BufferedReader in = new BufferedReader(streamReader);
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void loadCrystal(int id) {
		
	}
	
	protected void addItem(Crystal crystal) {
		
	}
	
	protected void removeItem(Crystal crystal) {
		
	}
	
	protected void editItem(Crystal crystal){
		
	}
}
