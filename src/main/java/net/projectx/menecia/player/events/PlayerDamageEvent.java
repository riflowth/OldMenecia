package net.projectx.menecia.player.events;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.mobs.healthbar.MobHealthBarManager;
import net.projectx.menecia.player.PlayerManager;
import net.projectx.menecia.player.PlayerWrapper;
import net.projectx.menecia.resources.Icons;
import net.projectx.menecia.resources.utilities.Hologram;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class PlayerDamageEvent implements Listener {

    private Menecia plugin;
    private PlayerManager playerManager;
    private MobHealthBarManager mobHealthBarManager;
    private static final int damageIndicatorShowTick = 20;
    private Map<LivingEntity, Map<UUID, Double>> damageMap = new HashMap<>();

    public PlayerDamageEvent(Menecia plugin) {
        this.plugin = plugin;
        this.playerManager = plugin.getManagers().getPlayerManager();
        this.mobHealthBarManager = plugin.getManagers().getMobHealthBarManager();
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player) && (MobUtil.isMob(event.getEntity()))) {
            Player player = (Player) event.getDamager();
            PlayerWrapper playerWrapper = playerManager.getPlayerWrapper(player);
            Location playerLocation = player.getLocation();
            LivingEntity mobEntity = (LivingEntity) event.getEntity();
            Mob mob = MobUtil.getMobInstance(mobEntity);
            Location mobLocation = mobEntity.getLocation();

            //TODO: Attack Range
            if (checkDistance(player, mobEntity) > 2) return;

            double damage = calculateDamage(player);
            updateDamage(player, mobEntity, damage);
            displayDamageEffect(mobEntity);
            displayDamageIndicator(damage, mobLocation);
            takeKnockback(mobEntity, player);

            double mobHealth = mobEntity.getHealth();
            mobHealth -= damage;
            if (mobHealth <= 0) {
                int totalDamage = (int) getTotalDamage(mobEntity, player);
                player.sendActionBar(Utils.color("&4You have took " + totalDamage  + " damage point!"));
                killMob(mobEntity);
            } else {
                mobEntity.setHealth(mobHealth);
                mobHealthBarManager.showHealthBar(player, mobEntity);
            }
        }
    }

    private double calculateDamage(Player damager) {
        if (damager.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
            return 1;
        }
        return 0;
    }

    private double getTotalDamage(LivingEntity mobEntity, Player player) {
        return damageMap.get(mobEntity).get(player.getUniqueId());
    }

    private void updateDamage(Player player, LivingEntity mobEntity, double damage) {
        damageMap.putIfAbsent(mobEntity, new HashMap<UUID, Double>() {{ put(player.getUniqueId(), 0D); }});
        Map<UUID, Double> playerDamagingMap = damageMap.get(mobEntity);
        playerDamagingMap.merge(player.getUniqueId(), damage, Double::sum);
    }

    private double checkDistance(Player player, Entity entity) {
        return player.getLocation().distance(entity.getLocation());
    }

    private void displayDamageIndicator(double damage, Location location) {
        double x = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        double y = ThreadLocalRandom.current().nextDouble(0, 1.5);
        double z = ThreadLocalRandom.current().nextDouble(-1.25, 1.25);
        Entity damageIndicator = Hologram.draw("&c-" + (int) damage + " &4" + Icons.RED_HEART,
                location.toCenterLocation().add(x, y, z));
        plugin.runTaskLater(damageIndicator::remove, damageIndicatorShowTick);
    }

    private void killMob(LivingEntity mobEntity) {
        mobEntity.setHealth(0);
        damageMap.remove(mobEntity);
        mobHealthBarManager.removeHealthBar(mobEntity);
    }

    private void displayDamageEffect(Entity entity) {
        entity.playEffect(EntityEffect.HURT);
        entity.getLocation().getWorld().spawnParticle(Particle.DAMAGE_INDICATOR,
                entity.getLocation().toCenterLocation(), 4, 0.0, 0.0, 0.0, 0.3);
    }

    private void takeKnockback(Entity victim, Entity damager) {
        Location knowbackLocation = damager.getLocation().clone();
        knowbackLocation.setPitch(0);
        victim.setVelocity(knowbackLocation.getDirection().normalize().multiply(0.05));
        if (victim.isOnGround()) {
            victim.setVelocity(victim.getVelocity().setY(0.1));
        }
    }

}
