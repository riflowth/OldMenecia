package net.projectx.menecia.mobs.events;

import com.google.common.base.Strings;
import net.projectx.menecia.Core;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.resources.Icons;
import net.projectx.menecia.resources.utilities.Hologram;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class MobDamageByBraveEvent implements Listener {

    private Core plugin;
    private static final int damageIndicatorShowTick = 20;
    private static final int healthBarScale = 20;

    public MobDamageByBraveEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if ((damager instanceof Player) && (MobUtil.isMob(event.getEntity()))) {
            LivingEntity victim = (LivingEntity) event.getEntity();
            Mob mob = MobUtil.getMobInstance(victim);

            int damage = (int) event.getDamage();
            event.setDamage(damage);
            displayDamageIndicator(damage, victim.getLocation());
            setHealthBar(victim, (int) victim.getHealth(), mob.getMaxHealth());
        }
    }

    private void setHealthBar(Entity entity, double currentHealth, double maxHealth) {
        double healthBarValue = (currentHealth / maxHealth) * healthBarScale;
        int heathBarPercentage = (int) Math.round((currentHealth / maxHealth) * 100);

        ChatColor componentColor = ChatColor.DARK_RED;
        ChatColor indicatorColor = ChatColor.RED;
        if ((heathBarPercentage >= 80) && (heathBarPercentage <= 100)) {
            componentColor = ChatColor.DARK_GREEN;
            indicatorColor = ChatColor.GREEN;
        } else if ((heathBarPercentage >= 60) && (heathBarPercentage < 80)) {
            componentColor = ChatColor.GOLD;
            indicatorColor = ChatColor.YELLOW;
        } else if ((heathBarPercentage >= 40) && (heathBarPercentage < 60)) {
            componentColor = ChatColor.RED;
            indicatorColor = ChatColor.GOLD;
        } else if ((heathBarPercentage >= 20) && (heathBarPercentage < 40)) {
            componentColor = ChatColor.DARK_RED;
            indicatorColor = ChatColor.RED;
        }

        String healthBar = Strings.repeat(indicatorColor + "|", (int) healthBarValue) +
                Strings.repeat(ChatColor.GRAY + "|", (int) (healthBarScale - healthBarValue));
        String display = componentColor + "[" + healthBar + componentColor + "]";
        entity.setCustomName(display);
    }

    private void displayDamageIndicator(int damage, Location location) {
        double x = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        double y = ThreadLocalRandom.current().nextDouble(0, 1.5);
        double z = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        Entity damageIndicator = Hologram.draw("&c-" + damage + " &4" + Icons.RED_HEART,
                location.toCenterLocation().add(x, y, z));
        plugin.runTaskLater(damageIndicator::remove, damageIndicatorShowTick);
    }

}
