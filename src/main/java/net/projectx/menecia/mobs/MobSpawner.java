package net.projectx.menecia.mobs;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class MobSpawner implements Listener{

    private Menecia plugin;
    private BukkitTask spawnerTask;
    private int maxiumNodeSpawn = 5;
    private Map<Mob, Integer> spawnedCountMap = new HashMap<>();

    public MobSpawner(Menecia plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void start() {
        Log.sendSuccess("Started Mob Spawner!");
        Location spawnLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
        spawnerTask = plugin.runTaskTimer(() -> {
            for (Mob mob : MobManager.getAllMobs()) {
                spawnedCountMap.putIfAbsent(mob, 0);
                if (spawnedCountMap.get(mob) < maxiumNodeSpawn) {
                    MobUtil.spawn(mob, spawnLocation);
                    spawnedCountMap.put(mob, spawnedCountMap.get(mob) + 1);
                    Log.sendSuccess("Spawned 1 " + MobUtil.getDisplayNameWithLevel(mob)
                            + " &6(" + spawnedCountMap.get(mob) + "/" + maxiumNodeSpawn + ")");
                }
            }
        }, 0, 10 * 20L);
    }

    @EventHandler
    private void mobDeathEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (MobUtil.isMob(entity)) {
            MobUtil.getMobInstance(entity).despawn(entity);
            removeMob(entity);
        }
    }

    public void removeMob(Entity entity) {
        if (MobUtil.isMob(entity)) {
            Mob mob = MobUtil.getMobInstance(entity);
            spawnedCountMap.put(mob, spawnedCountMap.get(mob) - 1);
            Log.sendWarning("Despawned 1 " + MobUtil.getDisplayNameWithLevel(mob)
                    + " &6(" + spawnedCountMap.get(mob) + "/" + maxiumNodeSpawn + ")");
        }
    }

    public void clearAllMobs() {
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            if (MobUtil.isMob(entity)) {
                entity.remove();
            }
        }
    }

    public void stop() {
        spawnerTask.cancel();
    }

}
