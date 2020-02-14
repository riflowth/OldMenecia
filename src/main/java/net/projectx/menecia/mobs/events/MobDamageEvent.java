package net.projectx.menecia.mobs.events;

import net.projectx.menecia.Core;
import net.projectx.menecia.DataManager;
import net.projectx.menecia.mobs.HostileMob;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.player.Brave;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDamageEvent implements Listener {

    private Core plugin;
    private DataManager dataManager;

    public MobDamageEvent(Core plugin) {
        this.plugin = plugin;
        dataManager = plugin.getDataManager();
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        if ((MobUtil.isMob(event.getDamager())) && (event.getEntity() instanceof Player)) {
            if (MobUtil.getMobInstance(event.getDamager()) instanceof HostileMob) {
                Player braveEntity = (Player) event.getEntity();
                Brave brave = dataManager.getBrave(braveEntity);
                LivingEntity mobEntity = (LivingEntity) event.getDamager();
                HostileMob mob = (HostileMob) MobUtil.getMobInstance(mobEntity);

                mob.attack(braveEntity);
            }
        }
    }

}
