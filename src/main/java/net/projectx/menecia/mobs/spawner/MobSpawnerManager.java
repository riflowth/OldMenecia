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
import java.util.concurrent.ThreadLocalRandom;

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
                    int spawnCount = getSpawnCount(spawner);
                    for (int i = 0; i < spawnCount; i++) {
                        Location spawningLocation = spawner.getSpawningArea().getRandomLocation();
                        int entityId = MobUtil.spawn(spawner.getMob(), spawningLocation).getEntityId();
                        entityMap.put(entityId, spawner);
                        spawner.increaseCurrentAmount();
                        Log.sendSuccess("Spawned 1 " + MobUtil.getDisplayNameWithLevel(spawner.getMob())
                                + " &6(" + spawner.getCurrentAmount() + "/" + spawner.getMaximumAmount() + ")");
                    }
                }
            }

        }, 0, Utils.TICK_PER_SEC);
    }

    public boolean canSpawn(MobSpawner spawner) {
        boolean isChunkLoaded = spawner.getSpawningArea().getCenter().isChunkLoaded();
        boolean isAmountAvailable = (spawner.getCurrentAmount() < spawner.getMaximumAmount());

        boolean isRightTime = false;
        long latestTimeStamp = spawner.getLatestSpawnTimestamp();
        if (latestTimeStamp == 0) {
            isRightTime = true;
            spawner.setLatestSpawnTimestamp(System.currentTimeMillis());
        } else {
            long cooldown = getDefaultCooldown(spawner) * 1000;
            if ((latestTimeStamp + cooldown) >= System.currentTimeMillis()) {
                isRightTime = true;
                spawner.setLatestSpawnTimestamp(System.currentTimeMillis());
            } else {
                long timeLeft = (latestTimeStamp + cooldown) / 1000 - System.currentTimeMillis() / 1000;
                System.out.println("Wait for " + timeLeft + " sec");
            }
        }

        return isChunkLoaded && isAmountAvailable && isRightTime;
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

    public int getSpawnCount(MobSpawner spawner) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        switch (spawner.getSpawnRate()) {
            case ABUNDANT:
                return random.nextInt(2, 4);
            case COMMON:
                return random.nextInt(1, 3);
            default:
                return 1;
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

    }

    public void addSpawner(MobSpawner spawner) {
        spawnerList.add(spawner);
        MobSpawnerConfig config = plugin.getConfigs().getMobSpawnerConfig();
    }

    public void removeSpawner(MobSpawner spawner) {
        spawnerList.remove(spawner);
    }

    public void stop() {
        spawnerTask.cancel();
    }

}
