package net.projectx.menecia.mobs;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.monsters.BabySlime;
import net.projectx.menecia.mobs.monsters.MotherSlime;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MobManager {

    private static MobManager instance;
    private Map<Integer, Mob> mobMap = new HashMap<>();

    public static void registerMobs(Menecia plugin) {
        if (instance == null) instance = new MobManager();

        instance.mobMap.put(BabySlime.ID, new BabySlime());
        instance.mobMap.put(MotherSlime.ID, new MotherSlime());
    }

    public static Mob getMob(int id) {
        return instance.mobMap.get(id);
    }

    public static Collection<Mob> getAllMobs() {
        return instance.mobMap.values();
    }

}
