package net.projectx.menecia.mobs;

import net.projectx.menecia.mobs.monsters.BabySlime;
import net.projectx.menecia.mobs.monsters.MotherSlime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MobManager {

    private static MobManager instance;
    private Map<Integer, Mob> mobMap = new HashMap<>();

    public static void registerMobs() {
        if (instance == null) {
            instance = new MobManager();
            register(BabySlime.ID, new BabySlime());
            register(MotherSlime.ID, new MotherSlime());
        }
    }

    private static void register(int id, Mob mob) {
        instance.mobMap.put(id, mob);
    }

    public static Mob getMob(int id) {
        return instance.mobMap.get(id);
    }

    public static Collection<Mob> getAllMobs() {
        return instance.mobMap.values();
    }

    public static void killAllMobs() {
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            if (MobUtil.isMob(entity)) {
                entity.remove();
            }
        }
    }

}
