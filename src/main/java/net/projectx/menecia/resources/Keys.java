package net.projectx.menecia.resources;

import net.projectx.menecia.Menecia;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class Keys {

    private static final Plugin plugin = Menecia.getPlugin(Menecia.class);
    public static final NamespacedKey MOB_ID = new NamespacedKey(plugin, "mobid");

}
