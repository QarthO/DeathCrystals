package me.quartzdev.DeathCrystals.listeners;

import java.util.Arrays;
import java.util.List;

import me.quartzdev.DeathCrystals.Language;
import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Storage;
import me.quartzdev.DeathCrystals.utils.CrystalNotFoundException;
import me.quartzdev.DeathCrystals.utils.ExpiredCrystalException;

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
		
		if (item.getType().equals(Material.SKULL_ITEM) || item.getType().equals(Material.NETHER_STAR)) {
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				
				// Nothing happens if a player clicks on an interactable type
				// block.
				if (event.hasBlock()) {
					Material[] interactables = { Material.CHEST, Material.FURNACE, Material.WOOD_BUTTON, Material.WOODEN_DOOR, Material.STONE_BUTTON, Material.BED_BLOCK, Material.BEACON, Material.WORKBENCH, Material.ANVIL, Material.BOAT,
							Material.BREWING_STAND, Material.BURNING_FURNACE, Material.CAKE_BLOCK, Material.COMMAND, Material.COMMAND_MINECART, Material.DAYLIGHT_DETECTOR, Material.TRAPPED_CHEST, Material.TRAP_DOOR, Material.STORAGE_MINECART,
							Material.REDSTONE_ORE, Material.POWERED_MINECART, Material.NOTE_BLOCK, Material.MINECART, Material.LEVER, Material.JUKEBOX, Material.ITEM_FRAME, Material.HOPPER_MINECART, Material.HOPPER, Material.FENCE_GATE,
							Material.ENDER_CHEST, Material.ENCHANTMENT_TABLE, Material.DROPPER, Material.DISPENSER, Material.DRAGON_EGG, Material.DIODE_BLOCK_ON, Material.DIODE_BLOCK_OFF };
					if (Arrays.asList(interactables).contains(event.getClickedBlock().getType())) {
						return;
					}
				}
				
				// Verifies the item in the player's hand has meta.
				if (!item.hasItemMeta()) {
					return;
				}
				ItemMeta im = item.getItemMeta();
				
				// Verifies the item in the player's hand has lore.
				if (!im.hasLore()) {
					return;
				}
				List<String> lore = im.getLore();
				
				if (Language.CRYSTAL_LORE.getMessage().equals(lore.get(lore.size() - 1))) {
					int id = Integer.valueOf(ChatColor.stripColor(lore.get(1)).replace("ID: ", ""));
					
					try {
						Inventory inv = Bukkit.createInventory(null, 45, im.getDisplayName() + ChatColor.GRAY + "ID" + ChatColor.stripColor(lore.get(1)).replace("ID: ", ""));
						inv.setContents(storage.loadCrystal(id).getContents().getContents());
						event.getPlayer().openInventory(inv);
					} catch (ExpiredCrystalException e) {
						event.getPlayer().sendMessage(String.format(Language.CRYSTAL_EXPIRED.getMessage(), id, lore.get(0).replace("Expires on ", "")));
						event.getPlayer().setItemInHand(null);
					} catch (CrystalNotFoundException e) {
						event.getPlayer().sendMessage(String.format(Language.CRYSTAL_NOT_FOUND.getMessage(), ChatColor.stripColor(lore.get(1)).replace("ID: ", "")));
						event.getPlayer().setItemInHand(null);
					}
				}
			}
		}
	}
}
