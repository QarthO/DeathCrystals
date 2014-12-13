package me.quartzdev.DeathCrystals.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Config {
	
	FileConfiguration config;
	
	DamageCause[] deathCause;
	boolean dropForPVP;
	boolean isUsingPermissions;
	long expirationDate;
	boolean isUsingPlayerHeads;
	
	public Config(FileConfiguration config) {
		this.config = config;
	}
	
	public void loadConfig(){
		// TODO if it doesn't exist, create default config
		// TODO load config
	}
	
	
}
