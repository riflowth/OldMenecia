package net.projectx.menecia.mobs.monsters;

import net.projectx.menecia.mobs.HostileMob;
import net.projectx.menecia.mobs.ResizableMob;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MotherSlime implements HostileMob, ResizableMob {

    public static final int ID = 2;
    private static final EntityType entityType = EntityType.SLIME;
    private static final String name = "Mother Slime";
    private static final int level = 2;
    private static final int maxHealth = 25;
    private static final int[] attackDamageRange = {8, 12};
    private static final int experience = 5;
    private static final int[] size = {3};

    @Override
    public void attack(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 2, 1, false, false));
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

    @Override
    public int[] getSize() {
        return size;
    }
}
