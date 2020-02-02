package net.projectx.menecia;

import net.projectx.menecia.player.Brave;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private Map<UUID, Brave> braveMap = new HashMap<>();

    DataManager() {
        Log.sendSuccess("DataManager has created");
    }

    public void addBrave(Player player) {
        braveMap.put(player.getUniqueId(), registerBrave(player));
        Log.sendSuccess("Added Brave named &f" + player.getName());
    }

    private Brave registerBrave(Player player) {
        return new Brave(player);
    }

    public void removeBrave(Player player) {
        braveMap.remove(player.getUniqueId());
    }

    public Brave getBrave(Player player) {
        return braveMap.get(player.getUniqueId());
    }

}
