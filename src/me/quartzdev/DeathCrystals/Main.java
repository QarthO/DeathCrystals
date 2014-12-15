package me.quartzdev.DeathCrystals;

import java.io.File;
import java.util.logging.Logger;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.listeners.InventoryInteractListener;
import me.quartzdev.DeathCrystals.listeners.PlayerDeathListener;
import me.quartzdev.DeathCrystals.listeners.PlayerInteractListener;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Config config;
    private Storage storage;
    private PlayerDeathListener playerDeathListener;
    private PlayerInteractListener playerInteractListener;
    private InventoryInteractListener inventoryInteractListener;
    private Logger logger;

    @Override
    public void onEnable() {
	config = new Config(this.getConfig());
	storage = new Storage(new File("plugins" + File.separator
		+ "DeathCrystals" + File.separator + "deathcrystals.txt"));
	playerDeathListener = new PlayerDeathListener(this, storage, config);
	playerInteractListener = new PlayerInteractListener(this, storage,
		config);
	inventoryInteractListener = new InventoryInteractListener(this,
		storage, config);
	logger = Bukkit.getLogger();

	this.saveDefaultConfig();
	config.loadConfig();
	Commands commands = new Commands();
	this.getServer().getPluginCommand("dc").setExecutor(commands);
	this.getServer().getPluginManager()
		.registerEvents(playerDeathListener, this);
	this.getServer().getPluginManager()
		.registerEvents(playerInteractListener, this);
	this.getServer().getPluginManager()
		.registerEvents(inventoryInteractListener, this);
    }

    @Override
    public void onDisable() {
	config.saveConfig();
    }

    public void logMessage(String message) {
	logger.info("[" + this.getName() + "] " + message);
    }

    public void errorMessage(String message) {
	logger.severe("[" + this.getName() + "] " + message);
    }

    public void warningMessage(String message) {
	logger.warning("[" + this.getName() + "] " + message);

    }

}
