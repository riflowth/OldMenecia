package net.projectx.menecia.mobs;

import net.projectx.menecia.mobs.monsters.BabySlime;
import net.projectx.menecia.resources.Keys;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MobUtil {

    private static MobUtil instance;
    private Map<Integer, Mob> mobMap = new HashMap<>();

    public static void registerMobs() {
        if (instance == null) {
            instance = new MobUtil();
            instance.mobMap.put(BabySlime.ID, new BabySlime());
        }
    }

    public static LivingEntity spawn(Mob mob, Location location) {
        try {
            LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, mob.getEntityType());

            entity.getPersistentDataContainer().set(Keys.MOB_ID, PersistentDataType.INTEGER, mob.getId());
            entity.setCustomNameVisible(true);
            entity.setCustomName(getDisplayNameWithLevel(mob));
            entity.setRemoveWhenFarAway(false);

            if (entity instanceof Zombie) ((Zombie) entity).setBaby(false);
            if (mob instanceof ResizableMob) {
                int[] allSize = ((ResizableMob) mob).getSize();
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

            return entity;
        } catch (ClassCastException exception) {
            return null;
        }
    }

    public static int getId(Entity entity) {
        if (isMob(entity)) {
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            return dataContainer.get(Keys.MOB_ID, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public static Mob getMobInstance(int mobId) {
        return instance.mobMap.get(mobId);
    }

    public static Mob getMobInstance(Entity entity) {
        return instance.mobMap.get(getId(entity));
    }

    public static Collection<Mob> getAllMobs() {
        return instance.mobMap.values();
    }

    public static boolean isMob(Entity entity) {
        if (!(entity instanceof LivingEntity)) return false;
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        return dataContainer.has(Keys.MOB_ID, PersistentDataType.INTEGER);
    }

    public static String getDisplayNameWithLevel(Mob mob) {
        String mobName = mob.getName();
        int mobLevel = mob.getLevel();
        if (mob instanceof HostileMob) {
            return Utils.color("&c" + mobName + " &2[Lv." + mobLevel + "]");
        } else if (mob instanceof NeutralMob) {
            return Utils.color("&6" + mobName + " &2[Lv." + mobLevel + "]");
        } else {
            return Utils.color("&a" + mobName + " &2[Lv." + mobLevel + "]");
        }
    }

    public static String getDisplayName(Mob mob) {
        String mobName = mob.getName();
        if (mob instanceof HostileMob) {
            return Utils.color("&c" + mobName);
        } else if (mob instanceof NeutralMob) {
            return Utils.color("&6" + mobName);
        } else {
            return Utils.color("&a" + mobName);
        }
    }

}
