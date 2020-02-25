package net.projectx.menecia.player.events;

import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PlayerPickupItemEvent implements Listener {

    @EventHandler
    private void onEvent(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            player.sendTitle("", Utils.color("&a+1 &f" + event.getItem().getName() + " &ato bag"),
                    10, 40, 10);
        }
    }

}
