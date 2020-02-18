package net.projectx.menecia;

import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.MobSpawner;
import net.projectx.menecia.mobs.events.MobDamageEvent;
import net.projectx.menecia.mobs.events.MobMoveEvent;
import net.projectx.menecia.mobs.events.ResetVanillaMobEvent;
import net.projectx.menecia.player.events.PlayerDamageEvent;
import net.projectx.menecia.player.events.PlayerGeneralEvent;
import net.projectx.menecia.player.events.PlayerLevelingEvent;
import net.projectx.menecia.player.events.ResetVanillaPlayerEvent;
import net.projectx.menecia.utilities.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class Menecia extends JavaPlugin {

    private DataManager dataManager;
    private MobSpawner mobSpawner;

    @Override
    public void onEnable() {
        Log.sendHeaderBanner();

        registerManagers();
        registerMobSystem();
        registerEvents();

        Log.sendFooterBanner();
    }

    @Override
    public void onDisable() {
        Log.sendHeaderBanner();

        unregisterAll();

        Log.sendFooterBanner();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ResetVanillaPlayerEvent(), this);
        pluginManager.registerEvents(new ResetVanillaMobEvent(), this);
        pluginManager.registerEvents(new PlayerGeneralEvent(this), this);
        pluginManager.registerEvents(new PlayerDamageEvent(this), this);
        pluginManager.registerEvents(new MobDamageEvent(this), this);
        pluginManager.registerEvents(new MobMoveEvent(), this);
        pluginManager.registerEvents(new PlayerLevelingEvent(this), this);
    }

    private void registerManagers() {
        dataManager = new DataManager(this);
    }

    private void registerMobSystem() {
        MobManager.registerMobs(this);
        mobSpawner = new MobSpawner(this);
        mobSpawner.clearAllMobs();
        mobSpawner.start();
    }

    private void unregisterAll() {
        dataManager = null;
        mobSpawner.stop();
        mobSpawner = null;
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

    public DataManager getDataManager() {
        return dataManager;
    }

}
