package net.projectx.menecia.mobs.healthbar;

import net.projectx.menecia.Menecia;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobHealthBarManager {

    private Map<LivingEntity, MobHealthBar> livingEntityToHealthBar = new HashMap<>();
    private Map<UUID, MobHealthBar> uuidToMobHealthBar = new HashMap<>();
    private MobHealthBarUpdater mobHealthBarUpdater;

    public MobHealthBarManager(Menecia plugin) {
        this.mobHealthBarUpdater = new MobHealthBarUpdater();
        plugin.runTaskTimerAsynchronously(mobHealthBarUpdater, 0,
                20 * MobHealthBarUpdater.UPDATE_PERIOD);
    }

    public void showHealthBar(Player player, LivingEntity mobEntity) {
        livingEntityToHealthBar.putIfAbsent(mobEntity, new MobHealthBar(mobEntity));
        MobHealthBar mobHealthBar = livingEntityToHealthBar.get(mobEntity);
        cacheHealthBarForPlayer(player, mobHealthBar);
        if (!mobHealthBar.hasShowTo(player)) mobHealthBar.show(player);
        mobHealthBar.update();
        mobHealthBarUpdater.update(player, mobHealthBar);
    }

    public void removeHealthBar(LivingEntity mobEntity) {
        if (livingEntityToHealthBar.containsKey(mobEntity)) livingEntityToHealthBar.get(mobEntity).hideAll();
        livingEntityToHealthBar.remove(mobEntity);
    }

    private void cacheHealthBarForPlayer(Player player, MobHealthBar newHealthBar) {
        if (uuidToMobHealthBar.get(player.getUniqueId()) != null) {
            if (uuidToMobHealthBar.get(player.getUniqueId()) != newHealthBar) {
                uuidToMobHealthBar.get(player.getUniqueId()).hide(player);
            }
        }
        uuidToMobHealthBar.put(player.getUniqueId(), newHealthBar);
    }

}
