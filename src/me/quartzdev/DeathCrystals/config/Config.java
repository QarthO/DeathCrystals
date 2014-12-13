package me.quartzdev.DeathCrystals.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Config {
	
	FileConfiguration config;
	DamageCause[] deathCause;
	
	
	public Config(FileConfiguration config) {
		this.config = config;
	}
	
	public void loadConfig(){
		
	}
	
}
