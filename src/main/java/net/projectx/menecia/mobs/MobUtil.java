package net.projectx.menecia.mobs;

import net.projectx.menecia.resources.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MobUtil {

    public static Entity spawn(Mob mob, Location location) {
        try {
            LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, mob.getEntityType());

            entity.getPersistentDataContainer().set(Keys.MOB_ID, PersistentDataType.INTEGER, mob.getId());
            entity.setCustomNameVisible(true);
            entity.setCustomName(getDisplayNameWithLevel(mob));
            entity.setRemoveWhenFarAway(false);

            if (entity instanceof Zombie) ((Zombie) entity).setBaby(false);
            if (mob.getSize() != null) {
                int[] allSize = mob.getSize();
                int selectedSize = allSize[0];
                if (allSize.length > 1) {
                    selectedSize = ThreadLocalRandom.current().nextInt(allSize[0], allSize[allSize.length - 1] + 1);
                }
                if (entity instanceof Slime) {
                    ((Slime) entity).setSize(selectedSize);
                } else if (entity instanceof Phantom) {
                    ((Phantom) entity).setSize(selectedSize);
                }
            }

            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(mob.getMaxHealth());
            entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
            entity.setHealth(mob.getMaxHealth());

            mob.spawn(entity);

            return entity;
        } catch (ClassCastException exception) {
            Entity entity = location.getWorld().spawnEntity(location, mob.getEntityType());
            entity.getPersistentDataContainer().set(Keys.MOB_ID, PersistentDataType.INTEGER, mob.getId());
            return entity;
        }
    }

    public static int getId(Entity entity) {
        if (isMob(entity)) {
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            return dataContainer.get(Keys.MOB_ID, PersistentDataType.INTEGER);
        }
        return -1;
    }

    public static Mob getMobInstance(Entity entity) {
        return MobManager.getMob(getId(entity));
    }

    public static boolean isMob(Entity entity) {
        if (!(entity instanceof LivingEntity)) return false;
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        return dataContainer.has(Keys.MOB_ID, PersistentDataType.INTEGER);
    }

    public static String getDisplayNameWithLevel(Mob mob) {
        return getDisplayName(mob) + ChatColor.DARK_GREEN + " [Lv." + mob.getLevel() + "]";
    }

    public static String getDisplayName(Mob mob) {
        Map<MobType, ChatColor> prefixColorMap = new HashMap<MobType, ChatColor>(){{
            put(MobType.PEACEFUL, ChatColor.GREEN);
            put(MobType.NEUTRAL, ChatColor.GOLD);
            put(MobType.HOSTILE, ChatColor.RED);
        }};
        return prefixColorMap.get(mob.getMobType()) + mob.getName();
    }

}
