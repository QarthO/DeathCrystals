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
		try {
			config.loadConfig();
		} catch (IOException e) {
			String Error = e.getMessage();
			this.getServer().getConsoleSender().sendMessage(Error);
		}
		Commands commands = new Commands();
		this.getServer().getPluginCommand("dc").setExecutor(commands);
		super.onEnable();
	}
	@Override
	public void onDisable() {
		try {
			config.saveConfig();
		} catch (IOException e) {
			String Error = e.getMessage();
			this.getServer().getConsoleSender().sendMessage(Error);
		}
		super.onDisable();
	}

}
