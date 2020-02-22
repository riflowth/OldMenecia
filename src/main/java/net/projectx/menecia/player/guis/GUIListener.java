package net.projectx.menecia.player.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public class GUIListener implements Listener {

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof GUI) {
            event.setCancelled(true);
            ((GUI) holder).onClick((Player) event.getWhoClicked(), event.getSlot(), event.getClick());
        }
    }

    @EventHandler
    private void onOpen(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof GUI) {
            ((GUI) holder).onOpen((Player) event.getPlayer());
        }
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        final InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof GUI) {
            ((GUI) holder).onClose((Player) event.getPlayer());
        }
    }

}
