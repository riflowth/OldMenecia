package net.projectx.menecia.resources.utilities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;

public class Hologram {

    public static void draw(String string, Location location) {
        spawnAreaEffectCloud(string, location);
    }

    public static void drawTemporary(String string, int second, Location location) {
        spawnAreaEffectCloud(string, location).setDuration(20 * second);
    }

    private static AreaEffectCloud spawnAreaEffectCloud(String string, Location location) {
        World world = location.getWorld();
        AreaEffectCloud hologram = (AreaEffectCloud) world.spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(Utils.translateColor(string));
        hologram.setRadius(0);
        hologram.clearCustomEffects();
        return hologram;
    }

}
