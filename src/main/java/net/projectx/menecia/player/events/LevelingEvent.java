package net.projectx.menecia.player.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class LevelingEvent implements Listener {

    @EventHandler
    private void playerReceivedExp(PlayerExpChangeEvent event) {
        // TODO: Exp mechanism
    }

    @EventHandler
    private void entityDamageByPlayer(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();
        if (damager instanceof Player && !(victim instanceof Player)) {
            Player player = (Player) event.getDamager();

        }
    }

    @EventHandler
    private void removeDropFromDeathEntity(EntityDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

}
