package net.projectx.menecia.resources;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class Keys {

    private static Plugin plugin;
    public static void setPlugin(Plugin plugin) {
        Keys.plugin = plugin;
    }

    public static final NamespacedKey MOB_ID = new NamespacedKey(plugin, "mobid");

}
