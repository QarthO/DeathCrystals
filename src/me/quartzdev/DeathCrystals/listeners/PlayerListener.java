package me.quartzdev.DeathCrystals.listeners;

import java.awt.Event;
import java.util.ArrayList;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Crystal;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener{
	
	Plugin plugin;
	Config config;
	public PlayerListener(Plugin main, Config config) {
		this.plugin = main;
	}
	
	@EventHandler
	public void onDeath(EntityDamageEvent event){
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		if(!(event.getEntity().isDead())){
			return;
		}
		ArrayList<DamageCause> deathcause = new ArrayList<DamageCause>();
		if(deathcause.contains(event.getCause())){
			Crystal crystal = new Crystal();
			
		}
	}
}
