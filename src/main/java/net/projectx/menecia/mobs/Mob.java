package net.projectx.menecia.mobs;

import org.bukkit.entity.EntityType;

public interface Mob {

    int getId();

    EntityType getEntityType();

    String getName();

    int getLevel();

    int getMaxHealth();

    int getExperience();

}
