package net.projectx.menecia;

import net.projectx.menecia.player.BraveManager;
import net.projectx.menecia.managers.MobHealthBarManager;
import net.projectx.menecia.utilities.Log;

public class DataManager {

    private BraveManager braveManager;
    private MobHealthBarManager mobHealthBarManager;

    DataManager(Menecia plugin) {
        braveManager = new BraveManager();
        mobHealthBarManager = new MobHealthBarManager(plugin);
        Log.sendSuccess("DataManager has created");
    }

    public BraveManager getBraveManager() {
        return braveManager;
    }

    public MobHealthBarManager getMobHealthBarManager() {
        return mobHealthBarManager;
    }

}
