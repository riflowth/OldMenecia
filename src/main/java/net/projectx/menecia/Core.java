package net.projectx.menecia;

import net.projectx.menecia.entity.npc.citizen.DoraCitizen;
import net.projectx.menecia.player.events.GeneralPlayerEvent;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private DataManager dataManager;

    @Override
    public void onEnable() {
        Log.sendHeaderBanner();

        registerEvents();
        dataManager = new DataManager();

        DoraCitizen test = new DoraCitizen();
        test.spawn(new Location(Bukkit.getWorlds().get(0), -436, 48, -274));
        test.follow(Bukkit.getPlayer("RiFlowTH"));

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
