package net.projectx.menecia.player.events;

import net.md_5.bungee.api.ChatColor;
import net.projectx.menecia.Menecia;
import net.projectx.menecia.player.PlayerManager;
import net.projectx.menecia.player.TabListManager;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerGeneralEvent implements Listener {

    private Menecia plugin;
    private PlayerManager playerManager;

    public PlayerGeneralEvent(Menecia plugin) {
        this.plugin = plugin;
        playerManager = plugin.getManager().getPlayerManager();
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerManager.add(player);
        TabListManager.initialize(player);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        playerManager.remove(event.getPlayer());
    }

    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(Utils.color("&f" + event.getPlayer().getDisplayName() + " &8> &7" +
                event.getMessage().replaceAll("%", "%%")));
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

}
