package net.projectx.menecia.player;

import net.projectx.menecia.resources.Icons;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerActionBarTask extends BukkitRunnable {

    private List<UUID> updateList = new ArrayList<>();

    public void addUpdater(Player player) {
        updateList.add(player.getUniqueId());
    }

    public void removeUpdater(Player player) {
        updateList.remove(player.getUniqueId());
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        updateList.forEach((uuid) -> {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null) {
                updateList.remove(uuid);
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
        });
    }

}
