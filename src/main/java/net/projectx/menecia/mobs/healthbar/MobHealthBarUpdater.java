package net.projectx.menecia.mobs.healthbar;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobHealthBarUpdater extends BukkitRunnable {

    private Map<UUID, MobHealthBar> uuidToMobHealthBar = new HashMap<>();
    private Map<UUID, Long> uuidToTimestamp = new HashMap<>();
    private static final int maximumTime = 10;
    public static final int UPDATE_PERIOD = 1;

    public void update(Player player, MobHealthBar mobHealthBar) {
        uuidToMobHealthBar.put(player.getUniqueId(), mobHealthBar);
        uuidToTimestamp.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public void remove(Player player) {
        uuidToMobHealthBar.remove(player.getUniqueId());
        uuidToTimestamp.remove(player.getUniqueId());
    }

    @Override
    public void run() {
        uuidToTimestamp.forEach((uuid, latestTimestamp) -> {
            long currentTimestamp = System.currentTimeMillis();
            if (((currentTimestamp - latestTimestamp) / 1000) >= maximumTime) {
                uuidToMobHealthBar.get(uuid).hide(uuid);
                uuidToMobHealthBar.remove(uuid);
                uuidToTimestamp.remove(uuid);
            }
        });
    }

}
