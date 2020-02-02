package net.projectx.menecia.mobs.events;

import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.Mobs;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDamageByBraveEvent implements Listener {

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        if (Mobs.isMob(victim)) {
            int mobId = Mobs.getId(victim);
            Mob mob = Mobs.get(mobId);

            // TODO: Monster Health Bubble!
        }
    }

}
