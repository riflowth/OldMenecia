package net.projectx.menecia;

import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.events.MobDamageEvent;
import net.projectx.menecia.mobs.events.MobDeathEvent;
import net.projectx.menecia.mobs.events.MobMoveEvent;
import net.projectx.menecia.mobs.events.ResetVanillaMobEvent;
import net.projectx.menecia.player.events.*;
import net.projectx.menecia.player.events.admin.mobspawner.AdminPlaceMobSpawner;
import net.projectx.menecia.player.guis.GUIListener;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class Menecia extends JavaPlugin {

    private Manager manager;
    private Config config;

    @Override
    public void onEnable() {
        Log.sendHeaderBanner();

        registerConfigs();
        registerManagers();
        registerEvents();

        MobManager.killAllMobs();

        Log.sendFooterBanner();
    }

    @Override
    public void onDisable() {
        Log.sendHeaderBanner();

        unregisterAll();

        Log.sendFooterBanner();
    }

    private void unregisterAll() {
        manager.getMobSpawnerManager().stop();
        manager = null;
        config = null;
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ServerEvent(), this);
        pluginManager.registerEvents(new ResetVanillaPlayerEvent(), this);
        pluginManager.registerEvents(new ResetVanillaMobEvent(), this);
        pluginManager.registerEvents(new GUIListener(), this);
        pluginManager.registerEvents(new AdminPlaceMobSpawner(this), this);
        pluginManager.registerEvents(new PlayerGeneralEvent(this), this);
        pluginManager.registerEvents(new PlayerDamageEvent(this), this);
        pluginManager.registerEvents(new PlayerPickupItemEvent(), this);
        pluginManager.registerEvents(new MobDamageEvent(this), this);
        pluginManager.registerEvents(new MobDeathEvent(), this);
        pluginManager.registerEvents(new MobMoveEvent(), this);
        pluginManager.registerEvents(new PlayerLevelingEvent(this), this);
    }

    private void registerConfigs() {
        config = new Config(this);
    }

    public Config getConfigs() {
        return config;
    }

    private void registerManagers() {
        manager = new Manager(this);
    }

    public Manager getManagers() {
        return manager;
    }

    public BukkitScheduler getScheduler() {
        return this.getServer().getScheduler();
    }

    public BukkitTask runTaskLater(Runnable runnable, long delay) {
        return this.getScheduler().runTaskLater(this, runnable, delay);
    }

    public BukkitTask runTaskTimer(Runnable runnable, long delay, long period) {
        return this.getScheduler().runTaskTimer(this, runnable, delay, period);
    }

    public BukkitTask runTaskLaterAsynchronously(Runnable runnable, long delay) {
        return this.getScheduler().runTaskLaterAsynchronously(this, runnable, delay);
    }

    public BukkitTask runTaskTimerAsynchronously(Runnable runnable, long delay, long period) {
        return this.getScheduler().runTaskTimerAsynchronously(this, runnable, delay, period);
    }

}
