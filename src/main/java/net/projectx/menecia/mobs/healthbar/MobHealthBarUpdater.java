package net.projectx.menecia.mobs.healthbar;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobHealthBarUpdater extends BukkitRunnable {

    private Map<UUID, MobHealthBar> playerUuidToMobHealthBar = new HashMap<>();
    private Map<UUID, Long> playerUuidToTimestamp = new HashMap<>();
    private static final int maximumTime = 10;
    public static final int UPDATE_PERIOD = 1;

    public void update(Player player, MobHealthBar mobHealthBar) {
        playerUuidToMobHealthBar.put(player.getUniqueId(), mobHealthBar);
        playerUuidToTimestamp.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public void remove(Player player) {
        playerUuidToMobHealthBar.remove(player.getUniqueId());
        playerUuidToTimestamp.remove(player.getUniqueId());
    }

    @Override
    public void run() {
        playerUuidToTimestamp.forEach((uuid, latestTimestamp) -> {
            long currentTimestamp = System.currentTimeMillis();
            if (((currentTimestamp - latestTimestamp) / 1000) >= maximumTime) {
                playerUuidToMobHealthBar.get(uuid).hide(uuid);
                playerUuidToMobHealthBar.remove(uuid);
                playerUuidToTimestamp.remove(uuid);
            }
        });
    }

}
