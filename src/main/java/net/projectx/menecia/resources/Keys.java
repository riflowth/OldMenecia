package net.projectx.menecia.resources;

import net.projectx.menecia.Core;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class Keys {

    private static final Plugin plugin = Core.getPlugin(Core.class);
    public static final NamespacedKey MOB_ID = new NamespacedKey(plugin, "mobid");

}
