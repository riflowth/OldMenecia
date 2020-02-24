package net.projectx.menecia;

import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerEvent implements Listener {

    // Need prefix space for centering MOTD
    private static final String motd = "                &7< The Age of &f&lMenecia &7>\n" +
            "        &8Let's get started the new ADVENTURE!";

    @EventHandler
    private void onPing(ServerListPingEvent event) {
        int playerCount = Bukkit.getOnlinePlayers().size();
        event.setMaxPlayers(playerCount + 1);
        event.setMotd(Utils.color(motd));
    }

}
