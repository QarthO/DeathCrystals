package me.quartzdev.DeathCrystals.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public void loadConfig() throws IOException{
		createConfig();
		
		List<String> damageCauses = config.getStringList("death-types");
		dropForPVP = damageCauses.remove("PVP");
		deathCause = new DamageCause[damageCauses.size()];
		deathCause = damageCauses.toArray(deathCause);
		
		isUsingPermissions = config.getBoolean("use-permissions", false);
		expirationDate = config.getLong("expiration-date", 0);
		isUsingPlayerHeads = config.getBoolean("player-heads", false);
		protectionTime = config.getLong("pickup-protection", 0);	
	}
	
	public void saveConfig() throws IOException{
		createConfig();
		
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
	
	private void createConfig() throws IOException{
		File configFile = new File("plugins" + File.separator + "DeathCrystals" + File.separator + "config.yml");
		if(configFile.exists()){
			configFile.createNewFile();
		}
	}

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
