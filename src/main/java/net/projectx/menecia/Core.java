package net.projectx.menecia;

import net.projectx.menecia.mobs.MobSpawner;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.mobs.events.MobDamageByBraveEvent;
import net.projectx.menecia.player.events.GeneralPlayerEvent;
import net.projectx.menecia.player.events.LevelingEvent;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class Core extends JavaPlugin {

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
        pluginManager.registerEvents(new GeneralPlayerEvent(this), this);
        pluginManager.registerEvents(new MobDamageByBraveEvent(this), this);
        pluginManager.registerEvents(new LevelingEvent(this), this);
    }

    private void registerManagers() {
        dataManager = new DataManager();
    }

    private void registerMobSystem() {
        MobUtil.registerMobs();
        mobSpawner = new MobSpawner(this);
        mobSpawner.clearAllMobs();
        mobSpawner.start();

    }

    private void unregisterAll() {
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
