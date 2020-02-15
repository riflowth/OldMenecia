package net.projectx.menecia.mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public interface Mob {

    int getId();

    MobType getMobType();

    EntityType getEntityType();

    void spawn(Entity entity);

    void despawn(Entity entity);

    String getName();

    int getLevel();

    int getMaxHealth();

    int getExperience();

    void attack(Player player);

    void stopAttack();

    void defense();

    int[] getAttackDamageRange();

    int getLeastAttackDamage();

    int getMostAttackDamage();

    int[] getSize();

}
