package net.projectx.menecia.resources.utilities;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Log {

    private static String prefix = "&f[Menecia]";
    private static ConsoleCommandSender sender = Bukkit.getConsoleSender();

    public static void sendMessage(String message) {
        sender.sendMessage(Utils.translateColor(message));
    }

    public static void sendSuccess(String message) {
        sender.sendMessage(Utils.translateColor(prefix + "&a " + message));
    }

    public static void sendWarning(String message) {
        sender.sendMessage(Utils.translateColor(prefix + "&e " + message));
    }

    public static void sendError(String message) {
        sender.sendMessage(Utils.translateColor(prefix + "&c " + message));
    }

    public static void sendHeaderBanner() {
        Log.sendMessage("");
        Log.sendMessage("&f========================================");
        Log.sendMessage("                [&f&lMenecia]             ");
        Log.sendMessage("        &8< Lets break your key! >        ");
        Log.sendMessage("&f========================================");
        Log.sendMessage("");
    }

    public static void sendFooterBanner() {
        Log.sendMessage("");
        Log.sendMessage("&f========================================");
        Log.sendMessage("");
    }

}
