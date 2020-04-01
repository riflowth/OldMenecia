package net.projectx.menecia.player.events;

import net.md_5.bungee.api.ChatColor;
import net.projectx.menecia.Menecia;
import net.projectx.menecia.player.PlayerStatusBarUpdater;
import net.projectx.menecia.player.PlayerWrapperManager;
import net.projectx.menecia.player.TabListManager;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerGeneralEvent implements Listener {

    private Menecia plugin;
    private PlayerWrapperManager playerWrapperManager;
    private PlayerStatusBarUpdater playerStatusBarUpdater;
    private static final int joiningEffectTime = 3;

    public PlayerGeneralEvent(Menecia plugin) {
        this.plugin = plugin;
        this.playerWrapperManager = plugin.getManagers().getPlayerWrapperManager();
        this.playerStatusBarUpdater = new PlayerStatusBarUpdater();
        this.plugin.runTaskTimerAsynchronously(playerStatusBarUpdater, 0, 5);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();

        playerWrapperManager.add(player);
        playerStatusBarUpdater.update(player);
        TabListManager.initialize(player);

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * joiningEffectTime,
                1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * joiningEffectTime,
                1000000, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * joiningEffectTime,
                -1000000, false, false, false));
        plugin.runTaskLater(() -> player.sendTitle("", Utils.color("&7Are you alright?"),
                10, 20 * 2, 20), 20 * joiningEffectTime);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();

        playerWrapperManager.remove(player);
        playerStatusBarUpdater.remove(player);
    }

    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(Utils.color("&f" + event.getPlayer().getDisplayName() + " &8> &7" +
                event.getMessage().replaceAll("%", "%%")));
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

}
