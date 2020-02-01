package net.projectx.menecia.player.events;

import net.md_5.bungee.api.ChatColor;
import net.projectx.menecia.Core;
import net.projectx.menecia.DataManager;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralPlayerEvent implements Listener {

    private DataManager dataManager;

    public GeneralPlayerEvent(Core plugin) {
        dataManager = plugin.getDataManager();
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        dataManager.addBrave(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        dataManager.removeBrave(event.getPlayer());
    }


    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(Utils.translateColor("&f" + event.getPlayer().getDisplayName() + " &8> &7" +
                event.getMessage().replaceAll("%", "%%")));
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

}