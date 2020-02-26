package net.projectx.menecia.player;

import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerWrapperManager {

    private Map<UUID, PlayerWrapper> uuidToWrapper = new HashMap<>();

    public void add(Player player) {
        uuidToWrapper.put(player.getUniqueId(), new PlayerWrapper(player));
        Log.sendSuccess("Added Player named &f" + player.getName());
    }

    public void remove(Player player) {
        uuidToWrapper.remove(player.getUniqueId());
    }

    public PlayerWrapper getPlayerWrapper(Player player) {
        return uuidToWrapper.get(player.getUniqueId());
    }

}
