package net.projectx.menecia.mobs.events;

import net.projectx.menecia.Core;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.resources.Icons;
import net.projectx.menecia.resources.utilities.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class MobDamageByBraveEvent implements Listener {

    private Core plugin;
    private static final int damageIndicatorShowTick = 10;

    public MobDamageByBraveEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();
        if ((damager instanceof Player) && (MobUtil.isMob(victim))) {
            int mobId = MobUtil.getId(victim);
            Mob mob = MobUtil.get(mobId);
            int damage = (int) event.getDamage();
            Location victimLocation = victim.getLocation();
            displayDamageHologram(damage, victimLocation);
        }
    }

    private void displayDamageHologram(int damage, Location location) {
        double x = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        double y = ThreadLocalRandom.current().nextDouble(0, 1.5);
        double z = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        Hologram.drawTemporary("&c-" + damage + " &4" + Icons.RED_HEART, damageIndicatorShowTick,
                location.toCenterLocation().add(x, y, z));
    }

}
