package me.quartzdev.DeathCrystals.listeners;

import java.util.ArrayList;
import java.util.List;

import me.quartzdev.DeathCrystals.Language;
import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Crystal;
import me.quartzdev.DeathCrystals.storage.Storage;
import me.quartzdev.DeathCrystals.utils.CrystalNotFoundException;
import me.quartzdev.DeathCrystals.utils.ExpiredCrystalException;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class InventoryInteractListener implements Listener {
	Plugin plugin;
	Storage storage;
	Config config;
	
	public InventoryInteractListener(Plugin main, Storage storage, Config config) {
		this.plugin = main;
		this.config = config;
		this.storage = storage;
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent event) {
		if (isCrystalInventory(event.getInventory())) {
			if (event.getCurrentItem().hasItemMeta()) {
				ItemMeta im = event.getCurrentItem().getItemMeta();
				if (im.hasLore()) {
					List<String> lore = im.getLore();
					if (Language.CRYSTAL_LORE.getMessage().equals(lore.get(lore.size() - 1))) {
						event.setCancelled(true);
						return;
					}
				}
			}
		}
		
		switch (event.getAction()) {
			case PLACE_ALL:
			case PLACE_SOME:
			case PLACE_ONE:
			case SWAP_WITH_CURSOR:
			case HOTBAR_SWAP:
			case UNKNOWN:
				if (isCrystalInventory(event.getInventory()) && isCrystalArea(event.getRawSlot())) {
					event.setCancelled(true);
				}
				break;
			case MOVE_TO_OTHER_INVENTORY:
				if ((!isCrystalArea(event.getRawSlot())) && (isCrystalInventory(event.getInventory()))) {
					int i = getFirstOpenSlot(event.getView().getBottomInventory(), event.getSlot());
					if (i == -1) {
						event.setCancelled(true);
					} else {
						ItemStack fromItem = event.getView().getBottomInventory().getItem(i);
						ItemStack toItem = event.getCurrentItem();
						
						event.setCancelled(true);
						event.setCurrentItem(fromItem);
						event.getView().getBottomInventory().setItem(i, toItem);
					}
				}
				break;
			default:
				break;
		
		}
		
		if (!event.isCancelled()) {
			try {
				Crystal crystal = storage.loadCrystal(Integer.valueOf(event.getView().getTopInventory().getTitle().split("ID")[1]));
				crystal.setContents(event.getView().getTopInventory());
			} catch (NumberFormatException | ExpiredCrystalException | CrystalNotFoundException e) {
				event.setCancelled(true);
				ArrayList<HumanEntity> toClose = new ArrayList<HumanEntity>();
				for (HumanEntity ent : event.getViewers()) {
					toClose.add(ent);
					((Player) ent).sendMessage(e.getMessage());
				}
				for (HumanEntity ent : toClose) {
					ent.closeInventory();
				}
				toClose = null;
			}
		}
		
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		if (isCrystalInventory(event.getInventory())) {
			for (int i : event.getRawSlots()) {
				if (isCrystalArea(i)) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	private int getFirstOpenSlot(Inventory inventory, int prevSlot) {
		int i;
		int n = 0;
		
		if (prevSlot <= 8) {
			i = 9;
		} else {
			i = 8;
		}
		
		while (n == 0) {
			ItemStack item = inventory.getItem(i);
			
			if ((item == null) || (item.equals(null)) || (item.getType().equals(Material.AIR))) {
				n = i;
			}
			if (0 < i && i <= 8) {
				i--;
			} else if (8 < i && i < 35) {
				i++;
			} else if (i == 0) {
				i = 9;
			} else {
				n = -1;
			}
		}
		return n;
	}
	
	private boolean isCrystalInventory(Inventory i) {
		return i.getName().contains("'s Inventory");
	}
	
	private boolean isCrystalArea(int i) {
		return i < 45;
	}
	
}
