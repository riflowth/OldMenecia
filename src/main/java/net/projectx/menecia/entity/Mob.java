package net.projectx.menecia.entity;

import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public abstract class Mob {

    protected Entity entity;
    private EntityType entityType;
    protected String name;
    protected double health;
    protected boolean hasSpawned = false;

    public Mob(EntityType entityType, String name, double health) {
        this.entityType = entityType;
        this.name = name;
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void spawn(Location location) {
        if (hasSpawned) {
            Log.sendWarning("Can't spawn " + this.getClass().getName() + " named " + name + ". It has spawned!");
        } else {
            entity = location.getWorld().spawnEntity(location, entityType);
        }
        hasSpawned = !hasSpawned;
    }

    public void despawn() {
        if (hasSpawned) {
            entity.remove();
        }
        hasSpawned = !hasSpawned;
    }

    public Entity getEntity() {
        return entity;
    }

}
