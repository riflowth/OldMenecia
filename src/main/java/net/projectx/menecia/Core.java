package net.projectx.menecia;

import net.projectx.menecia.player.events.GeneralPlayerEvent;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private DataManager dataManager;

    @Override
    public void onEnable() {
        Log.sendHeaderBanner();

        registerEvents();
        dataManager = new DataManager();

        Log.sendFooterBanner();
    }

    @Override
    public void onDisable() {
        Log.sendHeaderBanner();

        dataManager = null;

        Log.sendFooterBanner();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new GeneralPlayerEvent(this), this);
    }

    public DataManager getDataManager() {
        return dataManager;
    }

}
