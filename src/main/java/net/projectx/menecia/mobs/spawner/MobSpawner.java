package net.projectx.menecia.mobs.spawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.locations.Area;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.utilities.Log;
import net.projectx.menecia.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class MobSpawner implements Listener {

    private Menecia plugin;
    private BukkitTask spawnerTask;
    private int maxiumNodeSpawn = 5;
    private Map<Mob, Integer> spawnedCountMap = new HashMap<>();

    private static final World world = Bukkit.getWorlds().get(0);
    private static final Location firstLocation = new Location(world, -378, 69, -336);
    private static final Location secondLocation = new Location(world, -383, 70, -324);
    public static final Area spawnArea = new Area(firstLocation, secondLocation);

    public MobSpawner(Menecia plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void start() {
        Log.sendSuccess("Started Mob Spawner!");
        spawnerTask = plugin.runTaskTimer(() -> {
            for (Mob mob : MobManager.getAllMobs()) {
                spawnedCountMap.putIfAbsent(mob, 0);
                if (spawnedCountMap.get(mob) < maxiumNodeSpawn) {
                    Location spawnLocation = spawnArea.getRandomLocation();
                    while (spawnLocation.getBlock().getType() != Material.AIR) {
                        spawnLocation.add(0, 1, 0);
                    }
                    MobUtil.spawn(mob, spawnLocation);
                    spawnedCountMap.put(mob, spawnedCountMap.get(mob) + 1);
                    Log.sendSuccess("Spawned 1 " + MobUtil.getDisplayNameWithLevel(mob)
                            + " &6(" + spawnedCountMap.get(mob) + "/" + maxiumNodeSpawn + ")");
                }
            }
        }, 0, Utils.TICK_PER_SEC * 10);
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


    public void stop() {
        spawnerTask.cancel();
    }

}
