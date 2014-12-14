package me.quartzdev.DeathCrystals;

import java.io.File;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.listeners.PlayerListener;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	Plugin plugin;
	Config config = new Config(this.getConfig());
	Storage storage = new Storage(new File("plugins" + File.separator + "DeathCrystals" + File.separator + "deathcrystals.txt"));
	PlayerListener Listener = new PlayerListener(this, storage, config);
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		config.loadConfig();
		Commands commands = new Commands();
		this.getServer().getPluginCommand("dc").setExecutor(commands);
	}	
	@Override
	public void onDisable() {
		config.saveConfig();
	}

}
