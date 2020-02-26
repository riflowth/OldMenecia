package net.projectx.menecia.mobs.events;

import net.projectx.menecia.mobs.MobUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.SlimeSplitEvent;

public class ResetVanillaMobEvents implements Listener {

    @EventHandler
    private void resetVanillaDamage(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player) && (MobUtil.isMob(event.getEntity()))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void resetVanillaCombust(EntityCombustEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void resetVanillaSplitting(SlimeSplitEvent event) {
        event.setCancelled(true);
    }

}
