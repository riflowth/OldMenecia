package net.projectx.menecia.resources.utilities;

import net.md_5.bungee.api.ChatColor;

public class Utils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static <T> int getLength(T[] array){
        int count = 0;
        for (T element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

}
