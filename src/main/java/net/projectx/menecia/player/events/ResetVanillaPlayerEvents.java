package net.projectx.menecia.player.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ResetVanillaPlayerEvents implements Listener {

    @EventHandler
    private void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if ((event.getItem() != null) && (event.getItem().getType() == Material.WOODEN_SWORD)) {
                displayDamageEffect(event);
            }
        }
    }

    private void displayDamageEffect(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        Location particleLocation;
        //TODO: Attack Range
        particleLocation = player.getEyeLocation().clone().add(playerLocation.getDirection().normalize().multiply(2)).add(0, -0.25, 0);
        playerLocation.getWorld().spawnParticle(Particle.SWEEP_ATTACK, particleLocation, 1);
        player.playSound(playerLocation, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.8F, 0F);
    }

}
