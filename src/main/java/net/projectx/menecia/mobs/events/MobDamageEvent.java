package net.projectx.menecia.mobs.events;

import net.projectx.menecia.DataManager;
import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobType;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.player.Brave;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDamageEvent implements Listener {

    private Menecia plugin;
    private DataManager dataManager;

    public MobDamageEvent(Menecia plugin) {
        this.plugin = plugin;
        dataManager = plugin.getDataManager();
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        if ((MobUtil.isMob(event.getDamager())) && (event.getEntity() instanceof Player)) {
            if (MobUtil.getMobInstance(event.getDamager()).getMobType() == MobType.HOSTILE) {
                Player braveEntity = (Player) event.getEntity();
                Brave brave = dataManager.getBrave(braveEntity);
                LivingEntity mobEntity = (LivingEntity) event.getDamager();
                Mob mob = MobUtil.getMobInstance(mobEntity);

                mob.attack(braveEntity);
            }
        }
    }

}
