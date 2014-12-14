package me.quartzdev.DeathCrystals;

import me.quartzdev.DeathCrystals.config.Config;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	Plugin plugin;
	Config config = new Config(this.getConfig());
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
