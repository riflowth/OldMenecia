package net.projectx.menecia.mobs.monsters;

import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobType;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MotherSlime implements Mob {

    public static final int ID = 2;
    private static final MobType mobType = MobType.HOSTILE;
    private static final EntityType entityType = EntityType.SLIME;
    private static final String name = "Mother Slime";
    private static final int level = 2;
    private static final int maxHealth = 25;
    private static final int experience = 5;
    private static final int[] attackDamageRange = {8, 12};
    private static final int[] size = {3};

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public MobType getMobType() {
        return mobType;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public void spawn(Entity entity) {
    }

    @Override
    public void despawn(Entity entity) {

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
    public void attack(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Utils.TICK_PER_SEC * 2, 1, false, false));
    }

    @Override
    public void stopAttack() {

    }

    @Override
    public void defense() {

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
    public int[] getSize() {
        return size;
    }

}
