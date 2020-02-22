package net.projectx.menecia;

import net.projectx.menecia.player.PlayerManager;
import net.projectx.menecia.mobs.healthbar.MobHealthBarManager;
import net.projectx.menecia.utilities.Log;

public class DataManager {

    private PlayerManager playerManager;
    private MobHealthBarManager mobHealthBarManager;

    DataManager(Menecia plugin) {
        playerManager = new PlayerManager();
        mobHealthBarManager = new MobHealthBarManager(plugin);
        Log.sendSuccess("DataManager has created");
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public MobHealthBarManager getMobHealthBarManager() {
        return mobHealthBarManager;
    }

}
