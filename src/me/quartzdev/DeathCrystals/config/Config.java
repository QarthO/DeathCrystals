package me.quartzdev.DeathCrystals.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Config {
	
	FileConfiguration config;
	
	DamageCause[] deathCause;
	boolean dropForPVP;
	boolean isUsingPermissions;
	long expirationDate;
	boolean isUsingPlayerHeads;
	long protectionTime;
	
	public Config(FileConfiguration config) {
		this.config = config;
	}
	
	// Loads the config and creates the file if it doesn't exist.
	// If the file doesn't exist, it returns default values.
	public void loadConfig(){
		List<String> damageCauses = config.getStringList("death-types");
		Bukkit.broadcastMessage("DamageCauses: " + damageCauses.toString());
		dropForPVP = damageCauses.remove("PVP");
		DamageCause[] deathCause2 = new DamageCause[damageCauses.size()];
		deathCause2 = damageCauses.toArray(deathCause);
		deathCause = deathCause2;
		
		isUsingPermissions = config.getBoolean("use-permissions", false);
		expirationDate = config.getLong("expiration-date", 0);
		isUsingPlayerHeads = config.getBoolean("player-heads", false);
		protectionTime = config.getLong("pickup-protection", 0);	
	}
	
	// Saves the config from memory.
	public void saveConfig(){
		List<DamageCause> deathCauses = Arrays.asList(deathCause);
		ArrayList<String> deathCauseString = new ArrayList<String>();
		for(DamageCause dc : deathCauses){
			deathCauseString.add(dc.toString());
		}
		if(dropForPVP){
			deathCauseString.add("PVP");
		}
		
		config.set("death-types", deathCauseString);
		config.set("use-permissions", isUsingPermissions);
		config.set("expiration-date", expirationDate);
		config.set("player-heads", isUsingPlayerHeads);
		config.set("pickup-protection", protectionTime);
	}
	
	//Getters and setters for config values.
	
	public FileConfiguration getConfig() {
		return config;
	}

	public DamageCause[] getDeathCause() {
		return deathCause;
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

	public void setDeathCause(DamageCause[] deathCause) {
		this.deathCause = deathCause;
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
