package net.projectx.menecia.player;

import net.projectx.menecia.resources.Icons;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerStatusBarUpdater extends BukkitRunnable {

    private List<UUID> uuids = new ArrayList<>();

    public void update(Player player) {
        uuids.add(player.getUniqueId());
    }

    public void remove(Player player) {
        uuids.remove(player.getUniqueId());
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        uuids.forEach((uuid) -> {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null) {
                uuids.remove(uuid);
                return;
            }

            int maxHealth = (int) PlayerUtil.getMaxHealth(player);
            int health = (int) player.getHealth();

            stringBuilder
                    .append("&c")
                    .append(Icons.RED_HEART)
                    .append("HP ")
                    .append(health)
                    .append("/")
                    .append(maxHealth)
                    .append("     ");

            stringBuilder
                    .append("&2[")
                    .append("|||||")
                    .append("Sprint")
                    .append("|||||")
                    .append("]")
                    .append("     ");

            stringBuilder
                    .append("&9")
                    .append(Icons.STAR)
                    .append("Mana ")
                    .append(0)
                    .append("/")
                    .append(0);

            player.sendActionBar('&', stringBuilder.toString());
            stringBuilder.setLength(0);
        });
    }

}
