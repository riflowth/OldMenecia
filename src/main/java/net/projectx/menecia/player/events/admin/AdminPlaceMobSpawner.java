package net.projectx.menecia.player.events.admin;

import net.projectx.menecia.player.guis.MobSpawnerGUI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AdminPlaceMobSpawner implements Listener {

    private MobSpawnerGUI gui;

    public AdminPlaceMobSpawner() {
        gui = new MobSpawnerGUI();
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.STRUCTURE_BLOCK) {
            Location placedLocation = event.getBlockPlaced().getLocation();
            event.getPlayer().openInventory(gui.getInventory());
        }
    }

}
