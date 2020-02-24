package net.projectx.menecia.mobs.healthbar;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.player.bossbars.MobHealthBar;
import net.projectx.menecia.resources.utilities.Utils;
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

    public void showHealthBar(Player player, LivingEntity mobEntity) {
        healthBarMap.putIfAbsent(mobEntity, new MobHealthBar(mobEntity));
        MobHealthBar mobHealthBar = healthBarMap.get(mobEntity);
        cacheHealthBarForPlayer(player, mobHealthBar);
        if (!mobHealthBar.hasShowTo(player)) mobHealthBar.show(player);
        mobHealthBar.update();
        updateMobHealBarTask(player, mobHealthBar);
    }

    public void updateMobHealBarTask(Player player, MobHealthBar mobHealthBar) {
        if (taskMap.get(player.getUniqueId()) != null) taskMap.get(player.getUniqueId()).cancel();
        MobHealthBarTask mobHealthBarTask = new MobHealthBarTask(player.getUniqueId(), mobHealthBar);
        mobHealthBarTask.runTaskTimerAsynchronously(plugin, 0, 20);
        taskMap.put(player.getUniqueId(), mobHealthBarTask);
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
