package net.projectx.menecia.mobs.events;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobType;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.player.PlayerWrapper;
import net.projectx.menecia.player.PlayerWrapperManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDamageEvent implements Listener {

    private PlayerWrapperManager playerWrapperManager;

    public MobDamageEvent(Menecia plugin) {
        this.playerWrapperManager = plugin.getManagers().getPlayerWrapperManager();
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        if ((MobUtil.isMob(event.getDamager())) && (event.getEntity() instanceof Player)) {
            if (MobUtil.getMobInstance(event.getDamager()).getMobType() == MobType.HOSTILE) {
                Player player = (Player) event.getEntity();
                PlayerWrapper playerWrapper = playerWrapperManager.getPlayerWrapper(player);
                LivingEntity mobEntity = (LivingEntity) event.getDamager();
                Mob mob = MobUtil.getMobInstance(mobEntity);

                mob.attack(player);
            }
        }
    }

}
