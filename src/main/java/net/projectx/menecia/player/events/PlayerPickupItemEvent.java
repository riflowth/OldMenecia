package net.projectx.menecia.player.events;

import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerPickupItemEvent implements Listener {

    @EventHandler
    private void onEvent(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemStack = event.getItem().getItemStack();
            String dropName = event.getItem().getItemStack().getItemMeta().hasDisplayName()
                    ? event.getItem().getItemStack().getItemMeta().getDisplayName()
                    : event.getItem().getName();
            if (itemStack.getAmount() > 1) {
                player.sendTitle("", Utils.color("&7You have found some " + dropName + "s!"),
                        10, 40, 10);
            } else {
                player.sendTitle("", Utils.color("&7You have found a " + dropName + "!"),
                        10, 40, 10);
            }
        }
    }

}
