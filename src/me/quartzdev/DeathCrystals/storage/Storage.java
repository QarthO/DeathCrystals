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
	
	public void loadCrystal(int id) {
		try {
			FileInputStream stream = new FileInputStream(storageFile);
			InputStreamReader streamReader = new InputStreamReader(stream);
			BufferedReader in = new BufferedReader(streamReader);
			
			String line;
			while((line = in.readLine()) != null) {
				int id = Integer.valueOf(line.split("==")[0]);
			}
			in.close();
			streamReader.close();
			stream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void addItem(Crystal crystal) {
		
	}
	
	protected void removeItem(Crystal crystal) {
		
	}
	
	protected void editItem(Crystal crystal){
		
	}
}
