package net.projectx.menecia;

import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.healthbar.MobHealthBarManager;
import net.projectx.menecia.mobs.spawner.MobSpawnerManager;
import net.projectx.menecia.player.PlayerWrapperManager;
import net.projectx.menecia.player.events.admin.mobspawner.MobSpawnerSetUpManager;
import net.projectx.menecia.player.handlers.SkillHandler;

public class Manager {

    private final PlayerWrapperManager playerWrapperManager;
    private final MobSpawnerManager mobSpawnerManager;
    private final MobHealthBarManager mobHealthBarManager;
    private final MobSpawnerSetUpManager mobSpawnerSetUpManager;
    private final SkillHandler skillHandler;

    Manager(Menecia plugin) {
        MobManager.register();
        this.playerWrapperManager = new PlayerWrapperManager();
        this.mobHealthBarManager = new MobHealthBarManager(plugin);
        this.mobSpawnerManager = new MobSpawnerManager(plugin);
        this.mobSpawnerSetUpManager = new MobSpawnerSetUpManager(plugin);
        this.skillHandler = new SkillHandler();
    }

    public PlayerWrapperManager getPlayerWrapperManager() {
        return playerWrapperManager;
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

    public SkillHandler getSkillHandler() {
        return skillHandler;
    }

}
