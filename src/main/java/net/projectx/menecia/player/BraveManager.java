package net.projectx.menecia.player;

import net.projectx.menecia.utilities.Log;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BraveManager {

    private Map<UUID, Brave> braveMap = new HashMap<>();

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
