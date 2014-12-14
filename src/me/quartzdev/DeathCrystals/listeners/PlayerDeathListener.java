package me.quartzdev.DeathCrystals.listeners;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import me.quartzdev.DeathCrystals.Language;
import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Crystal;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
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
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		//Check if is a dead player
		if (!(event.getEntity() instanceof Player) && !(event instanceof PlayerDeathEvent)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		//Check if the player's inventory is empty
		ArrayList<ItemStack> droppedItems = new ArrayList<ItemStack>();
		for (ItemStack item : event.getDrops()) {
			droppedItems.add(item);
		}

		if (droppedItems.size() <= 0 || droppedItems == null) {
			return;
		}
		//Check if the Death-Type is in the config
		ArrayList<DamageCause> deathCauses = new ArrayList<DamageCause>();
		deathCauses = config.getDeathCauses();
		Bukkit.broadcastMessage(player.getLastDamageCause().getCause() + "");
		Bukkit.broadcastMessage(ChatColor.RED + "" + deathCauses + "");
		if (deathCauses.contains(player.getLastDamageCause().getCause())) {	
			Crystal crystal = Crystal.createItem(storage, config.getExpirationDate() + System.currentTimeMillis(), player.getInventory());
				event.getDrops().clear();
				event.getDrops().add(getCrystal(player, crystal));
		} else{
			Bukkit.broadcastMessage(player.getLastDamageCause().getCause() + "");
		}
	}
	
	private ItemStack getCrystal(Player player, Crystal crystal) {
		ItemStack DeathCrystal;
		if (config.isUsingPlayerHeads() != true) {
			DeathCrystal = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta meta = (SkullMeta) DeathCrystal.getItemMeta();
			meta.setOwner(player.getName());
			DeathCrystal.setItemMeta(meta);
		} else {
			DeathCrystal = new ItemStack(Material.NETHER_STAR);
		}
		ItemMeta im = DeathCrystal.getItemMeta();
		im.setDisplayName(ChatColor.DARK_PURPLE + player.getName() + "'s Inventory");
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
		String date = sdf.format(crystal.getExpirationDate() + System.currentTimeMillis());
		String[] lore = { ChatColor.RED + "Expires on " + date, ChatColor.BLACK + "ID: " + String.valueOf(crystal.getId()), "", ChatColor.GREEN + Language.CRYSTAL_LORE.getMessage() };
		im.setLore(Arrays.asList(lore));
		return null;
	}

}
