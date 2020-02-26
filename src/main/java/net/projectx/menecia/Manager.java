package net.projectx.menecia;

import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.healthbar.MobHealthBarManager;
import net.projectx.menecia.mobs.spawner.MobSpawnerManager;
import net.projectx.menecia.player.PlayerManager;
import net.projectx.menecia.player.events.admin.mobspawner.MobSpawnerSetUpManager;

public class Manager {

    private PlayerManager playerManager;
    private MobSpawnerManager mobSpawnerManager;
    private MobHealthBarManager mobHealthBarManager;
    private MobSpawnerSetUpManager mobSpawnerSetUpManager;

    Manager(Menecia plugin) {
        MobManager.register();
        playerManager = new PlayerManager();
        mobHealthBarManager = new MobHealthBarManager(plugin);
        mobSpawnerManager = new MobSpawnerManager(plugin);
        mobSpawnerSetUpManager = new MobSpawnerSetUpManager(plugin);
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

    public MobSpawnerSetUpManager getMobSpawnerSetUpManager() {
        return mobSpawnerSetUpManager;
    }

}
