package me.quartzdev.DeathCrystals.listeners;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.plugin.Plugin;

public class InventoryInteractListener {
	Plugin plugin;
	Storage storage;
	Config config;
	
	public InventoryInteractListener(Plugin main, Storage storage, Config config) {
		this.plugin = main;
		this.config = config;
		this.storage = storage;
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryInteractEvent event) {
		// TODO
		
	}
}
