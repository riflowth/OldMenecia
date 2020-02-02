package net.projectx.menecia.mobs;

import net.projectx.menecia.Core;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class MobSpawner implements Listener {

    private Core plugin;
    private BukkitTask spawnerTask;
    private int maxiumNodeSpawn = 5;
    private Map<Mob, Integer> spawnedCountMap = new HashMap<>();

    public MobSpawner(Core plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void start() {
        Log.sendSuccess("Started Mob Spawner!");
        Location spawnLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
        spawnerTask = plugin.runTaskTimer(() -> {
            for (Mob mob : Mobs.getAllMobs()) {
                if (!spawnedCountMap.containsKey(mob)) {
                    spawnedCountMap.put(mob, 0);
                }
                if (spawnedCountMap.get(mob) < maxiumNodeSpawn) {
                    Mobs.spawn(mob, spawnLocation);
                    spawnedCountMap.put(mob, spawnedCountMap.get(mob) + 1);
                    Log.sendSuccess("Spawned 1 " + Mobs.getDisplayName(mob)
                            + " &6(" + spawnedCountMap.get(mob) + "/" + maxiumNodeSpawn + ")");
                }
            }
        }, 0, 20 * 20L);
    }

    @EventHandler
    private void entityDeathEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (Mobs.isMob(entity)) {
            Mob mob = Mobs.get(Mobs.getId(entity));
            spawnedCountMap.put(mob, spawnedCountMap.get(mob) - 1);
            Log.sendWarning("Despawned 1 " + Mobs.getDisplayName(mob)
                    + " &6(" + spawnedCountMap.get(mob) + "/" + maxiumNodeSpawn + ")");
        }
    }

    @EventHandler
    private void resetVanillaCombust(EntityCombustEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            event.setCancelled(true);
        }
    }

    public void clearAllMobs() {
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            if (Mobs.isMob(entity)) {
                entity.remove();
            }
        }
    }

    public void stop() {
        spawnerTask.cancel();
    }

}
