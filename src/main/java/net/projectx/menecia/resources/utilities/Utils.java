package net.projectx.menecia.resources.utilities;

import net.md_5.bungee.api.ChatColor;

public class Utils {

    public static String translateColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}