package net.projectx.menecia.player;

import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerWrapperManager {

    private Map<UUID, PlayerWrapper> playerUuidToWrapper = new HashMap<>();

    public void add(Player player) {
        playerUuidToWrapper.put(player.getUniqueId(), new PlayerWrapper(player));
        Log.sendSuccess("Added Player named &f" + player.getName());
    }

    public void remove(Player player) {
        playerUuidToWrapper.remove(player.getUniqueId());
    }

    public PlayerWrapper getPlayerWrapper(Player player) {
        return playerUuidToWrapper.get(player.getUniqueId());
    }

}
