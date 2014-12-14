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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removeExpired(){
		
	}
	
	public Crystal loadCrystal(int id) {
		FileInputStream stream;
		InputStreamReader streamReader;
		BufferedReader in;
		
		try {
			stream = new FileInputStream(storageFile);
			streamReader = new InputStreamReader(stream);
			in = new BufferedReader(streamReader);
			
			String line;
			while((line = in.readLine()) != null) {
				int itemID = Integer.valueOf(line.split("==")[0]);
				if(itemID == id){
					return new Crystal(this, itemID, Long.valueOf(line.split("==")[1]), Converters.fromBase64(line.split("==")[2]));
				}
			}
			

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	protected void addItem(Crystal crystal) {
		
	}
	
	protected void removeItem(Crystal crystal) {
		
	}
	
	protected void editItem(Crystal crystal){
		
	}
}
