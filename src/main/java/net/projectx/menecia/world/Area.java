package net.projectx.menecia.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Area {

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private int zMin;
    private int zMax;
    private World world;

    public Area(Location firstLocation, Location secondLocation) {
        xMin = Math.min(firstLocation.getBlockX(), secondLocation.getBlockX());
        xMax = Math.max(firstLocation.getBlockX(), secondLocation.getBlockX());
        yMin = Math.min(firstLocation.getBlockY(), secondLocation.getBlockY());
        yMax = Math.max(firstLocation.getBlockY(), secondLocation.getBlockY());
        zMin = Math.min(firstLocation.getBlockZ(), secondLocation.getBlockZ());
        zMax = Math.max(firstLocation.getBlockZ(), secondLocation.getBlockZ());
        world = firstLocation.getWorld();
    }

    public Location getCenter() {
        return new Location(world, (xMax - xMin) / 2.0 + xMin, (yMax -yMin) / 2.0 + yMin, (zMax - zMin) / 2.0 + zMin);
    }

    public Location getRandomLocation() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int x = rand.nextInt(Math.abs(xMax - xMin) + 1) + xMin;
        int y = rand.nextInt(Math.abs(yMax - yMin) + 1) + yMin;
        int z = rand.nextInt(Math.abs(zMax - zMin) + 1) + zMin;
        return new Location(world, x, y, z);
    }

    public boolean isIn(Location loc) {
        return loc.getWorld() == world && loc.getBlockX() >= xMin && loc.getBlockX() <= xMax && loc.getBlockY() >= yMin &&
                loc.getBlockY() <= yMax && loc.getBlockZ() >= zMin && loc.getBlockZ() <= zMax;
    }

    public boolean isIn(Player player) {
        return isIn(player.getLocation());
    }

    public void expand(int radius) {
        xMin -= radius;
        xMax += radius;
        zMin -= radius;
        zMax += radius;
    }

}
