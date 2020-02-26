package net.projectx.menecia.mobs;

import net.projectx.menecia.mobs.monsters.BabySlime;
import net.projectx.menecia.mobs.monsters.DecayingCorpse;
import net.projectx.menecia.mobs.monsters.MotherSlime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobManager {

    private static MobManager instance;
    private Map<Integer, Mob> mobMap = new HashMap<>();

    private MobManager() {}

    public static void register() {
        if (instance == null) {
            instance = new MobManager();
            registerMob(BabySlime.ID, new BabySlime());
            registerMob(MotherSlime.ID, new MotherSlime());
            registerMob(DecayingCorpse.ID, new DecayingCorpse());
        }
    }

    private static void registerMob(int id, Mob mob) {
        instance.mobMap.put(id, mob);
    }

    public static Mob getMob(int id) {
        return instance.mobMap.get(id);
    }

    public static List<Mob> getAllMobs() {
        return new ArrayList<>(instance.mobMap.values());
    }

    public static void killAllMobs() {
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            if (MobUtil.isMob(entity)) {
                entity.remove();
            }
        }
    }

}
