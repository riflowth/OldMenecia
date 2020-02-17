package net.projectx.menecia.managers;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.player.bossbars.MobHealthBar;
import net.projectx.menecia.player.tasks.MobHealthBarTask;
import net.projectx.menecia.utilities.Utils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobHealthBarManager {

    private Menecia plugin;
    private Map<LivingEntity, MobHealthBar> healthBarMap = new HashMap<>();
    private Map<UUID, MobHealthBar> healthBarCache = new HashMap<>();
    private Map<UUID, MobHealthBarTask> taskMap = new HashMap<>();

    public MobHealthBarManager(Menecia plugin) {
        this.plugin = plugin;
    }

    public void showHealthBar(Player braveEntity, LivingEntity mobEntity) {
        healthBarMap.putIfAbsent(mobEntity, new MobHealthBar(mobEntity));
        MobHealthBar mobHealthBar = healthBarMap.get(mobEntity);
        cacheHealthBarForPlayer(braveEntity, mobHealthBar);
        if (!mobHealthBar.hasShowTo(braveEntity)) mobHealthBar.show(braveEntity);
        mobHealthBar.update();
        updateMobHealBarTask(braveEntity, mobHealthBar);
    }

    public void updateMobHealBarTask(Player braveEntity, MobHealthBar mobHealthBar) {
        if (taskMap.get(braveEntity.getUniqueId()) != null) taskMap.get(braveEntity.getUniqueId()).cancel();
        MobHealthBarTask mobHealthBarTask = new MobHealthBarTask(braveEntity.getUniqueId(), mobHealthBar);
        mobHealthBarTask.runTaskTimerAsynchronously(plugin, 0, Utils.TICK_PER_SEC);
        taskMap.put(braveEntity.getUniqueId(), mobHealthBarTask);
    }

    public void removeHealthBar(LivingEntity mobEntity) {
        if (healthBarMap.containsKey(mobEntity)) healthBarMap.get(mobEntity).hideAll();
        healthBarMap.remove(mobEntity);
    }

    private void cacheHealthBarForPlayer(Player braveEntity, MobHealthBar newHealthBar) {
        if (healthBarCache.get(braveEntity.getUniqueId()) != null) {
            if (healthBarCache.get(braveEntity.getUniqueId()) != newHealthBar) {
                healthBarCache.get(braveEntity.getUniqueId()).hide(braveEntity);
            }
        }
        healthBarCache.put(braveEntity.getUniqueId(), newHealthBar);
    }

}
