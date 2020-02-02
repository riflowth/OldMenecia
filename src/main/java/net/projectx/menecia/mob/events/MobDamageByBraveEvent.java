package net.projectx.menecia.mob.events;

import net.projectx.menecia.mob.Mob;
import net.projectx.menecia.mob.Mobs;
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


        }
    }

}
