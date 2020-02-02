package net.projectx.menecia.mobs.monsters;

import net.projectx.menecia.mobs.HostileMob;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class StarvingZombie implements HostileMob {

    public static final int ID = 1;
    private static final EntityType entityType = EntityType.ZOMBIE;
    private static final String name = "Starving Zombie";
    private static final int level = 5;
    private static final int maxHealth = 10;
    private static final int[] attackDamageRange = new int[] {2, 5};
    private static final int experience = 5;

    @Override
    public void attack(Player player) {

    }

    @Override
    public void stopAttack(Player player) {

    }

    @Override
    public int[] getAttackDamageRange() {
        return attackDamageRange;
    }

    @Override
    public int getLeastAttackDamage() {
        return attackDamageRange[0];
    }

    @Override
    public int getMostAttackDamage() {
        return attackDamageRange[1];
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getExperience() {
        return experience;
    }

}
