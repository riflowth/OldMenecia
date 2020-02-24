package net.projectx.menecia.player.events.admin.mobspawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.world.Area;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AdminPlaceMobSpawner implements Listener {

    private MobSpawnerSetUpManager setupManager;
    private Menecia plugin;

    public AdminPlaceMobSpawner(Menecia plugin) {
        this.plugin = plugin;
        setupManager = plugin.getManagers().getMobSpawnerSetUpManager();
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.STRUCTURE_BLOCK) {
            Location placedLocation = event.getBlockPlaced().getLocation();
            setupManager.setState(event.getPlayer(), MobSpawnerStepUpState.SELECT_MOB);
            setupManager.getSpawnerTemp(event.getPlayer()).setSpawningArea(new Area(placedLocation, placedLocation));
            event.getPlayer().openInventory(new MobSpawnerSelectMobGUI(plugin).getInventory());
        }
    }

}
