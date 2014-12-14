package me.quartzdev.DeathCrystals.listeners;

import java.util.Arrays;
import java.util.List;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class PlayerInteractListener implements Listener {
	
	Plugin plugin;
	Storage storage;
	Config config;
	
	public PlayerInteractListener(Plugin main, Storage storage, Config config) {
		this.plugin = main;
		this.config = config;
		this.storage = storage;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		ItemStack item = event.getPlayer().getItemInHand();
		
		if ((config.isUsingPlayerHeads() && item.equals(Material.SKULL_ITEM)) || (!config.isUsingPlayerHeads() && item.equals(Material.NETHER_STAR))) {
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Material[] List_Interactables = { Material.CHEST, Material.FURNACE, Material.WOOD_BUTTON, Material.WOODEN_DOOR, Material.STONE_BUTTON, Material.BED_BLOCK, Material.BEACON, Material.WORKBENCH, Material.ANVIL, Material.BOAT,
						Material.BREWING_STAND, Material.BURNING_FURNACE, Material.CAKE_BLOCK, Material.COMMAND, Material.COMMAND_MINECART, Material.DAYLIGHT_DETECTOR, Material.TRAPPED_CHEST, Material.TRAP_DOOR, Material.STORAGE_MINECART,
						Material.REDSTONE_ORE, Material.POWERED_MINECART, Material.NOTE_BLOCK, Material.MINECART, Material.LEVER, Material.JUKEBOX, Material.ITEM_FRAME, Material.HOPPER_MINECART, Material.HOPPER, Material.FENCE_GATE, Material.ENDER_CHEST,
						Material.ENCHANTMENT_TABLE, Material.DROPPER, Material.DISPENSER, Material.DRAGON_EGG, Material.DIODE_BLOCK_ON, Material.DIODE_BLOCK_OFF };
				if (Arrays.asList(List_Interactables).contains(event.getClickedBlock().getType())) {
					return;
				}
				if (item.hasItemMeta()) {
					ItemMeta im = item.getItemMeta();
					List<String> lore = im.getLore();
					int id;
					String id_string = ChatColor.stripColor(lore.get(2));
					id_string = id_string.replace("ID: ", "");
					id = Integer.getInteger(id_string);
					if (lore.get(lore.size()-1).equals(ChatColor.GREEN + Verify_Crystal)) {
						Inventory inv = Bukkit.createInventory(null, 45, im.getDisplayName());
						inv.setContents(storage.loadCrystal(id).getContents().getContents());
						event.getPlayer().openInventory(inv);
					}
				}
			}
		}
	}
	
}
