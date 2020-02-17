package net.projectx.menecia.player.tasks;

import net.projectx.menecia.player.bossbars.MobHealthBar;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class MobHealthBarTask extends BukkitRunnable {

    private int maximumTimeInSec = 10;
    private UUID playerUuid;
    private MobHealthBar mobHealthBar;

    public MobHealthBarTask(UUID playerUuid, MobHealthBar mobHealthBar) {
        this.playerUuid = playerUuid;
        this.mobHealthBar = mobHealthBar;
    }

    @Override
    public void run() {
        maximumTimeInSec--;
        if (maximumTimeInSec == 0) {
            mobHealthBar.hide(playerUuid);
            cancel();
        }
    }

}
