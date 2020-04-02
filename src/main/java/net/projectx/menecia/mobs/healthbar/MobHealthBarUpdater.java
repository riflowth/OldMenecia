package net.projectx.menecia.mobs.healthbar;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MobHealthBarUpdater extends BukkitRunnable {

    private Map<UUID, MobHealthBar> playerUuidToMobHealthBar = new HashMap<>();
    private Map<UUID, Long> playerUuidToTimestamp = new HashMap<>();
    private List<UUID> timeoutList = new ArrayList<>();
    private static final int maximumTime = 10;
    public static final int UPDATE_PERIOD = 1;

    public void update(Player player, MobHealthBar mobHealthBar) {
        playerUuidToMobHealthBar.put(player.getUniqueId(), mobHealthBar);
        playerUuidToTimestamp.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public void remove(UUID playerUuid) {
        playerUuidToMobHealthBar.get(playerUuid).hide(playerUuid);
        playerUuidToMobHealthBar.remove(playerUuid);
        playerUuidToTimestamp.remove(playerUuid);
    }

    @Override
    public void run() {
        playerUuidToTimestamp.forEach((uuid, latestTimestamp) -> {
            long currentTimestamp = System.currentTimeMillis();
            if (((currentTimestamp - latestTimestamp) / 1000) >= maximumTime) {
                timeoutList.add(uuid);
            }
        });
        timeoutList.forEach(this::remove);
        timeoutList.clear();
    }

}
