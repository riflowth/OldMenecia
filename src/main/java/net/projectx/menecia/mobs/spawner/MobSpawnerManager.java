package net.projectx.menecia.mobs.spawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.resources.configs.MobSpawnerConfig;
import net.projectx.menecia.resources.utilities.Log;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobSpawnerManager implements Listener {

    private Menecia plugin;
    private BukkitTask spawnerTask;
    private List<MobSpawner> spawnerList = new ArrayList<>();
    private Map<Integer, MobSpawner> entityMap = new HashMap<>();

    public MobSpawnerManager(Menecia plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void start() {
        Log.sendSuccess("Started Mob Spawner!");
        spawnerTask = plugin.runTaskTimer(() -> {
            for (MobSpawner spawner : spawnerList) {
                if (canSpawn(spawner)) {
                    Location spawningLocation = spawner.getSpawningArea().getRandomLocation();
                    int entityId = MobUtil.spawn(spawner.getMob(), spawningLocation).getEntityId();
                    entityMap.put(entityId, spawner);
                    spawner.increaseCurrentAmount();
                    Log.sendSuccess("Spawned 1 " + MobUtil.getDisplayNameWithLevel(spawner.getMob())
                            + " &6(" + spawner.getCurrentAmount() + "/" + spawner.getMaximumAmount() + ")");
                }
            }

        }, 0, 20);
    }

    public boolean canSpawn(MobSpawner spawner) {
        boolean isChunkLoaded = spawner.getSpawningArea().getCenter().isChunkLoaded();
        if (!isChunkLoaded) return false;

        boolean isAmountAvailable = (spawner.getCurrentAmount() < spawner.getMaximumAmount());
        if (!isAmountAvailable) return false;

        boolean isRightTime = false;
        long latestTimeStamp = spawner.getTimestamp();
        long cooldown = getDefaultCooldown(spawner) * 1000;
        if ((latestTimeStamp == 0) || ((latestTimeStamp + cooldown) <= System.currentTimeMillis())) {
            isRightTime = true;
            spawner.setTimestamp(System.currentTimeMillis());
        }
        return isRightTime;
    }

    public int getDefaultCooldown(MobSpawner spawner) {
        switch (spawner.getSpawnRate()) {
            case ABUNDANT:
                return 1;
            case COMMON:
                return 2;
            case RARE:
                return 3;
            case EXTREMELY_RATE:
                return 5;
            case MYTHICAL:
                return 10;
            default:
                return 0;
        }
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
            MobSpawner spawner = entityMap.get(entity.getEntityId());
            spawner.decreaseCurrentAmount();
            Log.sendWarning("Despawned 1 " + MobUtil.getDisplayNameWithLevel(mob)
                    + " &6(" + spawner.getCurrentAmount() + "/" + spawner.getMaximumAmount() + ")");
        }
    }

    public void loadSpawner(MobSpawner spawner) {
        // TODO: Work with Config file!
    }

    public void addSpawner(MobSpawner spawner) {
        spawnerList.add(spawner);
        // TODO: Work with Config file!
        MobSpawnerConfig config = plugin.getConfigs().getMobSpawnerConfig();
    }

    public void removeSpawner(MobSpawner spawner) {
        // TODO: Work with Config file!
        spawnerList.remove(spawner);
    }

    public void stop() {
        spawnerTask.cancel();
    }

}
