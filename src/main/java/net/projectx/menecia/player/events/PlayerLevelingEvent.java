package net.projectx.menecia.player.events;

import net.projectx.menecia.Menecia;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerLevelingEvent implements Listener {

    private Menecia plugin;

    public PlayerLevelingEvent(Menecia plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void playerReceivedExp(PlayerExpChangeEvent event) {
        // TODO: Exp mechanism
    }

    @EventHandler
    private void mobDamageByPlayer(EntityDamageByEntityEvent event) {
        // TODO: Exp mechanism
    }

    @EventHandler
    private void removeDropFromDeathEntity(EntityDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

}
