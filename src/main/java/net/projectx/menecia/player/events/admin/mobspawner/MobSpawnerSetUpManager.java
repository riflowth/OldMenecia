package net.projectx.menecia.player.events.admin.mobspawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.spawner.MobSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobSpawnerSetUpManager implements Listener {

    private Menecia plugin;
    private Map<UUID, MobSpawnerStepUpState> setupStateMap = new HashMap<>();
    private Map<UUID, MobSpawner> mobSpawnerTempMap = new HashMap<>();

    public MobSpawnerSetUpManager(Menecia plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void setState(Player player, MobSpawnerStepUpState state) {
        setupStateMap.put(player.getUniqueId(), state);
        if (state == MobSpawnerStepUpState.SELECT_MOB) {
            mobSpawnerTempMap.put(player.getUniqueId(), new MobSpawner());
        }
    }

    public void success(Player player) {
        setupStateMap.remove(player.getUniqueId());
        plugin.getManagers().getMobSpawnerManager().addSpawner(mobSpawnerTempMap.get(player.getUniqueId()));
        mobSpawnerTempMap.remove(player.getUniqueId());;
    }

    public MobSpawner getSpawnerTemp(Player player) {
        return mobSpawnerTempMap.get(player.getUniqueId());
    }

    @EventHandler
    public void onCloseSetUpGUI(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof MobSpawnerGUI) {
            Player player = (Player) event.getPlayer();
            if (setupStateMap.get(player.getUniqueId()) == null) return;
            plugin.runTaskLater(() -> {
                switch (setupStateMap.get(player.getUniqueId())) {
                    case SELECT_MOB:
                        player.openInventory(new MobSpawnerSelectMobGUI(plugin).getInventory());
                        break;
                    case SELECT_SPAWN_RATE:
                        player.openInventory(new MobSpawnerSelectSpawnRateGUI(plugin).getInventory());
                        break;
                    case SELECT_MAXIMUM_AMOUNT:
                        player.openInventory(new MobSpawnerSelectMaxAmountGUI(plugin).getInventory());
                        break;
                    case SELECT_AREA:
                        player.openInventory(new MobSpawnerSelectAreaGUI(plugin).getInventory());
                        break;
                }
            }, 5L);
        }
    }

}
