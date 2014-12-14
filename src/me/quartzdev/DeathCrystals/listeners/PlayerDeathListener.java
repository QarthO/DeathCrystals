package me.quartzdev.DeathCrystals.listeners;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class PlayerDeathListener implements Listener{
	
	Plugin plugin;
	Storage storage;
	Config config;
	
	public PlayerDeathListener(Plugin main, Storage storage, Config config) {
		this.plugin = main;
		this.config = config;
		this.storage = storage;
	}

}
