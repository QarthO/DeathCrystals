package me.quartzdev.DeathCrystals.listeners;

import me.quartzdev.DeathCrystals.config.Config;
import me.quartzdev.DeathCrystals.storage.Storage;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
	switch (event.getAction()) {
	case PLACE_ALL:
	case PLACE_SOME:
	case PLACE_ONE:
	case SWAP_WITH_CURSOR:
	case HOTBAR_SWAP:
	case UNKNOWN:
	    if (isCrystalInventory(event.getInventory())) {
		event.setCancelled(true);
	    }
	    break;
	case MOVE_TO_OTHER_INVENTORY:
	    if ((event.getRawSlot() <= 45)
		    && (isCrystalInventory(event.getInventory()))) {
		int i = getFirstOpenSlot(event.getView().getBottomInventory());
		if (i == -1) {
		    event.setCancelled(true);
		} else {
		    ItemStack fromItem = event.getView().getBottomInventory()
			    .getItem(i);
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
    }

    private int getFirstOpenSlot(Inventory inventory) {
	int i = 0;
	for (ItemStack item : inventory.getContents()) {
	    if ((item == null) || (item.equals(null))
		    || (item.getType().equals(Material.AIR))) {
		return i;
	    }
	    i++;
	}
	return -1;
    }

    private boolean isCrystalInventory(Inventory i) {
	return i.getName().contains("'s Inventory");
    }

}
