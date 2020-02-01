package net.projectx.menecia.mob;

import net.projectx.menecia.Core;
import net.projectx.menecia.mob.monster.StarvingZombie;
import net.projectx.menecia.resources.Key;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class MobSpawner implements Listener {

    private MobCreator mobCreator;
    private Core plugin;
    private BukkitTask spawnerTask;
    private Map<Integer, Mob> mobMap = new HashMap<>();
    private int maxiumNodeSpawn = 5;
    private Map<Integer, Integer> spawnedCountMap = new HashMap<>();

    public MobSpawner(Core plugin) {
        this.plugin = plugin;
        mobCreator = new MobCreator(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void registerMobs() {
        mobMap.put(StarvingZombie.ID, new StarvingZombie());
    }

    public void start() {
        Location spawnLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
        spawnerTask = plugin.runTaskTimer(() -> {
            if (!spawnedCountMap.containsKey(StarvingZombie.ID)) {
                spawnedCountMap.put(StarvingZombie.ID, 0);
            }
            if (spawnedCountMap.get(StarvingZombie.ID) < maxiumNodeSpawn) {
                Mob mob = mobMap.get(StarvingZombie.ID);
                mobCreator.spawn(mob, spawnLocation);
                spawnedCountMap.put(StarvingZombie.ID, spawnedCountMap.get(StarvingZombie.ID) + 1);
                Log.sendSuccess("Spawned 1 " + mob.getName() + " [Lv." + mob.getLevel() + "] "
                        + "(" + spawnedCountMap.get(StarvingZombie.ID) + "/" + maxiumNodeSpawn + ")");
            }
        }, 0, 20 * 20L);
    }

    @EventHandler
    private void entityDeathEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (isMob(entity)) {
            Mob mob = mobMap.get(getMobId(entity));
            spawnedCountMap.put(mob.getId(), spawnedCountMap.get(mob.getId()) - 1);
            Log.sendWarning("Despawned 1 " + mob.getName() + " [Lv." + mob.getLevel() + "] "
                    + "(" + spawnedCountMap.get(mob.getId()) + "/" + maxiumNodeSpawn + ")");
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
            if (isMob(entity)) {
                entity.remove();
            }
        }
    }

    private boolean isMob(Entity entity) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        return dataContainer.has(plugin.getNamespacedKey(Key.MOB_ID), PersistentDataType.INTEGER);
    }

    private int getMobId(Entity entity) {
        if (isMob(entity)) {
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            return dataContainer.get(plugin.getNamespacedKey(Key.MOB_ID), PersistentDataType.INTEGER);
        }
        return 0;
    }

    public void stop() {
        spawnerTask.cancel();
    }

}
