package me.quartzdev.DeathCrystals.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import me.quartzdev.DeathCrystals.config.Config;

public class Storage {
	
	File storageFile;
	
	public Storage(File storageFile) {
		this.storageFile = storageFile;
		storageFile.getParentFile().mkdirs();
		if (!storageFile.exists()) {
			try {
				storageFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removeExpired(Config config) {
		FileInputStream stream;
		InputStreamReader streamReader;
		BufferedReader in;
		
		try {
			FileWriter w = new FileWriter(storageFile.toString());
			BufferedWriter out = new BufferedWriter(w);
			
			stream = new FileInputStream(storageFile);
			streamReader = new InputStreamReader(stream);
			in = new BufferedReader(streamReader);
			
			String line;
			while ((line = in.readLine()) != null) {
				if (Long.valueOf(line.split("==")[1]) > System.currentTimeMillis()) {
					out.write(line);
					out.newLine();
				}
			}
			
			in.close();
			
			out.close();
			w.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			while ((line = in.readLine()) != null) {
				int itemID = Integer.valueOf(line.split("==")[0]);
				if (itemID == id) {
					in.close();
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
		FileInputStream stream;
		InputStreamReader streamReader;
		BufferedReader in;
		
		try {
			FileWriter w = new FileWriter(storageFile.toString());
			BufferedWriter out = new BufferedWriter(w);
			
			stream = new FileInputStream(storageFile);
			streamReader = new InputStreamReader(stream);
			in = new BufferedReader(streamReader);
			
			String line;
			boolean placed = false;
			while ((line = in.readLine()) != null) {
				if (crystal.getId() < Integer.valueOf(line.split("==")[0]) && !placed) {
					String crystalString = crystal.getId() + "==" + crystal.getExpirationDate() + "==" + Converters.toBase64(crystal.getContents());
					out.write(crystalString);
					out.newLine();
					placed = true;
				} else {
					out.write(line);
					out.newLine();
				}
				
			}
			
			if (!placed) {
				String crystalString = crystal.getId() + "==" + crystal.getExpirationDate() + "==" + Converters.toBase64(crystal.getContents());
				out.write(crystalString);
				out.newLine();
			}
			
			in.close();
			
			out.close();
			w.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void removeItem(Crystal crystal) {
		FileInputStream stream;
		InputStreamReader streamReader;
		BufferedReader in;
		
		try {
			FileWriter w = new FileWriter(storageFile.toString());
			BufferedWriter out = new BufferedWriter(w);
			
			stream = new FileInputStream(storageFile);
			streamReader = new InputStreamReader(stream);
			in = new BufferedReader(streamReader);
			
			String line;
			while ((line = in.readLine()) != null) {
				if (crystal.getId() != Integer.valueOf(line.split("==")[0])) {
					out.write(line);
					out.newLine();
				}
			}
			
			in.close();
			
			out.close();
			w.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void editItem(Crystal crystal) {
		FileInputStream stream;
		InputStreamReader streamReader;
		BufferedReader in;
		
		try {
			FileWriter w = new FileWriter(storageFile.toString());
			BufferedWriter out = new BufferedWriter(w);
			
			stream = new FileInputStream(storageFile);
			streamReader = new InputStreamReader(stream);
			in = new BufferedReader(streamReader);
			
			String line;
			while ((line = in.readLine()) != null) {
				if (crystal.getId() == Integer.valueOf(line.split("==")[0])) {
					String crystalString = crystal.getId() + "==" + crystal.getExpirationDate() + "==" + Converters.toBase64(crystal.getContents());
					out.write(crystalString);
					out.newLine();
				} else {
					out.write(line);
					out.newLine();
				}
			}
			
			in.close();
			
			out.close();
			w.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected int getLowestUnusedCrystal() {
		FileInputStream stream;
		InputStreamReader streamReader;
		BufferedReader in;
		
		try {
			
			stream = new FileInputStream(storageFile);
			streamReader = new InputStreamReader(stream);
			in = new BufferedReader(streamReader);
			
			String line;
			int linenumber = 1;
			while ((line = in.readLine()) != null) {
				int currentLineId = Integer.valueOf(line.split("==")[0]);
				if (linenumber == currentLineId) {
					linenumber++;
				} else {
					in.close();
					return linenumber;
				}
			}
			
			in.close();
			return linenumber++;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
}
