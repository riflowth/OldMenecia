package net.projectx.menecia.mobs.healthbar;

import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.resources.Icons;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MobHealthBar {

    private LivingEntity mobEntity;
    private BossBar healthBar;

    public MobHealthBar(LivingEntity mobEntity) {
        this.mobEntity = mobEntity;
        create(mobEntity);
    }

    public void show(Player player) {
        healthBar.addPlayer(player);
    }

    public boolean hasShowTo(Player player) {
        return healthBar.getPlayers().contains(player);
    }

    public void hide(Player player) {
        healthBar.removePlayer(player);
    }

    public void hide(UUID playerUuid) {
        Player player = Bukkit.getPlayer(playerUuid);
        if (player != null) {
            healthBar.removePlayer(player);
        }
    }

    public void hideAll() {
        healthBar.removeAll();
    }

    public void update() {
        double mobMaxHealth = MobUtil.getMobInstance(mobEntity).getMaxHealth();
        double mobHealth = mobEntity.getHealth();
        double healthBarValue = (mobHealth / mobMaxHealth);
        if (mobHealth <= 0) healthBarValue = 0;
        healthBar.setProgress(healthBarValue);
        String mobDisplayName = MobUtil.getDisplayNameWithLevel(MobUtil.getMobInstance(mobEntity));
        healthBar.setTitle(Utils.color(mobDisplayName + " &f- &c" + (int) mobHealth + " &4" + Icons.RED_HEART));
    }

    private void create(LivingEntity mobEntity) {
        String mobDisplayName = MobUtil.getDisplayNameWithLevel(MobUtil.getMobInstance(mobEntity));
        int health = (int) mobEntity.getHealth();
        String display = Utils.color(mobDisplayName + " &f- &c" + health + " &4" + Icons.RED_HEART);
        healthBar = Bukkit.createBossBar(display, BarColor.RED, BarStyle.SEGMENTED_20);
    }

}
