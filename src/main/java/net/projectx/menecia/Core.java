package net.projectx.menecia;

import net.projectx.menecia.mob.MobSpawner;
import net.projectx.menecia.player.events.GeneralPlayerEvent;
import net.projectx.menecia.player.events.LevelingEvent;
import net.projectx.menecia.resources.Key;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class Core extends JavaPlugin {

    private DataManager dataManager;
    private Map<Key, NamespacedKey> namespacedKeyMap = new HashMap<>();
    private MobSpawner mobSpawner;

    @Override
    public void onEnable() {
        Log.sendHeaderBanner();

        registerNamespacedKeys();
        dataManager = new DataManager();

        mobSpawner = new MobSpawner(this);
        mobSpawner.registerMobs();
        mobSpawner.clearAllMobs();
        mobSpawner.start();

        registerEvents();

        Log.sendFooterBanner();
    }

    @Override
    public void onDisable() {
        Log.sendHeaderBanner();

        namespacedKeyMap = null;
        dataManager = null;

        mobSpawner.stop();
        mobSpawner = null;

        Log.sendFooterBanner();
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

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new GeneralPlayerEvent(this), this);
        pluginManager.registerEvents(new LevelingEvent(), this);
    }

    private void registerNamespacedKeys() {
        namespacedKeyMap.put(Key.MOB_ID, new NamespacedKey(this, "MobID"));
    }

    public NamespacedKey getNamespacedKey(Key key) {
        return namespacedKeyMap.get(key);
    }

    public DataManager getDataManager() {
        return dataManager;
    }

}
