package net.projectx.menecia;

import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.healthbar.MobHealthBarManager;
import net.projectx.menecia.mobs.spawner.MobSpawnerManager;
import net.projectx.menecia.player.PlayerManager;

public class Manager {

    private PlayerManager playerManager;
    private MobSpawnerManager mobSpawnerManager;
    private MobHealthBarManager mobHealthBarManager;

    Manager(Menecia plugin) {
        MobManager.register();
        playerManager = new PlayerManager();
        mobHealthBarManager = new MobHealthBarManager(plugin);
        mobSpawnerManager = new MobSpawnerManager(plugin);
        mobSpawnerManager.start();
    }


    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public MobSpawnerManager getMobSpawnerManager() {
        return mobSpawnerManager;
    }

    public MobHealthBarManager getMobHealthBarManager() {
        return mobHealthBarManager;
    }

}
