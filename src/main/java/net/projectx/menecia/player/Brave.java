package net.projectx.menecia.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Brave {

    private UUID uuid;
    private boolean isStunned = false;
    private boolean isPoisoned = false;
    private boolean isFreeze = false;
    private boolean isDying = false;

    public Brave(Player player) {
        uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

}
