package me.quartzdev.DeathCrystals;

import java.io.IOException;

import me.quartzdev.DeathCrystals.config.Config;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	Plugin plugin;
	Config config = new Config(this.getConfig());
	@Override
	public void onEnable() {
		config.loadConfig();
		Commands commands = new Commands();
		this.getServer().getPluginCommand("dc").setExecutor(commands);
		super.onEnable();
	}
	@Override
	public void onDisable() {
		config.saveConfig();
		super.onDisable();
	}

}
