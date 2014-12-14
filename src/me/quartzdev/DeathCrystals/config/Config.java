package me.quartzdev.DeathCrystals.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Config {
	
	FileConfiguration config;
	
	private ArrayList<DamageCause> deathCauses;
	private boolean dropForPVP;
	private boolean isUsingPermissions;
	private long expirationDate;
	private boolean isUsingPlayerHeads;
	private long protectionTime;
	
	public Config(FileConfiguration config) {
		this.config = config;
		deathCauses = new ArrayList<DamageCause>();
	}
	
	// Loads the config and creates the file if it doesn't exist.
	// If the file doesn't exist, it returns default values.
	public void loadConfig() {
		List<String> damageCausesList = config.getStringList("death-types");
		dropForPVP = damageCausesList.remove("PVP");
		
		ArrayList<DamageCause> realDamageCausesList = new ArrayList<DamageCause>();
		for (String damageCause : damageCausesList) {
			realDamageCausesList.add(DamageCause.valueOf(damageCause));
		}
		
		for (DamageCause realDamageCause : realDamageCausesList) {
			deathCauses.add(realDamageCause);
		}
		
		// TODO Make letters work.
		isUsingPermissions = config.getBoolean("use-permissions");
		expirationDate = config.getLong("expiration-date");
		isUsingPlayerHeads = config.getBoolean("player-heads");
		protectionTime = config.getLong("pickup-protection");
	}
	
	// Saves the config from memory.
	public void saveConfig() {
		ArrayList<String> deathCauseString = new ArrayList<String>();
		for (DamageCause dc : deathCauses) {
			deathCauseString.add(dc.toString());
		}
		
		if (dropForPVP) {
			deathCauseString.add("PVP");
		}
		
		config.set("death-types", deathCauseString);
		config.set("use-permissions", isUsingPermissions);
		config.set("expiration-date", expirationDate);
		config.set("player-heads", isUsingPlayerHeads);
		config.set("pickup-protection", protectionTime);
	}
	
	// Getters and setters for config values.
	public FileConfiguration getConfig() {
		return config;
	}
	
	public ArrayList<DamageCause> getDeathCauses() {
		return deathCauses;
	}
	
	public boolean isDropForPVP() {
		return dropForPVP;
	}
	
	public boolean isUsingPermissions() {
		return isUsingPermissions;
	}
	
	public long getExpirationDate() {
		return expirationDate;
	}
	
	public boolean isUsingPlayerHeads() {
		return isUsingPlayerHeads;
	}
	
	public long getProtectionTime() {
		return protectionTime;
	}
	
	public void setConfig(FileConfiguration config) {
		this.config = config;
	}
	
	public void setDeathCauses(ArrayList<DamageCause> deathCauses) {
		this.deathCauses = deathCauses;
	}
	
	public void setDropForPVP(boolean dropForPVP) {
		this.dropForPVP = dropForPVP;
	}
	
	public void setUsingPermissions(boolean isUsingPermissions) {
		this.isUsingPermissions = isUsingPermissions;
	}
	
	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public void setUsingPlayerHeads(boolean isUsingPlayerHeads) {
		this.isUsingPlayerHeads = isUsingPlayerHeads;
	}
	
	public void setProtectionTime(long protectionTime) {
		this.protectionTime = protectionTime;
	}
	
}
