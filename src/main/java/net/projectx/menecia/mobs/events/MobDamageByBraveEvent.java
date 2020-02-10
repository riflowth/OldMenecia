package net.projectx.menecia.mobs.events;

import com.google.common.base.Strings;
import net.projectx.menecia.Core;
import net.projectx.menecia.DataManager;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.player.Brave;
import net.projectx.menecia.resources.Icons;
import net.projectx.menecia.resources.utilities.Hologram;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MobDamageByBraveEvent implements Listener {

    private Core plugin;
    private DataManager dataManager;
    private static final int damageIndicatorShowTick = 20;
    private static final int healthBarScale = 20;
    private Map<LivingEntity, Map<UUID, Double>> damageMap = new HashMap<>();

    public MobDamageByBraveEvent(Core plugin) {
        this.plugin = plugin;
        dataManager = plugin.getDataManager();
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player) && (MobUtil.isMob(event.getEntity()))) {
            event.setCancelled(true);

            Player braveEntity = (Player) event.getDamager();
            Brave brave = dataManager.getBrave(braveEntity);
            Location braveLocation = braveEntity.getLocation();
            LivingEntity mobEntity = (LivingEntity) event.getEntity();
            Mob mob = MobUtil.getMobInstance(mobEntity);
            Location mobLocation = mobEntity.getLocation();
            double mobMaxHealth = mob.getMaxHealth();
            double mobHealth = mobEntity.getHealth();

            if (braveEntity.getInventory().getItemInMainHand().getType().isSolid() ||
                    braveEntity.getInventory().getItemInMainHand().getType().isAir()) {
                event.setDamage(1);
            }

            mobHealth -= event.getDamage();

            displayDamageEffect(mobEntity);
            displayDamageIndicator(event.getDamage(), mobLocation);
            mobEntity.setCustomName(getHealthBar(mobHealth, mobMaxHealth));
            setKnockback(mobEntity, braveEntity);

            if (damageMap.containsKey(mobEntity)) {
                Map<UUID, Double> braveDamagingMap = damageMap.get(mobEntity);
                if (!braveDamagingMap.containsKey(braveEntity.getUniqueId())) {
                    braveDamagingMap.put(braveEntity.getUniqueId(), event.getDamage());
                } else {
                    Double latestDamage = braveDamagingMap.get(braveEntity.getUniqueId());
                    braveDamagingMap.put(braveEntity.getUniqueId(), latestDamage + event.getDamage());
                }
            } else {
                damageMap.put(mobEntity, new HashMap<UUID, Double>() {{
                    put(braveEntity.getUniqueId(), event.getDamage());
                }});
            }

            if (mobHealth <= 0) {
                mobEntity.setHealth(0);
                int totalDamage = (int) damageMap.get(mobEntity).get(braveEntity.getUniqueId()).doubleValue();
                braveEntity.sendActionBar(Utils.translateColor("&4You have took " + totalDamage  + " damage point!"));
                damageMap.remove(mobEntity);
            } else {
                mobEntity.setHealth(mobHealth);
            }
        }
    }

    private String getHealthBar(double currentHealth, double maxHealth) {
        double healthBarValue = (currentHealth / maxHealth) * healthBarScale;
        if (currentHealth <= 0) healthBarValue = 0;
        int heathBarPercentage = (int) Math.round((currentHealth / maxHealth) * 100);

        ChatColor componentColor, indicatorColor;
        if ((heathBarPercentage >= 80) && (heathBarPercentage <= 100)) {
            componentColor = ChatColor.DARK_GREEN;
            indicatorColor = ChatColor.GREEN;
        } else if ((heathBarPercentage >= 60) && (heathBarPercentage < 80)) {
            componentColor = ChatColor.GOLD;
            indicatorColor = ChatColor.YELLOW;
        } else if ((heathBarPercentage >= 40) && (heathBarPercentage < 60)) {
            componentColor = ChatColor.RED;
            indicatorColor = ChatColor.GOLD;
        } else {
            componentColor = ChatColor.DARK_RED;
            indicatorColor = ChatColor.RED;
        }

        String healthBar = Strings.repeat(indicatorColor + "|", (int) healthBarValue) +
                Strings.repeat(ChatColor.GRAY + "|", (int) (healthBarScale - healthBarValue));
        return componentColor + "[" + healthBar + componentColor + "]";
    }

    private void displayDamageIndicator(double damage, Location location) {
        double x = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        double y = ThreadLocalRandom.current().nextDouble(0, 1.5);
        double z = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        Entity damageIndicator = Hologram.draw("&c-" + (int) damage + " &4" + Icons.RED_HEART,
                location.toCenterLocation().add(x, y, z));
        plugin.runTaskLater(damageIndicator::remove, damageIndicatorShowTick);
    }

    private void displayDamageEffect(Entity entity) {
        entity.playEffect(EntityEffect.HURT);
        entity.getLocation().getWorld().spawnParticle(Particle.DAMAGE_INDICATOR,
                entity.getLocation().toCenterLocation(), 4, 0.0, 0.0, 0.0, 0.3);
    }

    private void setKnockback(Entity victim, Entity damager) {
        Location knowbackLocation = damager.getLocation().clone();
        knowbackLocation.setPitch(0);
        victim.setVelocity(knowbackLocation.getDirection().normalize().multiply(0.05));
        if (victim.isOnGround()) {
            victim.setVelocity(victim.getVelocity().setY(0.1));
        }
    }

}
