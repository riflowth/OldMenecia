package net.projectx.menecia.mobs.spawner;

import net.projectx.menecia.locations.Area;
import net.projectx.menecia.mobs.Mob;

public class MobSpawner {

    private Mob mob;
    private MobSpawnRate spawnRate;
    private Area spawningArea;
    private int maximumAmount;
    private int currentAmount = 0;
    private long latestSpawnTimestamp;

    public MobSpawner(Mob mob, MobSpawnRate spawnRate, Area spawningArea, int maximumAmount) {
        this.mob = mob;
        this.spawnRate = spawnRate;
        this.spawningArea = spawningArea;
        this.maximumAmount = maximumAmount;
    }

    public Mob getMob() {
        return mob;
    }

    public MobSpawnRate getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(MobSpawnRate spawnRate) {
        this.spawnRate = spawnRate;
    }

    public Area getSpawningArea() {
        return spawningArea;
    }

    public int getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(int maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void increaseCurrentAmount() {
        this.currentAmount++;
    }

    public void decreaseCurrentAmount() {
        this.currentAmount--;
    }

    public long getLatestSpawnTimestamp() {
        return latestSpawnTimestamp;
    }

    public void setLatestSpawnTimestamp(long latestSpawnTimestamp) {
        this.latestSpawnTimestamp = latestSpawnTimestamp;
    }

}
