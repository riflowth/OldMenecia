package net.projectx.menecia.player.events;

import net.projectx.menecia.Core;
import net.projectx.menecia.resources.Icons;
import net.projectx.menecia.resources.utilities.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class LevelingEvent implements Listener {

    private Core plugin;

    public LevelingEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void playerReceivedExp(PlayerExpChangeEvent event) {
        // TODO: Exp mechanism
    }

    @EventHandler
    private void entityDamageByPlayer(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();
        if (damager instanceof Player && !(victim instanceof Player)) {
            int damage = (int) event.getDamage();
            Location victimLocation = victim.getLocation();
            displayDamageHologram(damage, victimLocation);
        }
    }

    private void displayDamageHologram(int damage, Location location) {
        double x = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        double y = ThreadLocalRandom.current().nextDouble(0, 1.5);
        double z = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        Hologram.drawTemporary("&c-" + damage + " &4" + Icons.RED_HEART, 10,
                location.toCenterLocation().add(x, y, z));
    }

    @EventHandler
    private void removeDropFromDeathEntity(EntityDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

}
