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

    private Core plugin;
    private DataManager dataManager;

    public GeneralPlayerEvent(Core plugin) {
        this.plugin = plugin;
        dataManager = plugin.getDataManager();
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        dataManager.addBrave(e.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        dataManager.addBrave(e.getPlayer());
    }


    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setFormat(Utils.translateColor("&f" + e.getPlayer().getDisplayName() + " &8> &7" +
                e.getMessage().replaceAll("%", "%%")));
        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }

}
