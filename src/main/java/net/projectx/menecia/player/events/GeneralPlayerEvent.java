package net.projectx.menecia.player.events;

import net.md_5.bungee.api.ChatColor;
import net.projectx.menecia.Menecia;
import net.projectx.menecia.player.BraveManager;
import net.projectx.menecia.utilities.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralPlayerEvent implements Listener {

    private BraveManager braveManager;

    public GeneralPlayerEvent(Menecia plugin) {
        braveManager = plugin.getDataManager().getBraveManager();
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        braveManager.addBrave(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        braveManager.removeBrave(event.getPlayer());
    }

    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(Utils.color("&f" + event.getPlayer().getDisplayName() + " &8> &7" +
                event.getMessage().replaceAll("%", "%%")));
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

}
