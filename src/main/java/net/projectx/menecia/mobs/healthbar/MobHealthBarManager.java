package net.projectx.menecia.mobs.healthbar;

import net.projectx.menecia.Menecia;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobHealthBarManager {

    private Map<LivingEntity, MobHealthBar> healthBarMap = new HashMap<>();
    private Map<UUID, MobHealthBar> healthBarCache = new HashMap<>();
    private MobHealthBarUpdater mobHealthBarUpdater;

    public MobHealthBarManager(Menecia plugin) {
        this.mobHealthBarUpdater = new MobHealthBarUpdater();
        plugin.runTaskTimerAsynchronously(mobHealthBarUpdater, 0,
                20 * MobHealthBarUpdater.UPDATE_PERIOD);
    }

    public void showHealthBar(Player player, LivingEntity mobEntity) {
        healthBarMap.putIfAbsent(mobEntity, new MobHealthBar(mobEntity));
        MobHealthBar mobHealthBar = healthBarMap.get(mobEntity);
        cacheHealthBarForPlayer(player, mobHealthBar);
        if (!mobHealthBar.hasShowTo(player)) mobHealthBar.show(player);
        mobHealthBar.update();
        mobHealthBarUpdater.update(player, mobHealthBar);
    }

    public void removeHealthBar(LivingEntity mobEntity) {
        if (healthBarMap.containsKey(mobEntity)) healthBarMap.get(mobEntity).hideAll();
        healthBarMap.remove(mobEntity);
    }

    private void cacheHealthBarForPlayer(Player player, MobHealthBar newHealthBar) {
        if (healthBarCache.get(player.getUniqueId()) != null) {
            if (healthBarCache.get(player.getUniqueId()) != newHealthBar) {
                healthBarCache.get(player.getUniqueId()).hide(player);
            }
        }
        healthBarCache.put(player.getUniqueId(), newHealthBar);
    }

}
