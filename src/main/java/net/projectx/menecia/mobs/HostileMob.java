package net.projectx.menecia.mobs;

import org.bukkit.entity.Player;

public interface HostileMob extends Mob {

    void attack(Player player);

    void stopAttack(Player player);

    int[] getAttackDamageRange();

    int getLeastAttackDamage();

    int getMostAttackDamage();

}
