package net.projectx.menecia.utilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.util.Consumer;

public class Hologram {

    public static AreaEffectCloud draw(String string, Location location) {
        return spawnAreaEffectCloud(string, location);
    }

    private static AreaEffectCloud spawnAreaEffectCloud(String string, Location location) {
        World world = location.getWorld();
        Consumer<AreaEffectCloud> consumer = (hologram) -> {
            hologram.setCustomNameVisible(true);
            hologram.setCustomName(Utils.color(string));
            hologram.clearCustomEffects();
            hologram.setRadius(0);
            hologram.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.BLACK, 0));
        };
        return world.spawn(location, AreaEffectCloud.class, consumer);
    }

}
