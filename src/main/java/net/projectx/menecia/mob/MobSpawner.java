package net.projectx.menecia.mob;

import net.projectx.menecia.Core;
import net.projectx.menecia.mob.monster.StarvingZombie;
import net.projectx.menecia.resources.Key;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobSpawner implements Listener {

    private MobCreator mobCreator;
    private Core plugin;
    private BukkitTask spawnerTask;
    private Map<Integer, Mob> mobMap = new HashMap<>();
    private int maxiumNodeSpawn = 5;
    private Map<Integer, Integer> spawnedNodeMap = new HashMap<>();
    private Map<UUID, Integer> spawnedMap = new HashMap<>();

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
            if (!spawnedNodeMap.containsKey(StarvingZombie.ID)) {
                spawnedNodeMap.put(StarvingZombie.ID, 0);
            }
            if (spawnedNodeMap.get(StarvingZombie.ID) < maxiumNodeSpawn) {
                Mob mob = mobMap.get(StarvingZombie.ID);
                Entity entity = mobCreator.spawn(mob, spawnLocation);
                int previousEntityInNode = spawnedNodeMap.get(StarvingZombie.ID);
                spawnedNodeMap.put(StarvingZombie.ID, previousEntityInNode + 1);
                int currentEntityInNode = spawnedNodeMap.get(StarvingZombie.ID);
                spawnedMap.put(entity.getUniqueId(), StarvingZombie.ID);
                Log.sendSuccess("Spawned 1 " + mob.getName() + " [Lv." + mob.getLevel() + "] "
                        + "(" + currentEntityInNode + "/" + maxiumNodeSpawn + ")");
            }
        }, 0, 20 * 20L);
    }

    @EventHandler
    private void entityDeathEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        boolean isMeneciaMob = dataContainer.has(plugin.getNamespacedKey(Key.MOB_ID), PersistentDataType.INTEGER);
        if (isMeneciaMob) {
            Mob mob = mobMap.get(getMobId(entity));
            UUID mobUuid = entity.getUniqueId();
            int nodeId = spawnedMap.get(mobUuid);
            int previousEntityInNode = spawnedNodeMap.get(nodeId);
            spawnedNodeMap.put(nodeId, previousEntityInNode - 1);
            int currentEntityInNode = spawnedNodeMap.get(nodeId);
            spawnedMap.remove(mobUuid);
            Log.sendWarning("Despawned 1 " + mob.getName() + " [Lv." + mob.getLevel() + "] "
                    + "(" + currentEntityInNode + "/" + maxiumNodeSpawn + ")");
        }
    }

    public void clearAllMobs() {
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            boolean isMob = dataContainer.has(plugin.getNamespacedKey(Key.MOB_ID), PersistentDataType.INTEGER);
            if (isMob) {
                entity.remove();
            }
        }
    }

    private int getMobId(Entity entity) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        boolean isMob = dataContainer.has(plugin.getNamespacedKey(Key.MOB_ID), PersistentDataType.INTEGER);
        if (isMob) {
            return dataContainer.get(plugin.getNamespacedKey(Key.MOB_ID), PersistentDataType.INTEGER);
        }
        return 0;
    }

    public void stop() {
        spawnerTask.cancel();
    }

}
