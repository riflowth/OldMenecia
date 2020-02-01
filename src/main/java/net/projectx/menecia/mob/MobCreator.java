package net.projectx.menecia.mob;

import net.projectx.menecia.Core;
import net.projectx.menecia.resources.Key;
import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.persistence.PersistentDataType;

public class MobCreator {

    private Core plugin;

    public MobCreator(Core plugin) {
        this.plugin = plugin;
    }

    public Entity spawn(Mob mob, Location location) {
        Entity entity = location.getWorld().spawnEntity(location, mob.getEntityType());

        entity.getPersistentDataContainer().set(getKey(Key.MOB_ID), PersistentDataType.INTEGER, mob.getId());

        entity.setCustomNameVisible(true);
        entity.setCustomName(getDisplayName(mob));

        if (mob.getEntityType() == EntityType.ZOMBIE) {
            ((Zombie) entity).setBaby(false);
        }

        return entity;
    }

    private String getDisplayName(Mob mob) {
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

    private NamespacedKey getKey(Key key) {
        return plugin.getNamespacedKey(key);
    }

}
