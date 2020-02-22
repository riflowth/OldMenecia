package net.projectx.menecia.player;

import net.projectx.menecia.utilities.Utils;
import org.bukkit.entity.Player;

public class TabListManager {

    public static void initialize(Player player) {
        player.setPlayerListHeader(
                Utils.color("&f&lMenecia\n&7(The MMORPG Minecraft Server)")
        );
    }

}
