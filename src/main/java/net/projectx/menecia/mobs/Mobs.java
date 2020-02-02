package net.projectx.menecia.mobs;

import net.projectx.menecia.mobs.monsters.StarvingZombie;
import net.projectx.menecia.resources.Keys;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Mobs {

    private static Mobs instance;
    private Map<Integer, Mob> mobMap = new HashMap<>();

    public static void registerMobs() {
        if (instance == null) {
            instance = new Mobs();
            instance.mobMap.put(StarvingZombie.ID, new StarvingZombie());
        }
    }

    public static Mob get(int mobId) {
        return instance.mobMap.get(mobId);
    }

    public static Collection<Mob> getAllMobs() {
        return instance.mobMap.values();
    }

    public static Entity spawn(Mob mob, Location location) {
        Entity entity = location.getWorld().spawnEntity(location, mob.getEntityType());

        entity.getPersistentDataContainer().set(Keys.MOB_ID, PersistentDataType.INTEGER, mob.getId());

        entity.setCustomNameVisible(true);
        entity.setCustomName(getDisplayName(mob));

        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(mob.getMaxHealth());
            livingEntity.setHealth(mob.getMaxHealth());
        }

        if (mob.getEntityType() == EntityType.ZOMBIE) {
            ((Zombie) entity).setBaby(false);
        }

        return entity;
    }

    public static boolean isMob(Entity entity) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        return dataContainer.has(Keys.MOB_ID, PersistentDataType.INTEGER);
    }

    public static int getId(Entity entity) {
        if (isMob(entity)) {
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            return dataContainer.get(Keys.MOB_ID, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public static String getDisplayName(Mob mob) {
        String mobName = mob.getName();
        int mobLevel = mob.getLevel();
        if (mob instanceof HostileMob) {
            return Utils.translateColor("&c" + mobName + " &2[Lv." + mobLevel + "]");
        } else if (mob instanceof NeutralMob) {
            return Utils.translateColor("&6" + mobName + " &2[Lv." + mobLevel + "]");
        } else {
            return Utils.translateColor("&a" + mobName + " &2[Lv." + mobLevel + "]");
        }
    }

}
