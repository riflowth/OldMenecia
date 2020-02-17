package net.projectx.menecia.utilities;

import net.md_5.bungee.api.ChatColor;

public class Utils {

    public static final int TICK_PER_SEC = 20;

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
