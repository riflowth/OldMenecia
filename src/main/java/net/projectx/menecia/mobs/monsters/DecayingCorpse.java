package net.projectx.menecia.mobs.monsters;

import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobType;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class DecayingCorpse implements Mob {

    public static final int ID = 3;
    private static final MobType mobType = MobType.HOSTILE;
    private static final EntityType entityType = EntityType.ZOMBIE_VILLAGER;
    private static final String name = "Decaying Corpse";
    private static final int level = 7;
    private static final int maxHealth = 32;
    private static final int[] attackDamageRange = {10, 18};
    private static final int experience = 10;
    private static final int[] coinDrops = {5, 8};

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
        ((ZombieVillager) entity).setVillagerProfession(Villager.Profession.FARMER);
        ((ZombieVillager) entity).getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        ((ZombieVillager) entity).getEquipment().setBootsDropChance(0);
        ((ZombieVillager) entity).getEquipment().setHelmet(new ItemStack(Material.SKELETON_SKULL));
        ((ZombieVillager) entity).getEquipment().setHelmetDropChance(0);
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
    public int[] getCoinDrops() {
        return coinDrops;
    }

    @Override
    public void attack(Player player) {

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

}
